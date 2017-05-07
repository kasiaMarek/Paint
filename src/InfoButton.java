import javax.swing.*;
import java.awt.*;
/**
 * A class "Option" menu item.
 * @author Katarzyna Marek
 * @version 1.0
 */
class InfoButton extends JMenuItem {
    /**
     *  Creates a menu item with tile "Info" that opens information frame.
     */
    InfoButton() {
        setText("Info");
        addActionListener(e -> {
            InfoFrame info = new InfoFrame();
            info.setVisible(true);
        });
    }
    /**
     *  Class information frame.
     */
    private class InfoFrame extends JFrame {
        /**
         *  Creates information frame.
         */
        InfoFrame() {
            JLabel label = new JLabel();
            label.setHorizontalAlignment(SwingConstants.CENTER);
            setText(label);
            add(label, BorderLayout.CENTER);
            setTitle("Informacja");
            setSize(200, 200);
            setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            setLocationRelativeTo(null);
            setAlwaysOnTop( true );
        }
        /**
         *  A function printing information.
         *  @param label a component on which information is print
         */
        private void setText(JLabel label) {
            String sb = "<html><center>" +
                    "<br/>" + "nazwa: Edytor graficzny" + "<br/>" + "<br/>" +
                    "Program ten pozwala na tworzenie obrazow z protych figur geometycznych." + "<br/>" + "<br/>" +
                    "autor: Katarzyna Marek" +
                    "</center></html>";
            label.setText(sb);
        }
    }
}

