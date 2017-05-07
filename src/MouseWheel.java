import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * A class handling mouse wheel actions.
 * @author Katarzyna Marek
 * @version 1.0
 */
class MouseWheel implements MouseWheelListener {
    /**
     * Function resizing chosen shape if in SELECT mode and mouse wheel is moved.
     * @param e event that occurs when mouse wheel is moved
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(Surface.getMode() == Mode.SELECT) {
            if(Surface.getChosen() >= 0) {
                if(e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
                    if(e.getWheelRotation() > 0)
                        Surface.resizeShape(1.15);
                    else
                        Surface.resizeShape(0.95);
                }
            }
        }
    }
}
