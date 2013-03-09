package behavior.flow;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import behavior.ICommand;
import slogo.Model;
import slogo.Parser;
import exceptions.NoSuchCommandException;
import exceptions.SyntaxException;

public class Repeat implements ICommand {

    private List<ICommand> myCommands = new ArrayList<ICommand>();
    private Parser myParser = new Parser();
    private int myRunTime;
    private ResourceBundle myResources;
    
    private static final String DEFAULT_RESOURCE_PACKAGE = "resources.";
    
    
    public Repeat(String subCommands, int time) throws NoSuchCommandException, SyntaxException{
        
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "commands");
       
        myRunTime = time ;
         
        myParser.parseOneBracket(subCommands, myCommands);
        
        
    }
    
    
    public void addCommands(List<String[]> commands) throws SyntaxException, NoSuchCommandException{
        for(String[] str : commands){
            if( !myResources.containsKey(str[0])) {
                System.out.print(str[0]);
                throw new SyntaxException();
            }
            else{
                myCommands.add( myParser.buildCommand(str));
            
            }
        }
    }
    
  
    
    
    @Override
    public double move (Model model, int turtleNumber) throws SyntaxException {
        double returnValue = 0;
        int time = myRunTime;
        while (time != 0){
            for(ICommand command : myCommands){
                returnValue = command.move(model, turtleNumber);
            }
            time -- ;
        }
        return returnValue;
    }



    @Override
    public void initialize (String[] information, Model model) throws SyntaxException {
        // TODO Auto-generated method stub
        
    }

}