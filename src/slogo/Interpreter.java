package slogo;

import behavior.CommandEntities;
import behavior.ICommand;
<<<<<<< HEAD
import exceptions.NoSuchCommandException;
import exceptions.SyntaxException;
import behavior.To;
import behavior.flow.Repeat;
import java.util.Map;
=======
import behavior.IfElse;
import behavior.Repeat;
import behavior.To;
import exceptions.NoSuchCommandException;
import exceptions.SyntaxException;
import java.util.ArrayList;
>>>>>>> f4f2a115ec12604839ff870bb233820c00d55492
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


/**
 * 
 * @author Richard Yang & Jerry Li
 * 
 */
public class Interpreter {

    private static final int TO_LENGTH = 3;
    private static final int IFELSE_LENGTH = 7;
    private static final String END_BRACKET = "]";

    private CommandEntities myCommands;
    private Pattern myNumPattern;
    private Pattern myStrPattern;
    private Parser myParser;

    private Map<String, ICommand> myUserToCommands = new HashMap<String, ICommand>();

    /**
     * Constructs an interpreter
     */
    public Interpreter () {
        myNumPattern = Pattern.compile("[0-9]*");
        myStrPattern = Pattern.compile("[a-zA-Z]*");
        myParser = new Parser();
    }

<<<<<<< HEAD
   
=======
    /**
     * Returns an arraylist of the user input
     * 
     * @param commands user input
     * @return
     * 
     */
    public ArrayList<String[]> split (String commands) {

        ArrayList<String[]> allCommands = new ArrayList<String[]>();
        ArrayList<StringBuffer> allBuffers = new ArrayList<StringBuffer>();

        String[] cutBySpace = commands.split(" ");

        StringBuffer buffer = new StringBuffer();
        buffer.append(cutBySpace[0]);
        for (int i = 1; i < cutBySpace.length; i++) {
            if (myStrPattern.matcher(cutBySpace[i]).matches() ||
                myListPattern.matcher(cutBySpace[i]).matches()) {

                allBuffers.add(buffer);
                buffer = new StringBuffer();
                buffer.append(cutBySpace[i]);
            }
            else {
                buffer.append(" ");
                buffer.append(cutBySpace[i]);
            }
        }
        allBuffers.add(buffer);

        for (int i = 0; i < allBuffers.size(); i++) {
            String[] str = allBuffers.get(i).toString().split(" ");
            allCommands.add(str);
            str = null;
        }

        return allCommands;
    }
>>>>>>> f4f2a115ec12604839ff870bb233820c00d55492

