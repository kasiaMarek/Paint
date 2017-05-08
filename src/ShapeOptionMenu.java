import javax.swing.*;
/**
 * A class option menu bar seen as a "Option" button.
 * @author Katarzyna Marek
 * @version 1.0
 */
class ShapeOptionMenu extends JMenuBar {
    private JMenu menu;
    /**
     * Creates option bar and adds menu to it.
     */
    ShapeOptionMenu () {
        menu = new JMenu("Option");
        makeMenu();
        this.add(menu);
    }
    /**
     * Makes menu seen after clicking "Option".
     */
    private void makeMenu() {
        menu.add(new DeleteButton());
        menu.add(new UpDownButton(UpDown.UP));
        menu.add(new UpDownButton(UpDown.DOWN));
    }

    class DeleteButton extends JMenuItem {
        /**
         * Creates a menu item with tile "Delete" that deletes a shape when one is chosen in SELECT mode.
         */
        DeleteButton() {
            setText("Delete");
            addActionListener(e -> {
                if(Surface.getChosen() >= 0 && Surface.getMode() == Mode.SELECT)
                    Surface.deleteShape(Surface.getChosen());
            });
        }
    }

    class UpDownButton extends JMenuItem {
        /**
         * Creates a menu item with tile "Move up" or "Move down" that moves up or down on the list a shape when one is
         * chosen in SELECT mode.
         */
        UpDownButton(UpDown mode) {
            setText(UpDown.toString(mode));
            addActionListener(e -> {
                if(Surface.getChosen() >= 0 && Surface.getMode() == Mode.SELECT) {
                    if(mode == UpDown.UP)
                        Surface.moveUp(Surface.getChosen());
                    if(mode == UpDown.DOWN)
                        Surface.moveDown(Surface.getChosen());
                }
            });
        }
    }
    /**
     * An enum mode. UP for moving shapes up and DOWN for moving down.
     */
    enum UpDown{
        UP{
        },
        DOWN{
        };
        /**
         * Gives string assigned to mode.
         * @param mode UP or DOWN
         * @return name of the mode
         */
        private static String toString(UpDown mode){
            if(mode == UP)
                return "Move up";
            else
                return "Move down";
        }

    }
}
