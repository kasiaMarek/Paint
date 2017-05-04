import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kasia on 04.05.2017.
 */
public class ButtonPanel extends JPanel {
    private Mode mode = Mode.CIRCLE;
    public ButtonPanel(){
        add(new ShapeButton("Circle", Mode.CIRCLE));
        add(new ShapeButton("Rectangle", Mode.RECTANGLE));
        add(new ShapeButton("Polygon", Mode.POLYGON));
        add(new ShapeButton("Select", Mode.SELECT));
        add(new OptionMenu());
    }

    public class ShapeButton extends Button {

        public ShapeButton (String str, Mode m) {
            this.setLabel(str);
            addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mode = m;
                }
            });
        }
    }

    public Mode getMode(){
        return mode;
    }
}
