import java.awt.Dimension;
<<<<<<< HEAD
import slogo.Controller;
import view.Canvas;
import view.Window;

=======
import utilities.Controller;
import view.Window;
>>>>>>> b155bdfe9d69094e0e895d7dc6ae4e2b9e4e61ed


/**
 * 
 * @author Jerry
 * 
 */
public class Main {

    /**
     * default turtle area size
     */
    public static final Dimension TURTLE_AREA_SIZE = new Dimension(800, 500);

    /**
     * default display area size
     */
    public static final Dimension DISPLAY_AREA_SIZE = new Dimension(200, 500);

    /**
     * default command area size
     */
    public static final Dimension COMMAND_AREA_SIZE = new Dimension(1000, 40);
    /**
     * defaullt total canvas size
     */
    public static final Dimension SIZE = new Dimension(1100, 700);
    /**
     * Title of game
     */
    public static final String TITLE = "SLOGO";

    /**
     * @param args nothing
     */
    public static void main (String[] args) {

        Controller myController = new Controller();
<<<<<<< HEAD
        new Canvas(myController);
=======
        // new Canvas(myController);
>>>>>>> b155bdfe9d69094e0e895d7dc6ae4e2b9e4e61ed
        new Window(myController);

        // myController.processUserInput(0, "IF 50 [ FD 10 ]");
        // System.out.println(myController.getModel(0).getMyTurtle().getX());
        // System.out.println(myController.getModel(0).getMyTurtle().getY());
        // System.out.println(myController.getModel(0).getMyTurtle().getMyAngle());

    }

}
