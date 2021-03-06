package slogo;

import java.util.ArrayList;
import java.util.List;
import behavior.ICommand;


/**
 * 
 * @author Richard Yang & Jerry Li
 * 
 */
public class Interpreter {

    private Parser myParser;

    /**
     * Constructs an interpreter
     */
    public Interpreter () {
        myParser = new Parser();
    }

    /**
     * parse commands
     * 
     * @param command user input
     * @param myCommandList list of commands
     * @param model model
     * @throws Exception exception
     */
    public void parse (String command, List<ICommand> myCommandList,
                       Model model) throws Exception {

        myParser.parse(command, myCommandList, model);

    }

    /**
     * process user input
     * 
     * @param model model
     * @param turtleNumber turtle number
     * @param commands commands
     * @throws Exception exception
     */
    public void process (Model model, int turtleNumber, String commands) throws Exception {
        List<ICommand> myCommandList = new ArrayList<ICommand>();

        parse(commands, myCommandList, model);
        for (ICommand ic : myCommandList) {
            ic.move(model, turtleNumber);
        }

    }

}
