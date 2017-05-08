import javax.swing.*;
import java.awt.*;

/**
 * Class containing buttons "Ellipse", "Rectangle", "Polygon", "Select" and "Option".
 * @author Katarzyna Marek
 * @version 1.0
 */
class ButtonPanel extends JPanel {
     ButtonPanel() {
        add(new OptionMenu());
        add(new ShapeButton("Ellipse", Mode.ELLIPSE));
        add(new ShapeButton("Rectangle", Mode.RECTANGLE));
        add(new ShapeButton("Polygon", Mode.POLYGON));
        add(new ShapeButton("Select", Mode.SELECT));
        add(new ShapeOptionMenu());
    }
    /**
     * Inside class defining shape buttons.
     */
    public class ShapeButton extends Button {

         ShapeButton(String str, Mode m) {
            this.setLabel(str);
            addActionListener((e) -> {
                Surface.setMode(m);
                Surface.setPathToNull();
            });
        }
    }
}
