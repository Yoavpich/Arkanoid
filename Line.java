import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Yoav Pichoto
 * @version 2.1
 * @since 2020-03-27
 */
public class Line implements Sprite {
    private Point start;
    private Point end;
    private Color color;

    /**
     * constructor.
     *
     * @param start Point
     * @param end   Point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor.
     *
     * @param x1 double
     * @param y1 double
     * @param x2 double
     * @param y2 double
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * constructor.
     *
     * @param x1 double
     * @param y1 double
     * @param x2 double
     * @param y2 double
     * @param c  java.awt.Color.
     */
    public Line(double x1, double y1, double x2, double y2, java.awt.Color c) {
        this(x1, y1, x2, y2);
        this.color = c;
    }

    /**
     * Function to calculate length of line.
     *
     * @return the length of this line.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Function to calculate the middle point of the line.
     *
     * @return the middle point of the line.
     */
    public Point middle() {
        double middleX, middleY;
        middleX = (start.getX() + end.getX()) / 2;
        middleY = (start.getY() + end.getY()) / 2;
        return new Point(middleX, middleY);
    }

    /**
     * Function to get the start point of the line.
     *
     * @return the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Function to get the end point of the line.
     *
     * @return the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Function to calculate the equation of line.
     *
     * @param line Line
     * @return the equation of line, y = mx + b, as a point (m, b).
     */
    public Point lineToEquation(Line line) {
        double m, b;
        // slope 'm'
        m = (line.start.getY() - line.end.getY()) / (line.start.getX() - line.end.getX());
        // y-coordinate 'b'
        // y = mx + b -> b = y - mx
        b = line.start.getY() - m * line.start.getX();
        return new Point(m, b);
    }

