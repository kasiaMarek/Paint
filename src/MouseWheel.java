import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * Created by kasia on 04.05.2017.
 */
class MouseWheel implements MouseWheelListener {

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
