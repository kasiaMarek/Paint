import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.GeneralPath;

/**
 * A class handling simple mouse actions.
 * Contains information:
 * <ul>
 * <li> Color chooser
 * <li> X coordinate of where mouse was pressed.
 * <li> Y coordinate of where mouse was pressed.
 * <li> Temporary circle for editing circle when a new circle is being drawn
 * <li> Temporary rectangle for editing circle when a new rectangle is being drawn
 * <li> Temporary path for editing circle when a new polygon is being drawn
 * <li> Chosen shape used while editing shape in SELECT mode
 * <li> True false statement used while drawing a polygon. True before a start point for a new polygon is chosen by user.
 * </ul>
 * @author Katarzyna Marek
 * @version 1.0
 */
public class Mouse extends MouseAdapter {
    private ColorChooser c = new ColorChooser();
    static int sy;
    static int sx;
    static Circle circle;
    static Rectangle rectangle;
    private GeneralPath path = new GeneralPath();
    static Shape chosenTemp;
    private static boolean isFirstClick = true;
    /**
     * Creates mouse adapter.
     */
    Mouse() {}
    /**
     * Setter for isFirstClick to true.
     */
    static void setIsFirstClick() {
        isFirstClick = true;
    }
    /**
     * Displays color chooser.
     */
    private void actRightMouseButton() {
        c.setVisible(true);
    }
    /**
     * Draws a new polygon shape.
     */
    private void mousePressedInPolygonMode(){
        if(isFirstClick) {
            path = new GeneralPath();
            path.moveTo(sx, sy);
            Surface.setPath(path);
            Surface.setPol(new Circle(sx-5, sy-5, 10 , 10));
            isFirstClick = false;
        }
        else {
            if(Surface.getPol().getBounds2D().contains(sx, sy)) {
                path.closePath();
                Surface.addShape(path,Color.pink);
                Surface.setPathToNull();
            } else {
                path.lineTo(sx, sy);
                Surface.setPath(path);
            }
        }
    }
    /**
     * Occurs when mouse is pressed in select mode. Chooses the index of shape that was clicked by a user or -1 if
     * no shape was chosen.
     * If a shape was chosen it remembers this shape in ChosenTemp variable
     * If color chooser is opened it paints the chosen shape with chosen color.
     * Opens color chooser when right mouse button pressed.
     * @param e event that occurs when mouse pressed
     */
    private void mousePressedInSelectMode(MouseEvent e){
        Surface.choose(sx, sy);
        if(Surface.getChosen() >= 0)
            chosenTemp = Surface.getShape(Surface.getChosen());
        if(c.isVisible()) {
            Surface.setColor(c.getColor());
        }
        if(SwingUtilities.isRightMouseButton(e))
            actRightMouseButton();
    }
    /**
     * Function handling actions in different modes when mouse is pressed.
     * Gets x and y coordinates of where the mouse was pressed.
     * ELLIPSE mode: creates a new circle.
     * RECTANGLE mode: creates a new rectangle.
     * POLYGON mode: calls out mousePressedInPolygonMode function
     * SELECT mode: calls out mousePressedInSelectMode function
     * @param e event that occurs when mouse pressed
     */
    public void mousePressed(MouseEvent e) {
        sy = e.getY();
        sx = e.getX();
        switch(Surface.getMode()) {
            case ELLIPSE:
                circle = new Circle(sx,sy,0,0);
                Surface.addPath(circle);
                break;
            case RECTANGLE:
                rectangle = new Rectangle(sx,sy,0,0);
                Surface.addPath(rectangle);
                break;
            case POLYGON:
                mousePressedInPolygonMode();
                break;
            case SELECT:
                mousePressedInSelectMode(e);
        }
    }
    /**
     * Adds a shape to the list of shapes with color to it. Clears path variables so they can be used again.
     */
    private void paintNewShape(Shape shape, Color color) {
        Surface.addShape(shape, color);
        path = null;
        Surface.setPath(null);
        GUI.doRepaint();
    }
    /**
     * Function handling actions in different modes when mouse is released.
     * Adds the ellipse painted by user to the list of shapes in ELLIPSE mode.
     * Adds the rectangle painted by user to the list of shape in RECTANGLE mode.
     * Gets chosen shape if chosen (the index number of clicked shape) shows that a shape was chosen.
     * @param e event that occurs when mouse released
     */
    public void mouseReleased(MouseEvent e) {
        if(Surface.getMode() == Mode.ELLIPSE)
            paintNewShape(circle, Color.black);
        if(Surface.getMode() == Mode.RECTANGLE)
            paintNewShape(rectangle, Color.cyan);
        if(Surface.getChosen() >= 0)
            chosenTemp = Surface.getShape(Surface.getChosen());
    }
}
