package behavior;

import object.Turtle;


public class SetHeading implements ICommand {

    @Override
    public void move (Turtle turtle, double distanceOrAngle) throws Exception {
        turtle.setMyAngle(distanceOrAngle);
    }

    @Override
    public void move (Turtle turtle, double X, double Y) throws Exception {
        // TODO Auto-generated method stub
        
    }

   

}