    /**
     * Translates arraylist input and executes command
     * 
     * @param model the model
     * @param str arraylist command
     * @throws SyntaxException syntax exception
     * @throws NoSuchCommandException
     */
    // have to throw exception
<<<<<<< HEAD
    public void parse ( String command, List<ICommand> myCommandList) throws SyntaxException, NoSuchCommandException {
<<<<<<< HEAD
        
        myParser.parseOneBracket(command, myCommandList);

    }
//
//             //addCommands(split(commands));
//
//    
// public int parseTo (String command, List<ICommand> myCommandList) throws SyntaxException, NoSuchCommandException {
//        
//        int position = command.indexOf("TO");
//        if( position == -1 ){
//             myCommandList.addAll(myParser.buildMultipleCommands(myParser.split(command)));
//        }
//        else{
//            String formerString = command.substring(0, position);
//            if(formerString.length() != 0){
//                myCommandList.addAll(myParser.buildMultipleCommands(myParser.split(formerString)));
//            }
//            int bracketPosition = command.indexOf("[");
//            String commandName = command.substring(position + 3, bracketPosition);
//            String post = command.substring(bracketPosition+1, command.length());
//            String variable = post.substring(0, post.indexOf("]"));
//            String commandsBracket = post.substring(post.indexOf("[")+1, post.length()-1);
//            System.out.println(commandName);
//            System.out.println(variable);
//            System.out.println(commandsBracket);
//            List<String[]> variables = split(variable);
//            List<String[]> commandsFromBracket = split(commandsBracket);
//            System.out.println(Arrays.toString(commandsFromBracket.get(0)) + " " + Arrays.toString(commandsFromBracket.get(1)));
//            myCommandList.add(new To(commandName, variables, commandsFromBracket));
//            myUserToCommands.put(commandName, new To(commandName, variables, commandsFromBracket));
//            
//              if (variables.get(0).length == commandsFromBracket.size()) {
//                  return 1;
//              }
//              else {
//                  return 0;
//              }
//        }
//
//        return 0;
//     }
//
// public int ifElse (String command, List<ICommand> myCommandList) throws SyntaxException, NoSuchCommandException {
//         int position = command.indexOf("IFELSE");
//         if (position == -1) {
//             myCommandList.addAll(myParser.buildMultipleCommands(myParser.split(command)));
//         }
//         else {
//             String formerString = command.substring(0, position);
//             if(formerString.length() != 0){
//                 myCommandList.addAll(myParser.buildMultipleCommands(myParser.split(formerString)));
//             }
//             int bracketPosition = command.indexOf("[");
//             String value = command.substring(position + 3, bracketPosition);
//             String post = command.substring(bracketPosition+1, command.length());
//             String trueCommand = post.substring(0, post.indexOf("]"));
//             String falseCommand = post.substring(post.indexOf("[")+1, post.length()-1);
//             System.out.println(value);
//             System.out.println(trueCommand);
//             System.out.println(falseCommand);
//             List<String[]> trueCommands = split(trueCommand);
//             List<String[]> falseCommands = split(falseCommand);
//             System.out.println(Arrays.toString(trueCommands.get(0)) + " " + Arrays.toString(trueCommands.get(1)));
//             myCommandList.add(new IfElse(commandName, variables, commandsFromBracket, value));
//             myUserToCommands.put(commandName, new To(commandName, variables, commandsFromBracket, value));
//             
//               if (variables.get(0).length == commandsFromBracket.size()) {
//                   return 1;
//               }
//               else {
//                   return 0;
//               }
//         }
// }
=======
=======
    public void parse (String command, List<ICommand> myCommandList) throws SyntaxException,
                                                                    NoSuchCommandException {
>>>>>>> 2dd52cd1f2de0e61de004605a35c7e33cd3963fe

        int position = command.indexOf("REPEAT");
        if (position == -1) {
            myCommandList.addAll(myParser.buildMultipleCommands(myParser.split(command)));
        }
        else {
            String formerString = command.substring(0, position);
            if (formerString.length() != 0) {
                myCommandList.addAll(myParser.buildMultipleCommands(myParser.split(formerString)));
            }
            int bracketPosition = command.indexOf("[");
            int end = myParser.findRelatedBrackets(command, bracketPosition);

            String postString = null;

            if (end != command.length()) {
                postString = command.substring(end + 1);
            }

            String repeatString = command.substring(position, bracketPosition);

            List<String[]> repeatBuffer = myParser.split(repeatString);
            String recursionString = command.substring(bracketPosition + 1, end - 1);
            System.out.println("recursionString : " + recursionString);
            myCommandList.add(new Repeat(recursionString, Integer.parseInt(repeatBuffer.get(0)[1])));
            if (postString != null) {
                parse(postString, myCommandList);
            }
            // System.out.println(myCommandList.size());
        }

    }

    // addCommands(split(commands));

