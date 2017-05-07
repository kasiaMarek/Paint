import javax.swing.*;
/**
 * A class option menu bar seen as a "Option" button.
 * @author Katarzyna Marek
 * @version 1.0
 */
class OptionMenu extends JMenuBar {
    private JMenu menu;
    /**
     * Creates option bar and adds menu to it.
     */
    OptionMenu () {
        menu = new JMenu("Opcje");
        makeMenu();
        this.add(menu);
    }
    /**
     * Makes menu seen after clicking "Option".
     */
    private void makeMenu() {
        menu.add(new InfoButton());
        menu.add(new SaveOpen(SaveOpen.Mode.SAVE));
        menu.add(new SaveOpen(SaveOpen.Mode.OPEN));
    }

}
