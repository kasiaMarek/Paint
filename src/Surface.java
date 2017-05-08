import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.*;

/**
 * A class surface containing main area to draw and button panel.
 * Contains information:
 * <ul>
 * <li> List of shapes seen in drawing area
 * <li> List of colors for shapes
 * <li> A temporary not-filled shape that is seen when a user draws a new shape
 * <li> pol: a red circle which indicates the beginning of a polygon shape
 * <li> Button panel
 * <li> Mode: ELLIPSE, RECTANGLE, SELECT or POLYGON
 * <li> Number of index of the shape chosen by user to edit, when no shape chosen set to -1
 * </ul>
 * @author Katarzyna Marek
 * @version 1.0
 */
public class Surface extends JPanel {
    static private ArrayList<Shape> listOfShapes = new ArrayList<>();
    static private ArrayList<Color> listOfColors = new ArrayList<>();
    static private GeneralPath path;
    static private Circle pol;
    static private ButtonPanel buttonPanel = new ButtonPanel();
    static private Mode mode = Mode.ELLIPSE;
    static private int chosen = -1;
    /**
     * Constructor for surface. Adds button panel and mouse listeners.
     */
    public Surface() {
        add(buttonPanel, BorderLayout.NORTH);
        addMouseListener(new Mouse() {});
        addMouseWheelListener(new MouseWheel() {});
        addMouseMotionListener(new MouseMove() {});
    }
    /**
     * A getter for list of shapes.
     * @param i index of wanted shape
     * @return shape with i index
     */
    static Shape getShape(int i) {
        return listOfShapes.get(i);
    }
    /**
     * Adds new shape to the list of shapes and color for it on list of colors. And displays it in drawing area.
     * @param shape shape that should be added
     * @param color color of the shape that should be added
     */
    static void addShape(Shape shape, Color color) {
        listOfShapes.add(0, shape);
        listOfColors.add(0, color);
        GUI.doRepaint();
    }
    /**
     * Removes all the shapes from the list
     */
    static void removeShapes() {
        int size = Surface.listOfShapes.size();
        for(int i = 0; i < size; i++) {
            Surface.listOfShapes.remove(0);
            Surface.listOfColors.remove(0);
        }
        chosen = -1;
        path = null;
    }
    /**
     * Removes chosen shape from the list
     * @param i index of chosen shape
     */
    static void deleteShape(int i) {
        Surface.listOfShapes.remove(i);
        Surface.listOfColors.remove(i);
        chosen = -1;
        path = null;
        GUI.doRepaint();
    }
    /**
     * Moves up chosen shape on the list
     * @param i index of chosen shape
     */
    static void moveUp(int i) {
        if(i > 0 && i < listOfShapes.size()) {
            Shape shape = listOfShapes.get(i);
            Color color = listOfColors.get(i);
            listOfShapes.set(i, listOfShapes.get(i-1));
            listOfColors.set(i, listOfColors.get(i-1));
            listOfShapes.set(i-1, shape);
            listOfColors.set(i-1, color);
            chosen += -1;
            GUI.doRepaint();
        }
    }
    /**
     * Moves down chosen shape on the list
     * @param i index of chosen shape
     */
    static void moveDown(int i) {
        if(i < listOfShapes.size()-1 && i >= 0) {
            Shape shape = listOfShapes.get(i);
            Color color = listOfColors.get(i);
            listOfShapes.set(i, listOfShapes.get(i+1));
            listOfColors.set(i, listOfColors.get(i+1));
            listOfShapes.set(i+1, shape);
            listOfColors.set(i+1, color);
            chosen += 1;
            GUI.doRepaint();
        }
    }
    /**
     * Gives number of the shapes.
     * @return Number of the shapes on the list. Seen in drawing area.
     */
    static int getNumberOfShapes(){return listOfShapes.size();}
    /**
     * A getter for list of colors.
     * @param i index of wanted color
     * @return color of the shape with i index
     */
    static Color getColor(int i) {
        return listOfColors.get(i);
    }
    /**
     * Getter for chosen
     * @return Number of index on list of shapes of the shape that is currently chosen.
     */
    static int getChosen() {
        return chosen;
    }
    /**
     * Chooses the shape that was clicked and writes its index on list to chosen variable.
     * @param x x coordinate of the click
     * @param y y coordinate of the click
     */
    static void choose(float x, float y){
        for(Shape c: listOfShapes) {
            if(c.contains(x, y)) {
                chosen = listOfShapes.indexOf(c);
                path = new GeneralPath(c);
                GUI.doRepaint();
                return;
            }
        }
        path = null;
        GUI.doRepaint();
        chosen = -1;
    }
    /**
     * Sets color.
     * @param color chosen color
     */
    public static void setColor(Color color) {
        if(chosen >= 0)
            listOfColors.set(chosen, color);
        GUI.doRepaint();
    }
    /**
     * Moves chosen shape.
     * @param s chosen shape
     * @param w difference in x coordinates
     * @param h difference in y coordinates
     */
    static void moveShape(Shape s, double w, double h) {
        AffineTransform a = new AffineTransform(1,0,0,1,w,h);
        Shape shape = new GeneralPath(s).createTransformedShape(a);
        listOfShapes.set(chosen, shape);
        path = new GeneralPath(shape);
        GUI.doRepaint();
    }
    /**
     * Gets the x coordinate of a shape center.
     * @param shape chosen shape
     * @return x coordinate of shape center
     */
    private static double getXCenter(Shape shape){
        Rectangle2D rec = shape.getBounds2D();
        return rec.getX()+rec.getWidth()/2;
    }
    /**
     * Gets the y coordinate of a shape center.
     * @param shape chosen shape
     * @return y coordinate of shape center
     */
    private static double getYCenter(Shape shape){
        Rectangle2D rec = shape.getBounds2D();
        return rec.getY()+rec.getHeight()/2;
    }
    /**
     * Resizes chosen shape.
     * @param s scale
     */
    static void resizeShape(double s) {
        AffineTransform a = new AffineTransform(s,0,0,s,0,0);
        Shape shape = new GeneralPath(listOfShapes.get(chosen)).createTransformedShape(a);
        AffineTransform b = new AffineTransform(1,0,0,1, getXCenter(Mouse.chosenTemp) - getXCenter(shape),
                getYCenter(Mouse.chosenTemp) - getYCenter(shape));
        shape = new GeneralPath(shape).createTransformedShape(b);
        listOfShapes.set(chosen, shape);
        path = new GeneralPath(shape);
        GUI.doRepaint();
    }

