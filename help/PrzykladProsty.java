import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

public class PrzykladProsty extends Applet
{
	public void paint(Graphics g)
	{
            g.setFont( new Font("Courier", Font.PLAIN, 18));
            g.setColor(Color.RED);
		g.drawString("Cos tam prostego", 20, 20);
	}

}