package behavior;

import slogo.Model;
import exceptions.SyntaxException;


/**
 * 
 * @author Richard Yang
 * 
 */

public class PenDown extends NoParameterCommand {

    public static final int PARAMETER_NUMBER = 1;

    @Override
    public double move (Model model, int turtleNumber) throws SyntaxException {

        model.getMyTurtle(turtleNumber).leftTrail();
        return 1;

    }

}
