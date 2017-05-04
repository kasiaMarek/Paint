import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by kasia on 04.05.2017.
 */
public class ColorChooser extends JFrame {
    JColorChooser chooser = new JColorChooser();
    public ColorChooser() {
        add(chooser);
        setTitle("Wybor koloru");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLocation(950,550);
        setAlwaysOnTop( true );
    }

    public Color getColor(){
        return chooser.getColor();
    }
}

