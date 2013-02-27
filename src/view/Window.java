package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.JPanel;
import javax.swing.Timer;
import object.Turtle;
import utilities.Controller;


/**
 * 
 * @author Leonard and Alan
 * 
 */
public class Window extends JPanel {

    public static final int FRAMES_PER_SECOND = 25;
    public static final int ONE_SECOND = 1000;
    public static final int DEFAULT_DELAY = ONE_SECOND / FRAMES_PER_SECOND;

    public static final String DEFAULT_RESOURCE_PACKAGE = "resources.";
    public static final String USER_DIR = "user.dir";
    public ResourceBundle myResources;

    private static final long serialVersionUID = 1L;

    
    public Window (Dimension size, String language) {

        setPreferredSize(size);
        setSize(size);
        setFocusable(true);
        requestFocus();
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);

    }

    @Override
    public void paintComponent (Graphics pen) {
        pen.setColor(Color.WHITE);
        pen.fillRect(0, 0, getSize().width, getSize().height);
        
    }

    public void update (Graphics2D pen, Turtle turtle){
        turtle.paint(pen);
    }
}
