import java.awt.geom.Ellipse2D;

/**
 * A class ellpise.
 * @author Katarzyna Marek
 * @version 1.0
 */

class Circle extends Ellipse2D.Float {

    /**
     * Creates an ellipse.
     * @param x x coordinate of left corner
     * @param y y coordinate of left corner
     * @param width width
     * @param height height
     */
    Circle(float x, float y, float width, float height) {

        setFrame(x, y, width, height);
    }

    /**
    * Changes size of an ellipse.
    * @param w new width
    * @param h new height
    * @param sx x coordinate of left corner
    * @param sy y coordinate of left corner
    * @return resized ellipse
    */

    Circle resizeCircle(float w, float h, float sx, float sy) {
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

