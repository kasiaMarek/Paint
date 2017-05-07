import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * A class handling mouse move actions.
 * @author Katarzyna Marek
 * @version 1.0
 */
public class MouseMove extends MouseMotionAdapter {
    /**
     * Function handling actions in different modes when mouse is dragged.
     * ELLIPSE mode: resizes created circle
     * RECTANGLE mode: resizes created rectangle
     * SELECT mode: if a shape is chosen it moves it
     * @param e event that occurs when mouse dragged
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        switch(Surface.getMode()) {
            case ELLIPSE:
                Surface.addPath(Mouse.circle.resizeCircle(e.getX() - Mouse.sx, e.getY() - Mouse.sy, Mouse.sx, Mouse.sy));
                break;
            case RECTANGLE:
                Surface.addPath(Mouse.rectangle.resizeRectangle(e.getX() - Mouse.sx, e.getY() - Mouse.sy, Mouse.sx, Mouse.sy));
                break;
            case SELECT:
                if(Surface.getChosen() >= 0)
                    Surface.moveShape(Mouse.chosenTemp, e.getX() - Mouse.sx, e.getY() - Mouse.sy);
        }
    }
    /**
     * Function handling actions in different modes when mouse is dragged.
     * @param e event that occurs when mouse dragged
     */
    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
