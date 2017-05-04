import java.awt.geom.Rectangle2D;

/**
 * Created by kasia on 03.05.2017.
 */
class Rectangle extends Rectangle2D.Float {
    public Rectangle(float x, float y, float width, float height) {

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

    public Rectangle resizeRectangle(float w, float h) {
        Rectangle rectangle = this;
        rectangle.setWidth(w);
        rectangle.setHeight(h);
        return rectangle;

    }

}
