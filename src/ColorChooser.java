import javax.swing.*;
import java.awt.*;
/**
 * A class frame for color chooser.
 * @author Katarzyna Marek
 * @version 1.0
 */
public class ColorChooser extends JFrame {
    private JColorChooser chooser = new JColorChooser();

    /**
     * Creates a frame and adds color chooser.
     */
    ColorChooser() {
        add(chooser);
        setTitle("Wybor koloru");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLocation(950,550);
        setAlwaysOnTop( true );
    }
    /**
     * Gets the color chosen by user in color chooser.
     * @return color chosen by user
     */
    public Color getColor(){
        return chooser.getColor();
    }
}