    /**
     * Sets mode.
     * @param m mode
     */
    static void setMode(Mode m) {
        mode = m;
    }

    static Mode getMode() {
        return mode;
    }
    /**
     * Sets path from shape.
     * @param shape shape that should be set as path
     */
    static void addPath(Shape shape) {
        path = new GeneralPath(shape);
        GUI.doRepaint();
    }
    /**
     * Sets path.
     * @param p shape that should be set as path
     */
    static void setPath(GeneralPath p) {
        path = p;
        GUI.doRepaint();
    }

    /**
     * Sets pol, red circle that indicates the beginning of a new polygon shape.
     * @param c circle
     */
    static void setPol(Circle c) {
        pol = c;
        GUI.doRepaint();
    }
    /**
     * Gets pol.
     * @return circle
     */
    static Circle getPol() {
        return pol;
    }
    /**
     * Erases information needed when a polygon is being drawn.
     */
    static void setPathToNull() {
        path = null;
        pol = null;
        chosen = -1;
        GUI.doRepaint();
        Mouse.setIsFirstClick();
    }
    /**
     * Responsible for painting the shapes seen in drawing area.
     * @param g graphics
     */
    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        for(int i = listOfColors.size()-1; i >= 0; i--) {
            g2d.setPaint(listOfColors.get(i));
            g2d.fill(listOfShapes.get(i));
        }

        if(!(path == null)) {
            g2d.setPaint(Color.red);
            g2d.draw(path);
        }
        if(!(pol == null)) {
            g2d.setPaint(new Color(200, 0, 0));
            g2d.fill(pol);
        }
    }
    /**
     * Paints the surface
     * @param g graphics
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        doDrawing(g);
    }

}
