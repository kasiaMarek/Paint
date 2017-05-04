import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Created by kasia on 03.05.2017.
 */
public class Surface extends JPanel {
    ArrayList<Shape> listOfShapes = new ArrayList<>();
    ArrayList<Color> listOfColors = new ArrayList<>();
    GeneralPath path;
    private Circle pol;
    ButtonPanel buttonPanel = new ButtonPanel();

    int chosen = -1;

    public Surface() {
        add(buttonPanel, BorderLayout.NORTH);
        addMouseListener(new Mouse() {});
    }

    private void addPath(Shape shape) {
        path = new GeneralPath(shape);
        repaint();
    }

    public void setColor(Color color) {
        if(chosen >= 0)
            listOfColors.set(chosen, color);
    }

    private int choose(float x, float y){
        for(Shape c: listOfShapes) {
            if(c.contains(x, y))
                return listOfShapes.indexOf(c);
        }
        return -1;
    }

    private void moveShape(Shape s, double w, double h) {
        AffineTransform a = new AffineTransform(1,0,0,1,w,h);
        Shape shape = new GeneralPath(s).createTransformedShape(a);
        listOfShapes.set(chosen, shape);
        repaint();
    }

    private void resizeShape(double s) {
        AffineTransform a = new AffineTransform(s,0,0,s,0,0);
        Shape shape = new GeneralPath(listOfShapes.get(chosen)).createTransformedShape(a);
        listOfShapes.set(chosen, shape);
        repaint();
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        for(int i = listOfColors.size()-1; i >= 0; i--) {
            g2d.setPaint(listOfColors.get(i));
            g2d.fill(listOfShapes.get(i));
        }

        if(!(path == null)) {
            g2d.setPaint(Color.black);
            g2d.draw(path);
        }
        if(!(pol == null)) {
            g2d.setPaint(new Color(200, 0, 0));
            g2d.fill(pol);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        doDrawing(g);
    }


    public class Mouse extends MouseAdapter {
        ColorChooser c = new ColorChooser();
        int sy;
        int sx;
        boolean isFirstClick = true;
        Circle circle;
        Rectangle rectangle;
        Shape chosenTemp;

        public Mouse() {
            addMouseMotionListener(new MouseMove() {});
            addMouseWheelListener(new MouseWheel() {});
        }

        public void actRightMouseButton() {
            c.setVisible(true);
        }

        public void mousePressed(MouseEvent e) {
            Mode mode = buttonPanel.getMode();
            sy = e.getY();
            sx = e.getX();
            if(mode == Mode.CIRCLE) {
                circle = new Circle(sx,sy,0,0);
                addPath(circle);
            }
            if(mode == Mode.RECTANGLE) {
                rectangle = new Rectangle(sx, sy, 0, 0);
                addPath(rectangle);
            }
            if(mode == Mode.POLYGON) {
                if(isFirstClick) {
                    path = new GeneralPath();
                    path.moveTo(sx, sy);
                    pol = new Circle(sx-5, sy-5, 10 , 10);
                    isFirstClick = false;
                }
                else {
                    if(pol.getBounds2D().contains(sx, sy)) {
                        path.closePath();
                        listOfShapes.add(0,path);
                        listOfColors.add(0,Color.pink);
                        pol = null;
                        repaint();
                        path = null;
                        isFirstClick = true;
                    } else
                        path.lineTo(sx, sy);
                }
            }
            if(mode == Mode.SELECT) {
                chosen = choose(sx, sy);
                if(chosen >= 0)
                    chosenTemp = listOfShapes.get(chosen);
                if(c.isVisible()) {
                    setColor(c.getColor());
                    repaint();
                }
                if(SwingUtilities.isRightMouseButton(e))
                    actRightMouseButton();
            }

        }

        public void mouseReleased(MouseEvent e) {
            Mode mode = buttonPanel.getMode();
            if(mode == Mode.CIRCLE) {
                listOfShapes.add(0,circle);
                listOfColors.add(0,Color.BLACK);
                circle = null;
                path = null;
            }
            if(mode == Mode.RECTANGLE) {
                listOfShapes.add(0,rectangle);
                listOfColors.add(0,Color.cyan);
                rectangle = null;
                path = null;
            }
            repaint();
        }

        class MouseWheel implements MouseWheelListener {

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                Mode mode = buttonPanel.getMode();
                if(mode == Mode.SELECT) {
                    if(chosen >= 0) {
                        if(e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
                            if(e.getWheelRotation() > 0)
                                resizeShape(1.15);
                            else
                                resizeShape(0.95);
                        }
                    }
                }
            }
        }

        public class MouseMove extends MouseMotionAdapter {

            @Override
            public void mouseDragged(MouseEvent e) {
                Mode mode = buttonPanel.getMode();
                if(mode == Mode.CIRCLE)
                    addPath(circle.resizeCircle(e.getX() - sx, e.getY() - sy));
                if(mode == Mode.RECTANGLE)
                    addPath(rectangle.resizeRectangle(e.getX() - sx, e.getY() - sy));
                if(mode == Mode.SELECT)
                    if(chosen >= 0)
                        moveShape(chosenTemp, e.getX() - sx, e.getY() - sy);
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        }
    }
}
