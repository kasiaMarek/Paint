import javax.swing.*;

/**
 * A main runnable class for applet.
 * @author Katarzyna Marek
 * @version 1.0
 */
public class PaintApplet extends JApplet {
    /**
     * Main function for applet constructing main frame.
     */
    public void init() {
        GUI frame = new GUI();
        frame.setVisible(true);
    }
}
