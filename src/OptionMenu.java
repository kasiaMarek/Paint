import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kasia on 04.05.2017.
 */
public class OptionMenu extends JMenuBar {
    JMenu menu = new JMenu("Opcje");
    public OptionMenu () {
        makeMenu();
        this.add(menu);
    }

    private void makeMenu() {
        menu.add(new InfoButton());
        menu.add(new SaveButton());
    }

}
