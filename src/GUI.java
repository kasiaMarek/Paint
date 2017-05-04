import javax.swing.*;

class GUI extends JFrame {
    static Surface surface;
    public GUI() {

        surface = new Surface();
        add(surface);
        setTitle("Edytor Graficzny");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void doRepaint() {
        surface.repaint();
    }

    public static void main(String[] args) {
       GUI frame = new GUI();
       frame.setVisible(true);
    }


}