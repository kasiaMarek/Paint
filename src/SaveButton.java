import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by kasia on 04.05.2017.
 */
public class SaveButton extends JMenuItem {
    public SaveButton() {
        setText("Save");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               FileChooserFrame f = new FileChooserFrame();
            }
        });
    }

    private class FileChooserFrame extends JDialog {

        private FileChooserFrame() {
            add(new SaveButton.FileChooser());
        }
    }

    public class FileChooser extends JFileChooser {
        public FileChooser() {
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Tmp Files", "tmp");
            setFileFilter(filter);
            int returnVal = this.showSaveDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    writeFile(getSelectedFile().getAbsolutePath());
                }
                catch(IOException ex){
                }
            }
        }
    }

    public static void writeFile(String string) throws IOException {
        FileOutputStream fos = new FileOutputStream(string);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeInt(Surface.listOfShapes.size());

        for(int i = 0; i < Surface.listOfShapes.size(); i++) {
            oos.writeObject(Surface.listOfShapes.get(i));
            oos.writeObject(Surface.listOfColors.get(i));
        }

        oos.close();
    }
}
