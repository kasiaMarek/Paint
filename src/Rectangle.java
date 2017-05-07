import java.awt.geom.Rectangle2D;

/**
 * A class rectangle.
 * @author Katarzyna Marek
 * @version 1.0
 */
class Rectangle extends Rectangle2D.Float {
    /**
     * Creates a rectangle.
     * @param x x coordinate of left corner
     * @param y y coordinate of left corner
     * @param width width
     * @param height height
     */
    Rectangle(float x, float y, float width, float height) {

        setFrame(x, y, width, height);
    }


    /**
     * Changes size of a rectangle.
     * @param w new width
     * @param h new height
     * @param sx x coordinate of left corner
     * @param sy y coordinate of left corner
     * @return resized rectangle
     */
    Rectangle resizeRectangle(float w, float h, float sx, float sy) {
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
