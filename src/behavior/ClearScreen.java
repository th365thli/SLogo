package behavior;

import exceptions.SyntaxException;
import slogo.Model;
import view.Canvas;



/**
 * Clears the Screen
 * 
 * @author Richard Yang
 * 
 */

public class ClearScreen extends Home {

    @Override
    public double move (Model model, int turtleNumber) throws SyntaxException {

        model.getMyTurtle(turtleNumber).clearTrail();
        model.getMyTurtle(turtleNumber).initialize();
        return Math.sqrt(Math.pow(model.getMyTurtle(turtleNumber).getX() -
                                          Canvas.TURTLE_AREA_SIZE.getWidth() / 2, 2) +
                         Math.pow(model.getMyTurtle(turtleNumber).getY() -
                                          Canvas.TURTLE_AREA_SIZE.getHeight() / 2, 2));
    }

}
