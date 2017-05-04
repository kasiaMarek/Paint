import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.GeneralPath;


/**
 * Created by kasia on 04.05.2017.
 */
public class Mouse extends MouseAdapter {
    ColorChooser c = new ColorChooser();
    static int sy;
    static int sx;
    static Circle circle;
    static Rectangle rectangle;
    static Shape chosenTemp;
    GeneralPath path = new GeneralPath();
    static boolean isFirstClick = true;

    public static void setIsFirstClick(boolean b) {
        isFirstClick = b;
    }

    private int choose(float x, float y){
        for(Shape c: Surface.listOfShapes) {
            if(c.contains(x, y))
                return Surface.listOfShapes.indexOf(c);
        }
        return -1;
    }

    public Mouse() {
    }

    public void actRightMouseButton() {
        c.setVisible(true);
    }

    public void mousePressed(MouseEvent e) {
        sy = e.getY();
        sx = e.getX();
        if(Surface.getMode() == Mode.ELIPSE) {
            circle = new Circle(sx,sy,0,0);
            Surface.addPath(circle);
        }
        if(Surface.getMode() == Mode.RECTANGLE) {
            rectangle = new Rectangle(sx, sy, 0, 0);
            Surface.addPath(rectangle);
        }
        if(Surface.getMode() == Mode.POLYGON) {
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
                    Surface.setPath(path);
                    Surface.listOfShapes.add(0,path);
                    Surface.listOfColors.add(0,Color.pink);
                    Surface.setPathToNull();
                    path = null;
                } else
                    path.lineTo(sx, sy);
            }
        }
        if(Surface.getMode() == Mode.SELECT) {
            Surface.setChosen(choose(sx, sy));
            if(Surface.getChosen() >= 0)
                chosenTemp = Surface.listOfShapes.get(Surface.getChosen());
            if(c.isVisible()) {
                Surface.setColor(c.getColor());
                GUI.doRepaint();
            }
            if(SwingUtilities.isRightMouseButton(e))
                actRightMouseButton();
        }

    }

    public void mouseReleased(MouseEvent e) {
        if(Surface.getMode() == Mode.ELIPSE) {
            Surface.listOfShapes.add(0,circle);
            Surface.listOfColors.add(0,Color.BLACK);
            circle = null;
            path = null;
            Surface.setPath(null);
        }
        if(Surface.getMode() == Mode.RECTANGLE) {
            Surface.listOfShapes.add(0,rectangle);
            Surface.listOfColors.add(0,Color.cyan);
            rectangle = null;
            path = null;
            Surface.setPath(null);
        }
        GUI.doRepaint();
    }

}