    /**
     * Parses to
     * 
     * @param command The user input
     * @param myCommandList list of I command
     * @return
     * @throws SyntaxException if synatx is wrong
     * @throws NoSuchCommandException if no command exists
     */
    public int parseTo (String command, List<ICommand> myCommandList)
                                                                     throws SyntaxException,
                                                                     NoSuchCommandException {

        int position = command.indexOf("TO");
        if (position == -1) {
            myCommandList.addAll(myParser.buildMultipleCommands(myParser.split(command)));
        }
        else {
            String formerString = command.substring(0, position);
            if (formerString.length() != 0) {
                myCommandList.addAll(myParser.buildMultipleCommands(myParser.split(formerString)));
            }
            int bracketPosition = command.indexOf("[");
            String commandName = command.substring(position + TO_LENGTH, bracketPosition);
            String post = command.substring(bracketPosition + 1, command.length());
            String variable = post.substring(0, post.indexOf(END_BRACKET));
            String commandsBracket = post.substring(post.indexOf("[") + 1, post.length() - 1);
            List<String[]> variables = split(variable);
            List<String[]> commandsFromBracket = split(commandsBracket);
            myCommandList.add(new To(commandName, variables, commandsFromBracket));
            myUserToCommands.put(commandName, new To(commandName, variables, commandsFromBracket));

            if (variables.get(0).length == commandsFromBracket.size()) {
                return 1;
            }
            else {
                return 0;
            }
        }

        return 0;
    }
<<<<<<< HEAD
>>>>>>> f4f2a115ec12604839ff870bb233820c00d55492
    
=======

>>>>>>> 2dd52cd1f2de0e61de004605a35c7e33cd3963fe
    /**
     * Parses IFElse statements
     * 
     * @param command the user command
     * @param myCommandList list of commands
     * @throws SyntaxException if syntax is wrong
     * @throws NoSuchCommandException if command is not existent
     */
    public void parseIfElse (String command, List<ICommand> myCommandList) throws SyntaxException,
                                                                          NoSuchCommandException {
        int position = command.indexOf("IFELSE");
        if (position == -1) {
            myCommandList.addAll(myParser.buildMultipleCommands(myParser.split(command)));
        }
        else {
            String formerString = command.substring(0, position);
            if (formerString.length() != 0) {
                myCommandList.addAll(myParser.buildMultipleCommands(myParser.split(formerString)));
            }
            int bracketPosition = command.indexOf("[");
            String value = command.substring(position + IFELSE_LENGTH, bracketPosition);
            String post = command.substring(bracketPosition + 1, command.length());
            String trueCommand = post.substring(0, post.indexOf("]"));
            String falseCommand = post.substring(post.indexOf("[") + 1, post.length() - 1);
            List<String[]> trueCommands = split(trueCommand);
            List<String[]> falseCommands = split(falseCommand);
            double doubleValue = Double.parseDouble(value);
            System.out.println(doubleValue);
            ICommand currentIfElse = new IfElse(trueCommands, falseCommands, doubleValue);
            myCommandList.add(currentIfElse);
        }
    }

    /**
     * this method can finish the process of input commands.
     * 
     * @param model the model
     * @param commands input of user
     * @throws SyntaxException Syntax exception
     * @throws NoSuchCommandException
     */
    public void process (Model model, int turtleNumber, String commands) throws SyntaxException,
                                                                        NoSuchCommandException {

        List<ICommand> myCommandList = new ArrayList<ICommand>();
<<<<<<< HEAD
        parse(commands,myCommandList);
        //parseTo(commands , myCommandList);
=======
        parseTo(commands, myCommandList);
>>>>>>> 2dd52cd1f2de0e61de004605a35c7e33cd3963fe
        System.out.println(myCommandList.size());
        for (ICommand ic : myCommandList) {

            System.out.println(myUserToCommands.size());

            System.out.println("list size :" + myCommandList.size());
            ic.move(model, turtleNumber);
        }

    }

    /**
     * Makes a variable from user input
     * 
     * @param model the slogo model
     * @param currentCommand current command
     * @throws SyntaxException syntax exception
     */
    public void makeVariable (Model model, String[] currentCommand) throws SyntaxException {

        if (currentCommand.length < 2) { throw new SyntaxException(); }
        String name = currentCommand[0];
        String value = currentCommand[1];
        if (!(myStrPattern.matcher(name).matches() && myNumPattern.matcher(value).matches())) {
            throw new SyntaxException();
        }
        else {
            model.addVariable(name, Double.parseDouble(value));
        }
    }

}
