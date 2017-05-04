import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;

/**
 * Created by kasia on 03.05.2017.
 */

class Circle extends Ellipse2D.Float {

        public Circle(float x, float y, float width, float height) {

            setFrame(x, y, width, height);
        }

        public void setWidth(float w) {
            if(w > 0)
                this.width = w;
            else
                this.width = 0;
        }
        public void setHeight(float h) {
            if(h > 0)
                this.height = h;
            else
                this.height = 0;
        }
        public Circle resizeCircle(float w, float h) {
            Circle circle = this;
            circle.setWidth(w);
            circle.setHeight(h);
            return circle;
        }
}

