package behavior;

import object.Turtle;
/**
 * 
 * @author Richard Yang
 *
 */

public class Home implements ICommand {

    @Override
    public void move (Turtle turtle, double distanceOrAngle) throws Exception {
       
    }

    @Override
    public void move (Turtle turtle, double X, double Y) throws Exception {

    }

    @Override
    public void move (Turtle turtle) throws Exception {
        turtle.initialize();

    }

}
