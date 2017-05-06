import java.awt.geom.Rectangle2D;

/**
 * Created by kasia on 03.05.2017.
 */
class Rectangle extends Rectangle2D.Float {
    public Rectangle(float x, float y, float width, float height) {

        setFrame(x, y, width, height);
    }

    public Rectangle resizeRectangle(float w, float h, float sx, float sy) {
        Rectangle rectangle = this;
        rectangle.width = Math.abs(w);
        rectangle.height = Math.abs(h);
        if(w < 0)
            rectangle.x = sx + w;
        if(h < 0)
            rectangle.y = sy + h;
        return rectangle;

    }

}
