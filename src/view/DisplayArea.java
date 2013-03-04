package view;

import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import object.Turtle;



/**
 * 
 * @author Leonard
 * 
 */
public class DisplayArea extends Window {

    public static final Dimension DEFAULT_AREA_SIZE = new Dimension(200, 600);

    private static final long serialVersionUID = 1L;
    private JTextArea myTextArea;
    private static final int FIELD_SIZE = 30;
    private List<Turtle> myTurtle;
    private MouseListener myMouseListener;

    public DisplayArea (Dimension size, List<Turtle> myTurtles) {
        super(size, "English");
        myTurtle = myTurtles;
        makeListeners();

        add(makeDisplay(), BorderLayout.CENTER);
        setVisible(true);
        revalidate();

    }

    private JComponent makeDisplay () {
        myTextArea = new JTextArea(FIELD_SIZE, 18);
        myTextArea.addMouseListener(myMouseListener);

        return new JScrollPane(myTextArea);
    }

    public void update () {
    }

    private void makeListeners () {

        myMouseListener = new MouseListener() {
            @Override
            public void mouseClicked (MouseEvent e) {
                echo("clicked", e);
            }

            @Override
            public void mouseEntered (MouseEvent e) {
            }

            @Override
            public void mouseExited (MouseEvent e) {
            }

            @Override
            public void mousePressed (MouseEvent e) {
            }

            @Override
            public void mouseReleased (MouseEvent e) {
            }
        };

    }

    private void echo (String s, MouseEvent e) {
        showMessage("x coordinate: " + 
                        myTurtle.get(0).toString(myTurtle.get(0).getX()));
        showMessage("y coordinate: " + 
                        myTurtle.get(0).toString(myTurtle.get(0).getY()));
        showMessage("turtle angle: " + 
                        myTurtle.get(0).toString(myTurtle.get(0).getMyAngle()));
        showMessage("\n");
    }

    public void showMessage (String message) {
        myTextArea.append(message + "\n");
        myTextArea.setCaretPosition(myTextArea.getText().length());
    }

}
