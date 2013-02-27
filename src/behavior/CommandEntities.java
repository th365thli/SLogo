package behavior;

import java.util.HashMap;
import java.util.Map;
import utilities.Model;


/**
 * 
 * @author Richard Yang
 * 
 */

public class CommandEntities {

    public static final String FORWARD = "FD";

    public static final String BACK = "BK";

    public static final String LEFT = "LT";

    public static final String RIGHT = "RT";

    public static final String SETHEADING = "SETH";

    public static final String TOWARDS = "TOWARDS";

    public static final String SETXY = "SETXY";

    public static final String PENDOWN = "PD";

    public static final String PENUP = "PU";

    public static final String SHOWTURTLE = "ST";

    public static final String HIDETURTLE = "HT";

    public static final String HOME = "HOME";

    public static final String CLEARSCREEN = "CS";

    private Map<String, ICommand> myCommands ;


    public CommandEntities () {
        myCommands = new HashMap<String, ICommand>() ;
    }

    public void addCommand (String command, ICommand newCommand) {
        myCommands.put(command, newCommand);
    }

    public void removeCommand (String command) {
        myCommands.remove(command);
    }

    public void initialize () {
        addCommand(FORWARD, new Forward());
        addCommand(BACK, new Back());
        addCommand(LEFT, new Left());
        addCommand(RIGHT, new Right());
        addCommand(SETHEADING, new SetHeading());
        addCommand(TOWARDS, new Towards());
        addCommand(SETXY, new SetXY());
        addCommand(PENDOWN, new Forward());
        addCommand(PENUP, new PenUp());
        addCommand(SHOWTURTLE, new ShowTurtle());
        addCommand(HIDETURTLE, new HideTurtle());
        addCommand(CLEARSCREEN, new ClearScreen());
    }

    public ICommand getCommand (String command) {
        return myCommands.get(command);
    }

    public void doCommand (Model model , String commandName, double distanceOrAngle) {
        ICommand command = getCommand(commandName);
        try {
            command.move(model.getMyTurtle(), distanceOrAngle);
        }
        catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void doCommand (Model model , String commandName, double x, double y) {
        ICommand command = getCommand(commandName);
        try {
            command.move(model.getMyTurtle(), x, y);
        }
        catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void doCommand (Model model , String commandName) {
        ICommand command = getCommand(commandName);
        try {
            command.move(model.getMyTurtle());
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
