import javax.swing.*;

/**
 * A main runnable class for applet mode.
 * @author Katarzyna Marek
 * @version 1.0
 */
public class PApplet extends JApplet {
    private static Surface surface;
    /**
     * Main function for applet constructing main frame.
     */
    public void init() {
        surface = new Surface();
        add(surface);
    }
    /**
     * Main function constructing main frame
     * @param args arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        surface = new Surface();
        frame.add(surface);
        frame.setTitle("Edytor Graficzny");
        frame.setSize(700, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    /**
     * Triggers paint function for Surface.
     */
    static void doRepaint() {
        surface.repaint();
    }
}