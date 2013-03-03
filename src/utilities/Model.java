package utilities;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import object.Turtle;
import view.DisplayArea;
import view.TurtleArea;


/**
 * 
 * @author Richard Yang
 * 
 */
public class Model {

    public static final Dimension TURTLE_AREA_SIZE = new Dimension(800, 500);
    public static final Dimension DISPLAY_AREA_SIZE = new Dimension(200, 500);
    public static final Dimension SIZE = new Dimension(1100, 700);
    public static final String TITLE = "SLOGO";

    private Turtle myTurtle;

    private DisplayArea myDisplayArea;

    private TurtleArea myTurArea;

    private Controller myController;
    
    private Map<String , Double> myVariables ;

    public Model (Controller controller) {
        myController = controller;
        myTurtle = new Turtle();
        myDisplayArea = new DisplayArea(DisplayArea.DEFAULT_AREA_SIZE, myTurtle);
        myTurArea = new TurtleArea(TurtleArea.DEFAULT_AREA_SIZE, myTurtle);
        myVariables = new HashMap<String , Double>();

    }

    public void update () {
        System.out.println("update!");
        myDisplayArea.update();
        myTurArea.update();
    }

    public Turtle getMyTurtle () {
        return myTurtle;
    }
    
    public void showMessage(String message){
        myDisplayArea.showMessage(message);
    }

    public void addVariable (String name , Double value){
        myVariables.put(name, value);
    }
    
    public void removeVariable(String name){
        myVariables.remove(name);
    }
    
    public double getVariableValue(String name){
        return myVariables.get(name);
    }
    
    public void clearVariable(){
        myVariables.clear();
    }
    
}
