import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by kasia on 05.05.2017.
 */
public class OpenButton extends JMenuItem {
    public OpenButton(){
        setText("Open");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileChooserFrame f = new FileChooserFrame();
            }
        });
    }

    private class FileChooserFrame extends JDialog {

        private FileChooserFrame() {
            add(new FileChooser());
        }
    }

    public class FileChooser extends JFileChooser {
        public FileChooser() {
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Tmp Files", "tmp");
            setFileFilter(filter);
            int returnVal = this.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    for(int i = 0; i < Surface.listOfShapes.size(); i++) {
                        Surface.listOfShapes.remove(i);
                        Surface.listOfColors.remove(i);
                    }
                    readFile(getSelectedFile().getAbsolutePath());
                }
                catch(IOException ex){
                    System.out.println("zle");
                } catch(ClassNotFoundException e1) {
                    System.out.println("zle");
                }
            }
        }
    }

    public static void readFile(String name) throws IOException, ClassNotFoundException {
        FileInputStream fos = new FileInputStream(name);
        ObjectInputStream oos = new ObjectInputStream(fos);

        int n = oos.readInt();

        for(int i = 0; i < n; i++) {
            Surface.listOfShapes.add((Shape) oos.readObject());
            Surface.listOfColors.add((Color) oos.readObject());
        }
        oos.close();
        GUI.doRepaint();
    }
}
