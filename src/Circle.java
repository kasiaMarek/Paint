import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;

/**
 * Created by kasia on 03.05.2017.
 */

class Circle extends Ellipse2D.Float {

        public Circle(float x, float y, float width, float height) {

            setFrame(x, y, width, height);
        }

        public Circle resizeCircle(float w, float h, float sx, float sy) {
            Circle circle = this;
            circle.width = Math.abs(w);
            circle.height = Math.abs(h);
            if(w < 0)
                circle.x = sx + w;
            if(h < 0)
                circle.y = sy + h;
            return circle;
        }
}

