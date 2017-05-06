import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Created by kasia on 04.05.2017.
 */
public class MouseMove extends MouseMotionAdapter {

    @Override
    public void mouseDragged(MouseEvent e) {
        if(Surface.getMode() == Mode.ELIPSE)
            Surface.addPath(Mouse.circle.resizeCircle(e.getX() - Mouse.sx, e.getY() - Mouse.sy, Mouse.sx, Mouse.sy));
        if(Surface.getMode() == Mode.RECTANGLE)
            Surface.addPath(Mouse.rectangle.resizeRectangle(e.getX() - Mouse.sx, e.getY() - Mouse.sy, Mouse.sx, Mouse.sy));
        if(Surface.getMode() == Mode.SELECT)
            if(Surface.getChosen() >= 0)
                Surface.moveShape(Mouse.chosenTemp, e.getX() - Mouse.sx, e.getY() - Mouse.sy);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
