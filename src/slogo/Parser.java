package slogo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import behavior.ICommand;
import behavior.flow.If;
import behavior.flow.IfElse;
import behavior.flow.Repeat;
import behavior.flow.To;
import exceptions.NoSuchCommandException;
import exceptions.NoSuchVariableException;
import exceptions.ParameterException;
import exceptions.SyntaxException;


/**
<<<<<<< HEAD
 * parse the command
 * 
 * @author Richard Yang
=======
>>>>>>> 0b9ccc6ce78591a46d0a6f2849f954e9db33bc8e
 * Parses input
 * 
 * @author Richard, Jerry
 * 
 */
public class Parser {

    private static final int TO_LENGTH = 3;
    private static final int IFELSE_LENGTH = 7;
    private static final String LEFT_BRACKET = "[";
    private static final String DEFAULT_RESOURCE_PACKAGE = "resources.";
    private Map<String, ICommand> myUserToCommands = new HashMap<String, ICommand>();

    private Pattern myNumPattern;
    private Pattern myStrPattern;
    private Pattern mySpacePattern;
    private ResourceBundle myResources;
    private ResourceBundle myFlows;

    /**
<<<<<<< HEAD
     * constructor
=======
>>>>>>> 0b9ccc6ce78591a46d0a6f2849f954e9db33bc8e
     * Constructs parser
     */
    public Parser () {
        myNumPattern = Pattern.compile("[0-9]*");
        myStrPattern = Pattern.compile("[a-zA-Z]*");
        mySpacePattern = Pattern.compile("[\\s]+");
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "commands");
        myFlows = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "flow");
    }

    /**
     * split the string
     * 
     * @param commands commands we want to split
     * @return splited string
<<<<<<< HEAD
     * Splits commands
     * 
     * @param commands commands
     * @return
=======
     * 
>>>>>>> 0b9ccc6ce78591a46d0a6f2849f954e9db33bc8e
     */
    public ArrayList<String[]> split (String commands) {

        if (commands == null) { return null; }

        ArrayList<String[]> allCommands = new ArrayList<String[]>();
        ArrayList<StringBuffer> allBuffers = new ArrayList<StringBuffer>();

        for (int i = 0; i < commands.length(); i++) {
            if (commands.charAt(i) != ' ') {
                commands = commands.substring(i);
                break;
            }
        }

        String[] cutBySpace = mySpacePattern.split(commands);
        if (cutBySpace.length == 0) { return null; }
        StringBuffer buffer = new StringBuffer();
        buffer.append(cutBySpace[0]);
        for (int i = 1; i < cutBySpace.length; i++) {
            if (myStrPattern.matcher(cutBySpace[i]).matches()) {

                allBuffers.add(buffer);
                buffer = new StringBuffer();
                buffer.append(cutBySpace[i]);
            }
            else if (myNumPattern.matcher(cutBySpace[i]).matches()) {
                buffer.append(" ");
                buffer.append(cutBySpace[i]);
            }
        }
        allBuffers.add(buffer);

        for (int i = 0; i < allBuffers.size(); i++) {
            String[] str = mySpacePattern.split(allBuffers.get(i).toString());
            allCommands.add(str);
            str = null;
        }

        return allCommands;
    }

    /**
     * build a command through string we got
     * 
     * @param str splited input commands
     * @param model model we want to operate
     * @return command
     * @throws NoSuchCommandException
     * @throws SyntaxException
     */

    public ICommand buildCommand (String[] str, Model model) throws NoSuchCommandException,
                                                            SyntaxException {
        if (!myResources.containsKey(str[0].toUpperCase())) {
            throw new NoSuchCommandException();
        }
        else {
            String[] subArray = subStringArray(str);
            System.out.println(str[0]);
            String commandName = myResources.getString(str[0].toUpperCase());

            Class<?> commandClass = null;
            try {
                commandClass = Class.forName("behavior." + commandName);
            }
            catch (ClassNotFoundException e) {
                // model.showMessage("class not found");
            }
            Object o = null;
            try {
                o = commandClass.newInstance();
            }
            catch (InstantiationException | IllegalAccessException e) {

                // model.showMessage("illegal access");

            }
            ICommand myCommand = (ICommand) o;
            myCommand.initialize(subArray, null);
            return myCommand;

        }
    }

    /**
     * build multiple commands
     * 
     * @param commands command strings
     * @param model mode we want to operate
     * @return
     * @throws SyntaxException
     * @throws NoSuchCommandException
     */

    public List<ICommand> buildMultipleCommands (List<String[]> commands, Model model)
                                                                                      throws SyntaxException,
                                                                                      NoSuchCommandException {
        if (commands == null) { return null; }

        List<ICommand> myCommandList = new ArrayList<ICommand>();
        for (int i = 0; i < commands.size(); i++) {
            String[] str = commands.get(i);
            if (str[0].equals("IFELSE") || str[0].equals("TO")) {
                myCommandList.add(buildTwoBracketFlowCommand(str, commands.get(commands.indexOf(str)+1),
                                                             commands.get(commands.indexOf(str)+2), model));
                i += 2;
            }
            else if (str[0].equals("REPEAT") || str[0].equals("IF")) {
              
            }
            else {
                myCommandList.add(buildCommand(str, model));
            }
        }
        return myCommandList;
    }
    
   
    
    public ICommand buildTwoBracketFlowCommand (String[] name, String[] firstBracket, String[] secondBracket, Model model)
            throws SyntaxException,
            NoSuchCommandException {
        
        if (name[0].equals("IFELSE")) {
            String command = "";
            for (int i = 0; i < name.length; i++) {
                command += name[i] + " ";
            }
            command += "[";
            for (int i = 0; i < firstBracket.length; i++) {
                command += firstBracket[i] + " ";
            }
            command += "]";
            command += "[";
            for (int i = 0; i< secondBracket.length; i++) {
                command += secondBracket[i] + " ";
            }
            command += "]";
            System.out.println(command);
            return new IfElse(command, model);
        }
        else if (name[0].equals("TO")) {
            String command = "";
            for (int i = 0; i < name.length; i++) {
                command += name[i] + " ";
            }
            for (int i = 0; i < firstBracket.length; i++) {
                if (i == 0) {
                    command += firstBracket[i] + "[";
                }
                else {
                    command += firstBracket[i] + " ";
                }
            }
            command += "]";
            command += "[";
            for (int i = 0; i < secondBracket.length; i++) {
                command += secondBracket[i] + " ";
            }
            command += "]";
            System.out.println(command + " Second bracket size: " + secondBracket.length);
            return new To(command, model);
        
        }
        else {
            return null;
        }
    }

    /**
     * delete first element of a string
     * 
     * @param str input string
     */
    public String[] subStringArray (String[] str) {
        int size = str.length;
        String[] subArray = new String[size - 1];
        for (int i = 0; i < size - 1; i++) {
            subArray[i] = str[i + 1];
        }

        return subArray;
    }

    /**
     * find brackets that are in pair
     * 
     * @param str input string
     * @param position position of "["
     * @return position of "]"
     * @throws SyntaxException
     */
    public int findRelatedBrackets (String str, int position) throws SyntaxException {
        if (str.charAt(position) != '[') {
            throw new SyntaxException();
        }
        else {
            int priority = 0;
            int i = position;
            for (i = position + 1; i < str.length(); i++) {
                if (priority == 0 && str.charAt(i) == ']') {
                    break;
                }
                else if (str.charAt(i) == '[') {
                    priority++;
                }
                else if (str.charAt(i) == ']') {
                    priority--;
                }
            }
            return i;
        }
    }

    
    
    public void parse(String command, List<ICommand> myCommandList, Model model)  throws NoSuchCommandException,
        SyntaxException,
        NoSuchVariableException {
        
        ArrayList<String[]> splits = split(command);
        for (int i = 0; i < splits.size(); i++) {
            System.out.println(Arrays.toString(splits.get(i)));
        }
        myCommandList.addAll(buildMultipleCommands(split(command), model));
       
    }
    

    /**
     * parse commands that need one bracket
     * 
     * @param command command we want to parse
     * @param myCommandList our commandlist, used for recursion
     * @param model model we want to operation
     * @throws NumberFormatException
     * @throws NoSuchCommandException
     * @throws SyntaxException
     * @throws NoSuchVariableException
     */
    public void parseOneBracket (String command, List<ICommand> myCommandList, Model model)
                                                                                           throws NoSuchCommandException,
                                                                                           SyntaxException,
                                                                                           NoSuchVariableException {

        //System.out.println(command);
        int position = findFirstFlow(command);
        //System.out.println(position);

        if (position == -1) {
            myCommandList.addAll(buildMultipleCommands(split(command), model));
        }
        else {
            String formerString = command.substring(0, position);
            if (formerString.length() != 0 && !mySpacePattern.matcher(formerString).matches()) {
                myCommandList.addAll(buildMultipleCommands(split(formerString), model));
            }
            int bracketPosition = command.indexOf(LEFT_BRACKET);
            int end = findRelatedBrackets(command, bracketPosition);

            String postString = "";

            if (end != command.length()) {
                postString = command.substring(end + 1);
            }

            String repeatString = command.substring(position, bracketPosition);

            List<String[]> repeatBuffer = split(repeatString);
            String flowName = repeatBuffer.get(0)[0].toUpperCase();
            //System.out.println(flowName);
            String recursionString = command.substring(bracketPosition + 1, end - 1);
            System.out.println("recursionString : " + recursionString);
            if (flowName.equals("REPEAT")) {
                myCommandList.add(new Repeat(recursionString,
                                             Integer.parseInt(repeatBuffer.get(0)[1]), model));
            }
            else if (flowName.equals("IF")) {
                myCommandList.add(new If(recursionString, Integer.parseInt(repeatBuffer.get(0)[1]),
                                         model));
            }
            if (postString.length() != 0 && !mySpacePattern.matcher(postString).matches()) {
                parseOneBracket(postString, myCommandList, model);
            }

        }
    }

    /**
     * Parses TO command
     * 
     * @param command String command
     * @param myCommandList command LIst
     * @return
     * @throws SyntaxException Syntax Exception
     * @throws NoSuchCommandException No command exception
     */
   
    
    public int parseTo (String command, List<ICommand> myCommandList, Model model) throws NoSuchCommandException, SyntaxException {
        To currentTo = new To(command, model);
        myCommandList.add(currentTo);
        myUserToCommands.put(currentTo.getName(), currentTo);
        return currentTo.checkLength();
    }

    /**
     * parse ifelse statements
     * 
     * @param command Command
     * @param myCommandList command list
     * @throws SyntaxException Syntax Exeception
     * @throws NoSuchCommandException NoCommand exceptoin
     */

    public void parseIfElse (String command, List<ICommand> myCommandList, Model model)
                                                                                       throws SyntaxException,
                                                                                       NoSuchCommandException {
       IfElse currentIfElse = new IfElse(command, model);
       myCommandList.add(currentIfElse);
       
    }


    /**
     * find first flow word in a string
     * 
     * @param command input command
     * @return position of first flow string
     */
    public int findFirstFlow (String command) {

        int toAndIf = command.length();
        int repeatAndIfElse = command.length();
        for (int i = 0; i < command.length() - 1; i++) {
            if (myFlows.containsKey(command.substring(i, i + 2).toUpperCase())) {
                toAndIf = i;
            }
        }
        for (int i = 0; i < command.length() - 5; i++) {
            if (myFlows.containsKey(command.substring(i, i + 6).toUpperCase())) {
                repeatAndIfElse = i;
            }
        }
        System.out.println("flow" + toAndIf);
        System.out.println("flow" + repeatAndIfElse);
        if (toAndIf < repeatAndIfElse) {
            return toAndIf;
        }
        else if (toAndIf > repeatAndIfElse) {

            return repeatAndIfElse;
        }
        else {
            return -1;
        }
    }

}