    /**
     * Function to check if 2 lines is intersecting.
     *
     * @param other Line
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        double intersectX, intersectY, minX, maxX, minY, maxY;
        Point thisEqu, otherEqu;
        if (this.start.getX() != this.end.getX() && other.start.getX() != other.end.getX()) {
            thisEqu = lineToEquation(this);
            otherEqu = lineToEquation(other);
            // y = m1x + b1, y = m2x + b2 -> x = (b2 - b1) / (m1 - m2)
            intersectX = (otherEqu.getY() - thisEqu.getY()) / (thisEqu.getX() - otherEqu.getX());
            // If the x-value of the intersection is not in range of this line
            minX = Math.min(this.start.getX(), this.end.getX());
            maxX = Math.max(this.start.getX(), this.end.getX());
            if (minX > intersectX || intersectX > maxX) {
                return false;
            }
            // If the x-value of the intersection is not in range of other line
            minX = Math.min(other.start.getX(), other.end.getX());
            maxX = Math.max(other.start.getX(), other.end.getX());
            if (minX > intersectX || intersectX > maxX) {
                return false;
            }
            // If the x-value of intersection is in both ranges
            return true;
        } else if (this.start.getX() == this.end.getX() && other.start.getX() != other.end.getX()) {
            otherEqu = lineToEquation(other);
            intersectX = this.start.getX();
            // If the x-value of the intersection is not in range of other line
            minX = Math.min(other.start.getX(), other.end.getX());
            maxX = Math.max(other.start.getX(), other.end.getX());
            if (minX > intersectX || intersectX > maxX) {
                return false;
            }
            // y = m1x + b
            intersectY = otherEqu.getX() * intersectX + otherEqu.getY();
            minY = Math.min(this.start.getY(), this.end.getY());
            maxY = Math.max(this.start.getY(), this.end.getY());
            if (minY > intersectY || intersectY > maxY) {
                return false;
            }
            // Otherwise
            return true;
        } else if (this.start.getX() != this.end.getX() && other.start.getX() == other.end.getX()) {
            thisEqu = lineToEquation(this);
            intersectX = other.start.getX();
            // If the x-value of the intersection is not in range of this line
            minX = Math.min(this.start.getX(), this.end.getX());
            maxX = Math.max(this.start.getX(), this.end.getX());
            if (minX > intersectX || intersectX > maxX) {
                return false;
            }
            // y = m1x + b
            intersectY = thisEqu.getX() * intersectX + thisEqu.getY();
            minY = Math.min(other.start.getY(), other.end.getY());
            maxY = Math.max(other.start.getY(), other.end.getY());
            if (minY > intersectY || intersectY > maxY) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Function to calculate the intersection point of 2 lines.
     *
     * @param other Line
     * @return the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        double intersectX = -1, intersectY = -1;
        Point thisEqu, otherEqu;
        // First, if the lines intersect
        if (isIntersecting(other)) {
            if (this.start.getX() != this.end.getX() && other.start.getX() != other.end.getX()) {
                thisEqu = lineToEquation(this);
                otherEqu = lineToEquation(other);
                // y = m1x + b1, y = m2x + b2 -> x = (b2 - b1) / (m1 - m2)
                intersectX = (otherEqu.getY() - thisEqu.getY()) / (thisEqu.getX() - otherEqu.getX());
                // y = m1x + b
                intersectY = thisEqu.getX() * intersectX + thisEqu.getY();
            } else if (this.start.getX() == this.end.getX() && other.start.getX() != other.end.getX()) {
                otherEqu = lineToEquation(other);
                intersectX = this.start.getX();
                // y = m1x + b
                intersectY = otherEqu.getX() * intersectX + otherEqu.getY();
            } else if (this.start.getX() != this.end.getX() && other.start.getX() == other.end.getX()) {
                thisEqu = lineToEquation(this);
                intersectX = other.start.getX();
                // y = m1x + b
                intersectY = thisEqu.getX() * intersectX + thisEqu.getY();
            }
            return new Point(intersectX, intersectY);
        }
        // If they not intersect
        return null;
    }

    /**
     * Function to check if 2 lines equals.
     *
     * @param other Line
     * @return true is the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        // If both start and end points equals
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        }
        // Else
        return false;
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     *
     * @param rect Rectangle
     * @return view the above.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> interPoints = rect.intersectionPoints(this);
        if (interPoints.isEmpty()) { // If there are no intersection points
            return null;
        }
        // Find the closest point
        Point closest = interPoints.get(0);
        for (int i = 1; i < interPoints.size(); i++) {
            if (new Line(this.start, interPoints.get(i)).length() < new Line(this.start, closest).length()) {
                closest = interPoints.get(i);
            }
        }
        return closest;
    }

    /**
     * Method to find the closest point to this.start() out of the list.
     *
     * @param list java.util.List<Point>
     * @return the closest point to this.start() out of the points in the list.
     */
    public Point closestToStart(java.util.List<Point> list) {
        Point p0 = list.get(0), pI, closest = p0;
        for (int i = 1; i < list.size(); i++) {
            pI = list.get(i);
            if (p0.distance(this.start) > pI.distance(this.start)) {
                closest = pI;
            }
            p0 = pI;
        }
        return closest;
    }

    /**
     * Method to check if point is on this line.
     *
     * @param point Point
     * @return true if on line, otherwise - false.
     */
    public boolean onLine(Point point) {
        double min, max;
        if (this.start.getX() == this.end.getX()) {
            if (point.getX() == this.start.getX()) {
                min = Math.min(this.start.getY(), this.end.getY());
                max = Math.max(this.start.getY(), this.end.getY());
                return !(min > point.getY()) && !(point.getY() > max);
            }
            return false;
        }
        Point thisEqu = lineToEquation(this);
        if (point.getY() == point.getX() * thisEqu.getX() + thisEqu.getY()) {
            min = Math.min(this.start.getX(), this.end.getX());
            max = Math.max(this.start.getX(), this.end.getX());
            return !(min > point.getX()) && !(point.getX() > max);
        }
        return false;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        int x1 = (int) this.start.getX();
        int y1 = (int) this.start.getY();
        int x2 = (int) this.end.getX();
        int y2 = (int) this.end.getY();
        d.setColor(this.color);
        d.drawLine(x1, y1, x2, y2);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }

    /**
     * Method to add this sprite to both collidableList and SpriteList (if necessary).
     *
     * @param g Game
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Get the sprite's color.
     *
     * @return color.
     */
    @Override
    public Color getColor() {
        return this.color;
    }

    /**
     * Set c as the line's color.
     *
     * @param c java.awt.Color.
     */
    public void setColor(java.awt.Color c) {
        this.color = c;
    }
}
