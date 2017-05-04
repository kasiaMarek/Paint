import java.applet.*;
import java.awt.*;
import java.net.*;
import java.awt.event.*;

public class PrzykladWiekszy extends Applet implements ActionListener {
  public void init() {
    Button button = new Button("go to Google");
    button.addActionListener(this);
    add(button);
  }

  public void actionPerformed(ActionEvent ae) {
    try {
      URL url = new URL(getDocumentBase(), "http://www.google.com");

      getAppletContext().showDocument(url);
    } catch (MalformedURLException e) {
      showStatus("URL not found");
    }
  }
}