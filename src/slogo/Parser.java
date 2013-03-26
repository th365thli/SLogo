package slogo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import behavior.ICommand;
import exceptions.NoSuchCommandException;


/**
 * parse the command
 * 
 * @author Richard Yang
 *         Parses input
 * 
 * @author Richard, Jerry
 * 
 */
public class Parser {

    private static final String DEFAULT_RESOURCE_PACKAGE = "resources.";

    private ResourceBundle myResources;

    private Pattern myNumPattern;
    private Pattern myStrPattern;
    private Pattern mySpacePattern;

    /**
     * constructor
     * Constructs parser
     */
    public Parser () {
        myNumPattern = Pattern.compile("[0-9]*");
        myStrPattern = Pattern.compile("[a-zA-Z]*");
        mySpacePattern = Pattern.compile("[\\s]+");
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "commands");
    }

    /**
     * Splits String of spaces and brackets
     * 
     * @param s String
     * @param model model
     * @return
     * @throws Exception exception
     */
    public List<String[]> split (String s, Model model) throws Exception {
        List<String> l = new LinkedList<String>();
        int depth = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[') {
                depth += 1;
            }
            else if (c == ']') {
                depth -= 1;
            }
            else if (c == ' ' && depth == 0) {
                l.add(sb.toString().toUpperCase());
                sb = new StringBuilder();
                continue;
            }
            sb.append(c);
        }
        l.add(sb.toString().toUpperCase());

        return addCommands(l, model);
    }

    /**
     * creates list of commands and adds it
     * 
     * @param l string list
     * @param model model
     * @return
     * @throws Exception exception
     */
    public List<String[]> addCommands (List<String> l, Model model) throws Exception {
        List<String[]> commandArray = new ArrayList<String[]>();
        for (int i = 0; i < l.size(); i++) {

            if (model.getUserCommands().containsKey(l.get(i))) {
                String[] userCommand = new String[1];
                userCommand[0] = l.get(i).toUpperCase();
                commandArray.add(userCommand);

            }
            else if (myResources.containsKey(l.get(i))) {
                ArrayList<String> temp = new ArrayList<String>();
                String commandName = myResources.getString(l.get(i).toUpperCase());

                Class<?> commandClass = null;
                try {
                    commandClass = Class.forName("behavior." + commandName);
                }
                catch (ClassNotFoundException e) {
                    model.showMessage("class not found");
                }

                Field field = commandClass.getDeclaredField("PARAMETER_NUMBER");
                int parameter = field.getInt(commandClass);
                for (int j = 0; j < parameter + 1; j++) {
                    temp.add(l.get(i + j));
                }
                String command[] = new String[temp.size()];
                temp.toArray(command);
                commandArray.add(command);
            }
            else {
                determineException(l.get(i), model);
                break;
            }

        }

        return commandArray;
    }

    public void determineException (String string, Model model) {
        model.showMessage("\"" + string + "\"" + " is not a valid command");

    }

    /**
     * build a command through string we got
     * 
     * @param str splited input commands
     * @param model model we want to operate
     * @return command
     * @throws Exception
     */

    public ICommand buildCommand (String[] str, Model model) throws Exception {
        if (model.getUserCommands().containsKey(str[0].toUpperCase())) {
            return model.getUserCommands().get(str[0].toUpperCase());
        }
        else if (!myResources.containsKey(str[0].toUpperCase())) {
            throw new NoSuchCommandException();
        }
        else {
            String[] subArray = subStringArray(str, model);
            String commandName = myResources.getString(str[0].toUpperCase());

            Class<?> commandClass = null;
            try {
                commandClass = Class.forName("behavior." + commandName);
            }
            catch (ClassNotFoundException e) {
                model.showMessage("class not found");
            }
            Object o = null;
            try {
                o = commandClass.newInstance();

            }
            catch (InstantiationException | IllegalAccessException e) {
                model.showMessage("illegal access");
            }
            ICommand myCommand = (ICommand) o;
            myCommand.initialize(subArray, model);
            return myCommand;

        }
    }

    /**
     * build multiple commands
     * 
     * @param commands command strings
     * @param model mode we want to operate
     * @return
     * @throws Exception
     */

    public List<ICommand> buildMultipleCommands (List<String[]> commands, Model model)
                                                                                      throws Exception {
        if (commands == null) { return null; }

        List<ICommand> myCommandList = new ArrayList<ICommand>();
        for (int i = 0; i < commands.size(); i++) {
            String[] str = commands.get(i);
            myCommandList.add(buildCommand(str, model));
        }

        return myCommandList;
    }

    /**
     * delete first element of a string
     * 
     * @param str input string
     */
    public String[] subStringArray (String[] str, Model model) {
        int size = str.length;
        String[] subArray = new String[size - 1];
        for (int i = 0; i < size - 1; i++) {
            if (str[i + 1].charAt(0) == ':' && !str[i].toUpperCase().equals("SET")) {
                if (!model.getUserVariables().containsKey(str[i + 1])) {
                    model.addVariable(str[i + 1], 0 + "");
                    model.getUserVariables();

                }
                subArray[i] = model.getUserVariables().get(str[i + 1]);
            }
            else {
                subArray[i] = str[i + 1];
            }
        }
        return subArray;
    }

    public void parse (String command, List<ICommand> myCommandList,
                       Model model) throws Exception {
        String parsedCommand = parseExtraSpaces(command, model);
        myCommandList.addAll(buildMultipleCommands(split(parsedCommand, model), model));
    }

    public String parseExtraSpaces (String command, Model model) {
        String delim = "[ ]+";
        String[] parsedCommandArray = command.split(delim);
        String parsedCommand = "";
        for (String element : parsedCommandArray) {
            parsedCommand += element + " ";
        }
        return parsedCommand;
    }

    /**
     * 
     * @param commandWithBracket
     * @return
     */
    public String prune (String commandWithBracket) {
        return commandWithBracket.substring(1, commandWithBracket.length() - 1);
    }

    /**
     * 
     * @param myCommand
     * @return
     */
    public String[] splitBlanksInsideBracket (String myCommand) {
        String myPrunedCommand = prune(myCommand);
        String[] myContent = getSpacePattern().split(myPrunedCommand);
        List<String> buffer = new ArrayList<String>();
        for (int i = 0; i < myContent.length; i++) {
            if (!getSpacePattern().matcher(myContent[i]).matches() &&
                !myContent[i].equals("")) {
                buffer.add(myContent[i]);
            }
        }
        String[] myNewContent = new String[buffer.size()];
        for (int i = 0; i < buffer.size(); i++) {
            myNewContent[i] = buffer.get(i);
        }
        return myNewContent;
    }

    public Pattern getNumPattern () {
        return myNumPattern;
    }

    public boolean judgeNumeric (String str) {
        if (myNumPattern.matcher(str).matches()) { return true; }
        return false;
    }

    public Pattern getSpacePattern () {
        return mySpacePattern;
    }

}
