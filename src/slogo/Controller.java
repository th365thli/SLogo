package slogo;

import java.awt.Dimension;
import util.Location; 
import java.util.ArrayList;
import java.util.List;
import object.Turtle;
import view.Canvas;
import exceptions.NoSuchCommandException;
import exceptions.SyntaxException;


/**
 * 
 * @author Richard Yang & Jerry Li
 * 
 */
public class Controller {

    /**
     * Default Turtle area size
     */
    public static final Dimension TURTLE_AREA_SIZE = new Dimension(800, 500);
    /**
     * Default Display area size
     */
    public static final Dimension DISPLAY_AREA_SIZE = new Dimension(200, 500);

    private List<Model> myModels = new ArrayList<Model>();

    private Interpreter myInterpreter;
    private Factory myFactory;
    private Canvas myView;

    /**
     * Initialize a model view controller
     */
    public Controller () {

        myFactory = new Factory(this);
        addModel();
        myInterpreter = new Interpreter();
        for (int i = 0; i < myModels.size(); i++) {
            myFactory.createTurtle(i);
        }
        myView = new Canvas(this);
    }

    /**
     * Return factory
     * 
     * @return
     */
    public Factory getFactory () {
        return myFactory;
    }

    /**
     * Update display areas
     */
    public void update () {
        myView.update();
    }

    /**
     * Process user input
     * 
     * @param seq sequence
     * @param string string
     */
    public void processUserInput (int seq, String string) {

        try {
            myInterpreter.process(myModels.get(seq), string);
        }
        catch (SyntaxException e) {
            // myModels.get(seq).showMessage("Syntax Error, please check your commands");
            System.out.println("Syntax Error");
        }
        catch (NoSuchCommandException e) {
            // myModels.get(seq).showMessage("Syntax Error, please check your commands");
            System.out.println("No such command");
        }
        List<Turtle> myTurtles= myModels.get(seq).getMyTurtles();
        for (Turtle t:myTurtles){
        	checkBounds(t);
        }
        // update view
        update();
    }
    
    public static void checkBounds(Turtle t){
    	double x=t.getX();
    	double y=t.getY();
    	double xMargin=t.DEFAULT_SIZE.getWidth()/2;
    	double yMargin=t.DEFAULT_SIZE.getHeight()/2;
    	if (x>TURTLE_AREA_SIZE.getWidth()-xMargin){
    		x=TURTLE_AREA_SIZE.getWidth()-xMargin; 
    	}
    	if (y>TURTLE_AREA_SIZE.getHeight()-yMargin){
    		y=TURTLE_AREA_SIZE.getHeight()-yMargin; 
    	}
    	if (x<xMargin){
    		x=xMargin;
    	}
    	if (y<yMargin){
    		y=yMargin; 
    	}
    	t.setCenter(new Location (x,y));
    	
    }
    /**
     * Add a model
     */
    public void addModel () {
        myModels.add(new Model(this, myModels.size()));
    }

    /**
     * Add a specific model
     * 
     * @param model the model to add
     */
    public void addModel (Model model) {
        myModels.add(model);
    }

    /**
     * remove model at a index
     * 
     * @param seq the index
     */
    public void removeModel (int seq) {
        myModels.remove(seq);
    }

    /**
     * Remove a specific model
     * 
     * @param model the model to remove
     */
    public void removeModel (Model model) {
        myModels.remove(model);
    }

    /**
     * Return a model at an inex
     * 
     * @param seq the index
     * @return the model
     */
    public Model getModel (int seq) {
        return myModels.get(seq);
    }

    /**
     * Return the list of models
     * 
     * @return list of models
     */
    public List<Model> getMyModels () {
        return myModels;
    }

    /**
     * Return interpreter
     * 
     * @return
     */
    public Interpreter getMyInter () {
        return myInterpreter;
    }

    /**
     * return turtle
     * 
     * @return
     */
    public Turtle getMyTurtle () {
        return myModels.get(0).getMyTurtle(0);
    }

    /**
     * Returns list of turtles
     * 
     * @return
     */
    public List<Turtle> getMyTurtles () {
        return myModels.get(0).getMyTurtles();
    }
}
