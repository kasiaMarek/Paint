import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;

/**
 * A class "Save" or "Open" menu item.
 * Contains information about the mode.
 * @author Katarzyna Marek
 * @version 1.0
 */
class SaveOpen extends JMenuItem {
    private Mode mode;
    /**
     * Creates "Save" or "Open" menu item and adds dialog to open file chooser.
     * @param mode SAVE for saving files  or OPEN for opening files
     */
    SaveOpen(Mode mode) {
        this.mode = mode;
        setText(Mode.toString(mode));
        addActionListener(e -> {
            JDialog f = new JDialog();
            f.add(new FileChooser());
        });
    }

    public class FileChooser extends JFileChooser {
        /**
         * Creates a file chooser dialog.
         */
        FileChooser() {
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Tmp Files", "tmp");
            setFileFilter(filter);
            if(mode == Mode.SAVE)
                save();
            if(mode == Mode.OPEN)
                open();
        }
        /**
         * Opens a file chooser dialog. That depending on mode allows to save a file.
         */
        private void save() {
            int returnVal = this.showSaveDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    writeFile(getSelectedFile().getAbsolutePath());
                } catch(IOException ex) {
                    System.out.println("Zapis pliku sie nie powiodl");
                }
            }
        }
        /**
         * Opens a file chooser dialog. That depending on mode allows to open a file first clearing the drawing area.
         */
        private void open() {
            int returnVal = this.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    Surface.removeShapes();
                    readFile(getSelectedFile().getAbsolutePath());
                    GUI.doRepaint();
                }
                catch(IOException e2){
                    System.out.println("Odczyt pliku sie nie powiodl");
                } catch(ClassNotFoundException | ClassCastException e2) {
                    System.out.println("Niepoprawny plik");
                }
            }
        }
    }
    /**
     * Writes information about created shapes into a .tmp file.
     * @param string name/path to file
     */
    private static void writeFile(String string) throws IOException {
        FileOutputStream fos = new FileOutputStream(string);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        int numberOfShapes = Surface.getNumberOfShapes();
        oos.writeInt(numberOfShapes);

        for(int i = numberOfShapes-1; i >= 0; i--) {
            oos.writeObject(Surface.getShape(i));
            oos.writeObject(Surface.getColor(i));
        }

        oos.close();
    }
    /**
     * Reads information about created shapes from a .tmp file.
     * @param name name/path to file
     */
    private static void readFile(String name) throws IOException, ClassNotFoundException, ClassCastException {
        FileInputStream fos = new FileInputStream(name);
        ObjectInputStream oos = new ObjectInputStream(fos);

        int n = oos.readInt();
        for(int i = 0; i < n; i++) {
            Surface.addShape((Shape) oos.readObject(),(Color) oos.readObject());
        }
        oos.close();
    }
    /**
     * An enum mode. SAVE for saving files  or OPEN for opening files.
     */
    enum Mode{
        SAVE{
        },
        OPEN{
        };
        /**
         * Gives string assigned to mode.
         * @param mode SAVE or OPEN
         * @return name of the mode
         */
        private static String toString(Mode mode){
            if(mode == SAVE)
                return "Save";
            else
                return "Open";
        }

    }
}

