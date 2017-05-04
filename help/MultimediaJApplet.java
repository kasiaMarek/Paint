import javax.swing.*;
import java.awt.*;
import java.applet.*;
import java.net.*;

public class MultimediaJApplet extends JApplet {
  private Image image;
  private AudioClip audio;

  public void init() {
    try {
      image = getImage(new URL(this.getDocumentBase(),"tic.jpg"));
      audio = getAudioClip(new URL(this.getDocumentBase(),"muzyka.wav"));
    
    }
    catch(MalformedURLException e) {
      showStatus("Could not load files!");
      stop();
    }
  }
  public void paint(Graphics g) {
    g.drawImage(image,0,0,600,600,this);
 g.setFont( new Font("Courier", Font.PLAIN, 18));
     g.setColor(Color.RED);
     g.drawString("Atitlan lake, Guatemala", 20, 20);  }
  public void start() {
    if( audio!=null ) audio.loop();
  }
  public void stop() {
    if( audio!=null ) audio.stop();
  }
}
