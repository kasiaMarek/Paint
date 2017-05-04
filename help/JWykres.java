import javax.swing.*;
import java.awt.* ;
import java.awt.event.* ;
import java.applet.Applet;

class JWykresWindowAdapter extends WindowAdapter {
  public void windowClosing(WindowEvent e) { System.exit(0); }
}

class JPrzyRys extends JButton implements ActionListener {
  JWykres p;
  double aa,bb;

  JPrzyRys( JWykres pp ) {
    super( "RYSUJ" );
    p = pp;
  }

  public void actionPerformed( ActionEvent e ) {
    try {
      aa = Double.parseDouble( p.aa.getText() ); }
    catch( NumberFormatException x ) {
      p.aa.setText( "Wstaw tu liczbe" );
      return;
    }
    try {
      bb = Double.parseDouble( p.bb.getText() ); }
    catch( NumberFormatException x ) {
      p.bb.setText( "Wstaw tu liczbe" );
      return;
    }
    if( bb <= aa ) {
      p.bb.setText( "Wstaw tu liczbe > od poczatku" );
      return;
    }
    p.a = aa;
    p.b = bb;
    p.rysuj();
  }
}

class jpanel extends JPanel
{
  jpanel( JWykres p ) {
    setLayout( new BorderLayout() );

    JPanel dane = new JPanel();
    dane.setLayout( new GridLayout( 2, 2 ) );
    dane.add( new JLabel( "Poczatek przedzialu", JLabel.CENTER ) );
    dane.add( new JLabel( "Koniec przedzialu", JLabel.CENTER ) );
    p.aa = new JTextField( 20 );
    dane.add( p.aa );
    p.bb = new JTextField( 20 );
    dane.add( p.bb );
    add( "North", dane );

    JPanel ster = new JPanel();
    ster.setLayout( new GridLayout( 1, 2 ) );
    ster.add( new JLabel( "Funkcja "+p.f.toString(), JLabel.CENTER ) );
    p.pr = new JPrzyRys( p );
    p.pr.addActionListener( p.pr );
    ster.add( p.pr );
    add( "South", ster );

    p.rys = new JPanel(true);
    add( "Center", p.rys );

    setSize(400, 450 );
  }
}

public class JWykres extends JApplet {
  Funkcja f;
  JPanel rys;         // miejsce rysunku
  JPrzyRys pr;        // przycisk uruchomienia rysowania
  JTextField aa,bb;   // pola wejsciowe odcinka [a,b]
  double a,b;

  public void init() {
   f = new Funkcja();
   setLayout( new BorderLayout() );
   add( "Center", new jpanel( this ) );
  }
  
  public void paint(Graphics g)
  {
    super.paint(g);
    rysuj();
  }

  public void rysuj() {
    double min, max, xh, yh;
    int x0, y0;
    int xx = rys.getSize().width - 100, yy = rys.getSize().height - 100;
    double[] wart = new double[xx+1];

    xh = (b - a) / xx;
    min = max = wart[0] = f.wartosc( a );
    for( int i = 1; i <= xx; i++ ) {
      wart[i] = f.wartosc( a + i * xh );
      if( wart[i] < min ) min = wart[i];
      else if( wart[i] > max ) max = wart[i];
    }
    if( min < max ) yh = (max - min) / yy; else yh = 1;

    // ramka i czyszczenie --------------------------------------------------
    Graphics g = rys.getGraphics();
    g.clearRect( 0, 0, xx+100, yy+100 );
    g.setColor( Color.red );
    g.drawRect( 5, 5, xx+90, yy+90 );
    // os y-ow  i x-ow -------------------------------------------------------
    g.setColor( Color.black );
    if( a*b <= 0 ) x0 = 50 - (int)(a / xh); else x0 = 50;
    g.drawLine( x0, yy+75, x0, 25 );
    g.drawLine( x0-5, yy+50, x0+5, yy+50 );
    g.drawLine( x0-5, 50, x0+5, 50 );
    g.drawLine( x0-5, 30, x0, 25 );
    g.drawLine( x0, 25, x0+5, 30 );
    if( min*max <= 0 ) y0 = yy+50 + (int)(min / yh); else y0 = yy+50;
    g.drawLine( 25, y0, xx+75, y0 );
    g.drawLine( 50, y0-5, 50, y0+5 );
    g.drawLine( xx+50, y0-5, xx+50, y0+5 );
    g.drawLine( xx+70, y0-5, xx+75, y0 );
    g.drawLine( xx+75, y0, xx+70, y0+5 );
    // etykiety -------------------------------------------------------------
    g.setColor( Color.red );
    g.drawString( String.valueOf( a ), 45, y0+15 );
    g.drawString( String.valueOf( b ), xx+45, y0+15 );
    g.drawString( String.valueOf( ((double)((int)(100*min)))/100 ), x0+10, yy+55 );
    g.drawString( String.valueOf( ((double)((int)(100*max)))/100 ), x0+10, 55 );
    // wykres ---------------------------------------------------------------
    g.setColor( Color.blue );
    for( int i = 1; i <= xx; i++ )
      g.drawLine( 49+i, yy+50 - (int)((wart[i-1] - min) / yh), 50+i, yy+50 - (int)((wart[i] - min) / yh) );
  }

  public static void main(String[] args) {
    JFrame f = new JFrame("Nie applet");
    f.setSize(640,480);
    f.addWindowListener(new JWykresWindowAdapter());
    JWykres w = new JWykres();
    f.add(w);
    w.init();
    f.setVisible(true);
  }
}
