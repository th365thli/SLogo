package behavior;

import exceptions.SyntaxException;
import object.Turtle;


/**
 * 
 * @author Richard Yang
 * 
 */

public class And implements ICommand {

    @Override
    public double move (Turtle turtle, double[] parameters) throws SyntaxException {
        if (parameters.length != 2) {
            throw new SyntaxException();
        }
        else if (!((parameters[0] == 1 || parameters[0] == 0) 
                && (parameters[1] == 1 || parameters[1] == 0))) {
            throw new SyntaxException();
        }

        return ((parameters[0] == 1) && (parameters[1] == 1)) ? 1 : 0;
    }

}