/**
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-03-30
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor.
     *
     * @param dx double
     * @param dy double
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p Point
     * @return new point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        double newX, newY;
        newX = p.getX() + this.dx;
        newY = p.getY() + this.dy;
        return new Point(newX, newY);
    }

    /**
     * Function to get dx-value of velocity.
     *
     * @return the dx-value of velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Function to get dy-value of velocity.
     *
     * @return the dy-value of velocity.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Convert an angle and speed into a velocity.
     *
     * @param angle double
     * @param speed double
     * @return new Velocity.
     */
    public Velocity fromAngleAndSpeed(double angle, double speed) {
        double newDx = speed * Math.cos(Math.toRadians(angle));
        double newDy = speed * Math.sin(Math.toRadians(angle));
        return new Velocity(newDx, newDy);
    }
}