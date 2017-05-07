import javax.swing.*;

/**
 * Class main frame.
 * @author Katarzyna Marek
 * @version 1.0
 */
class GUI extends JFrame {
    private static Surface surface;

    /**
     * Constructor for main frame.
     */
    GUI() {

        surface = new Surface();
        add(surface);
        setTitle("Edytor Graficzny");
        setSize(700, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    /**
     * Triggers paint function for Surface.
     */
    static void doRepaint() {
        surface.repaint();
    }


}