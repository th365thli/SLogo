package behavior;

import slogo.Model;
import exceptions.ParameterException;
import exceptions.SyntaxException;


/**
 * an abstract class for one parameter command
 * 
 * @author Richard Yang
 * 
 */
public abstract class OneParameterCommand implements ICommand {

    private double myValue;

    @Override
    public double move (Model model, int turtleNumber) {
        return 0;
    }

    @Override
    public void initialize (String[] information, Model model) throws SyntaxException {
        if (information.length != 1) { 
            throw new ParameterException("ParameterException"); 
        //} else if (!myNumPattern.matcher(information[0]).matches()) {
            //throw new ParameterException("ParameterException");
        }
        myValue = Double.parseDouble(information[0]);
    }

    /**
     * getter for myvalue
     * 
     * @return
     */
    public double getMyValue () {
        return myValue;
    }

    /**
     * inverse parameter
     */
    public void inverseMyValue () {
        myValue = -myValue;
        System.out.println("Did it inverse correctly? " + myValue);
    }

}
