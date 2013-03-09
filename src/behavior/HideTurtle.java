package behavior;

import slogo.Model;
import exceptions.SyntaxException;


/**
 * 
 * @author Richard Yang
 * 
 */
public class HideTurtle extends NoParameterCommand {

    @Override
    public double move (Model model, int turtleNumber) throws SyntaxException {

        model.getMyTurtle(turtleNumber).setInvisible();
        return 0;

    }

}
