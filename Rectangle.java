/**
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-04-23
 */
public class Rectangle {
    private double width;
    private double height;
    private Point upperLeft;

    /**
     * constructor.
     *
     * @param upperLeft Point
     * @param width     double
     * @param height    double
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Calculates the intersection points of specified line with this rectangle.
     *
     * @param line Line
     * @return a (possibly empty) List of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> list = new java.util.ArrayList<>();
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point lowerRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        // Rectangle made from 4 sides. Each one of them is a line:
        // Upper side
        if (null != line.intersectionWith(new Line(upperLeft, upperRight))) {
            list.add(line.intersectionWith(new Line(upperLeft, upperRight)));
        }
        // Lower side
        if (null != line.intersectionWith(new Line(lowerLeft, lowerRight))) {
            list.add(line.intersectionWith(new Line(lowerLeft, lowerRight)));
        }
        // Left side
        if (null != line.intersectionWith(new Line(upperLeft, lowerLeft))) {
            list.add(line.intersectionWith(new Line(upperLeft, lowerLeft)));
        }
        // Right side
        if (null != line.intersectionWith(new Line(upperRight, lowerRight))) {
            list.add(line.intersectionWith(new Line(upperRight, lowerRight)));
        }
        return list;
    }

    /**
     * Gets the width of this rectangle.
     *
     * @return width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets the height of this rectangle.
     *
     * @return height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets the upper-left point of this rectangle.
     *
     * @return upper-left point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}