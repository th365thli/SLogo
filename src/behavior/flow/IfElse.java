package behavior.flow;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import slogo.Model;
import slogo.Parser;
import behavior.ICommand;
import exceptions.NoSuchCommandException;
import exceptions.ParameterException;
import exceptions.SyntaxException;


/**
 * Implements IFELSE statement
 * 
 * @author Jerry Li
 * 
 */
public class IfElse implements ICommand {

    /**
     * Resources for commands
     */
    private static final String DEFAULT_RESOURCE_PACKAGE = "resources.";
    private List<String[]> myStringTrueCommands;
    private List<String[]> myStringFalseCommands;
    private List<ICommand> myTrueCommands;
    private List<ICommand> myFalseCommands;
    private Parser myParser = new Parser();
    private double myValue;
    private double myFinalValue;
    private String myRecurse;
    
    

    private ResourceBundle myResources;


    /**
     * Constructs the command
     * 
     * @param trueCommands commands in true bracket
     * @param falseCommands commands in false bracket
     * @param value value
     * @throws NoSuchCommandException
     * @throws SyntaxException
     */
    public IfElse (String command,
                   Model model)
                               throws NoSuchCommandException, SyntaxException {
        
        myRecurse = parse(command);
//        myStringTrueCommands = trueCommands;
//        myStringFalseCommands = falseCommands;
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "commands");
        myTrueCommands = new ArrayList<ICommand>();
        myFalseCommands = new ArrayList<ICommand>();
        
        map(myStringTrueCommands, myStringFalseCommands, model);
    }
    
    public String getRecurse() {
        return myRecurse;
    }
    
    public String parse(String command) {
        int position = command.indexOf("IFELSE");
        int bracketPosition = command.indexOf("[");
        String value = command.substring(position + 7, bracketPosition);
        String post = command.substring(bracketPosition + 1, command.length());
        String trueCommand = post.substring(0, post.indexOf("]"));
        String temp = post.substring(post.indexOf("[") + 1, post.length());
        String falseCommand = temp.substring(0, temp.indexOf("]"));
        String recurse = temp.substring(temp.indexOf("]") + 1, temp.length());

        myStringTrueCommands = myParser.split(trueCommand);
        myStringFalseCommands = myParser.split(falseCommand);
        myValue = Double.parseDouble(value);
        System.out.println(myValue);
        return recurse;
       
    }

    /**
     * return final value
     * 
     * @return
     */
    public double getMyFinalValue () {
        return myFinalValue;
    }

    /**
     * Map to respective lists
     * 
     * @param trueCommands true commands
     * @param falseCommands false commands
     * @throws NoSuchCommandException
     * @throws SyntaxException
     */
    public void map (List<String[]> trueCommands, List<String[]> falseCommands, Model model)
                                                                                            throws NoSuchCommandException,
                                                                                            SyntaxException {
        myTrueCommands = buildCommands(trueCommands, model);
        myFalseCommands = buildCommands(falseCommands, model);
    }

    /**
     * Build ICommands from list of string commands
     * 
     * @param commands commands
     * @return
     * @throws NoSuchCommandException
     * @throws SyntaxException
     */
    public List<ICommand> buildCommands (List<String[]> commands, Model model)
                                                                              throws NoSuchCommandException,
                                                                              SyntaxException {
        List<ICommand> theCommands = myParser.buildMultipleCommands(commands, model);
        return theCommands;
    }

    /**
     * Moves the turtle
     * 
     * @param model the model
     * @param turtleNumber the turtle
     * @throws SyntaxException SyntaxException
     * 
     */
    @Override
    public double move (Model model, int turtleNumber) throws SyntaxException {
        myFinalValue = move(model, turtleNumber, myValue);
        return myFinalValue;
    }

    /**
     * Moves turtle with value
     * 
     * @param model the model
     * @param turtleNumber the turtle
     * @param value the value
     * @return
     * @throws SyntaxException SyntaxException
     */
    public double move (Model model, int turtleNumber, double value) throws SyntaxException {
        if (value == 0) {
            for (int i = 0; i < myFalseCommands.size(); i++) {
                myFalseCommands.get(i).move(model, turtleNumber);
            }
            double myLastValue =
                    Double.parseDouble(myStringTrueCommands.get(myStringTrueCommands.size() - 1)[1]);
            return myLastValue;
        }
        else {
            for (int i = 0; i < myTrueCommands.size(); i++) {
                myTrueCommands.get(i).move(model, turtleNumber);
            }
            double myLastValue =
                    Double.parseDouble(myStringFalseCommands.get(myStringFalseCommands.size()
                            - 1)[1]);
            return myLastValue;
        }

    }

    @Override
    public void initialize (String[] information, Model model) throws SyntaxException {

    }

}
