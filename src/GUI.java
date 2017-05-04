import javax.swing.*;
import java.awt.*;

class GUI extends JFrame {
    public GUI() {

        add(new Surface());
        setTitle("Edytor Graficzny");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
       GUI frame = new GUI();
       frame.setVisible(true);
    }
}