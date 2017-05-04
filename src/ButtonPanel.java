import javax.swing.*;
import java.awt.*;

/**
 * Created by kasia on 04.05.2017.
 */
public class ButtonPanel extends JPanel {
    private Mode mode = Mode.ELIPSE;

    public ButtonPanel() {
        add(new ShapeButton("Elipse", Mode.ELIPSE));
        add(new ShapeButton("Rectangle", Mode.RECTANGLE));
        add(new ShapeButton("Polygon", Mode.POLYGON));
        add(new ShapeButton("Select", Mode.SELECT));
        add(new OptionMenu());
    }

    public class ShapeButton extends Button {

        public ShapeButton(String str, Mode m) {
            this.setLabel(str);
            addActionListener((e) -> {
                Surface.setMode(m);
                Surface.setPathToNull();
            });
        }
    }

    public Mode getMode() {
        return mode;
    }
}
