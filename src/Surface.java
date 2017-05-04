import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Created by kasia on 03.05.2017.
 */
public class Surface extends JPanel {
    static ArrayList<Shape> listOfShapes = new ArrayList<>();
    static ArrayList<Color> listOfColors = new ArrayList<>();
    static private GeneralPath path;
    static private Circle pol;
    static private ButtonPanel buttonPanel = new ButtonPanel();
    static private Mode mode = Mode.ELIPSE;
    static private int chosen = -1;

    public static int getChosen() {
        return chosen;
    }

    public static void setChosen(int chosen) {
        Surface.chosen = chosen;
    }

    public Surface() {
        add(buttonPanel, BorderLayout.NORTH);
        addMouseListener(new Mouse() {});
        addMouseWheelListener(new MouseWheel() {});
        addMouseMotionListener(new MouseMove() {});
    }

    public static void addPath(Shape shape) {
        path = new GeneralPath(shape);
        GUI.doRepaint();
    }

    public static void setColor(Color color) {
        if(chosen >= 0)
            listOfColors.set(chosen, color);
    }


    public static void moveShape(Shape s, double w, double h) {
        AffineTransform a = new AffineTransform(1,0,0,1,w,h);
        Shape shape = new GeneralPath(s).createTransformedShape(a);
        listOfShapes.set(chosen, shape);
        GUI.doRepaint();
    }

    public static void resizeShape(double s) {
        AffineTransform a = new AffineTransform(s,0,0,s,0,0);
        Shape shape = new GeneralPath(listOfShapes.get(chosen)).createTransformedShape(a);
        listOfShapes.set(chosen, shape);
        GUI.doRepaint();
    }
    public static void setMode(Mode m) {
        mode = m;
    }

    public static Mode getMode() {
        return mode;
    }

    public static GeneralPath getPath() {
        return path;
    }

    public static void setPath(GeneralPath p) {
        path = p;
    }

    public static void setPol(Circle c) {
        pol = c;
    }

    public static Circle getPol() {
        return pol;
    }

    public static void setPathToNull() {
        path = null;
        pol = null;
        GUI.doRepaint();
        Mouse.setIsFirstClick(true);
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        for(int i = listOfColors.size()-1; i >= 0; i--) {
            g2d.setPaint(listOfColors.get(i));
            g2d.fill(listOfShapes.get(i));
        }

        if(!(path == null)) {
            g2d.setPaint(Color.black);
            g2d.draw(path);
        }
        if(!(pol == null)) {
            g2d.setPaint(new Color(200, 0, 0));
            g2d.fill(pol);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        doDrawing(g);
    }

}
