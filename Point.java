/**
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-03-27
 */
public class Point {
    private double x;
    private double y;

    /**
     * constructor.
     *
     * @param x double
     * @param y double
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Function to calculate distance between 2 points.
     *
     * @param other point
     * @return the distance of this point to the other point.
     */
    public double distance(Point other) {
        double distX, distY;
        distX = (this.x - other.getX()) * (this.x - other.getX());
        distY = (this.y - other.getY()) * (this.y - other.getY());
        return Math.sqrt(distX + distY);
    }

    /**
     * Function to check if 2 points equals.
     *
     * @param other point
     * @return true is the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return this.x == other.getX() && this.y == other.getY();
    }

    /**
     * Function to get x-value of point.
     *
     * @return the x-value of point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Function to get y-value of point.
     *
     * @return the y-value of point.
     */
    public double getY() {
        return this.y;
    }
}
