import com.sun.tools.classfile.InnerClasses_attribute;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kasia on 04.05.2017.
 */
public class InfoButton extends JMenuItem {
    public InfoButton() {
        setText("Info");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InfoFrame info = new InfoFrame();
                info.setVisible(true);
            }
        });
    }
    public void addListner() {
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InfoFrame info = new InfoFrame();
                info.setVisible(true);
            }
        });
    }
    private class InfoFrame extends JFrame {
        public InfoFrame() {
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

        private void setText(JLabel label) {
            StringBuilder sb = new StringBuilder();
            sb.append("<html><center>");
            sb.append("<br/>" + "nazwa: Edytor graficzny" + "<br/>" + "<br/>" +
                    "Program ten pozwala na tworzenie obrazow z protych figur geometycznych." + "<br/>" + "<br/>" +
                    "autor: Katarzyna Marek");
            sb.append("</center></html>");
            label.setText(sb.toString());
        }
    }
}

