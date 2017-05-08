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
        menu = new JMenu("More");
        makeMenu();
        this.add(menu);
    }
    /**
     * Makes menu seen after clicking "Option".
     */
    private void makeMenu() {
        menu.add(new NewButton());
        menu.add(new SaveOpen(SaveOpen.Mode.SAVE));
        menu.add(new SaveOpen(SaveOpen.Mode.OPEN));
        menu.add(new InfoButton());
    }

    /**
     * A class "New" menu item. Seen in menu "More".
     */

    class NewButton extends JMenuItem {
        /**
         * Creates a menu item with tile "New" that deletes all shapes seen in drawing area.
         */
        NewButton() {
            setText("New");
            addActionListener(e -> {
                Surface.removeShapes();
                GUI.doRepaint();
            });
        }
    }

}
