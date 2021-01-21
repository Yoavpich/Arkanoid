import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Yoav Pichoto
 * @version 2.2
 * @since 2020-03-30
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private java.awt.Color edgeColor;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * constructor.
     *
     * @param center Point
     * @param r      int
     * @param color  java.awt.Color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * constructor.
     *
     * @param center Point.
     * @param r      int.
     * @param v      Velocity.
     * @param color  java.awt.Color.
     */
    public Ball(Point center, int r, Velocity v, java.awt.Color color) {
        this(center, r, color);
        this.velocity = v;
    }

    /**
     * constructor.
     *
     * @param x     double
     * @param y     double
     * @param r     int
     * @param color java.awt.Color
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this(new Point(x, y), r, color);
    }

    /**
     * Function to get x-value of the center.
     *
     * @return the x-value of the center
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Function to get y-value of the center.
     *
     * @return the y-value of the center
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Function to get the radius of the ball.
     *
     * @return the radius of this ball.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Function to get the color of the ball.
     *
     * @return the color of this ball.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Set edge color.
     *
     * @param c Color.
     */
    public void setEdgeColor(Color c) {
        this.edgeColor = c;
    }

    /**
     * Function to draw the ball on the given DrawSurface.
     *
     * @param surface DrawSurface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        if (this.edgeColor != null) {
            surface.setColor(this.edgeColor);
            surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        }
    }

    /**
     * notify the ball that time has passed, and then run the moveOneStep method.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
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
     * Function to define a velocity.
     *
     * @param v velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Function to define a velocity.
     *
     * @param dx double
     * @param dy double
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Function to access the velocity-value.
     *
     * @return velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Method to set a new game environment.
     *
     * @param g GameEnvironment
     */
    public void setGameEnvironment(GameEnvironment g) {
        this.gameEnvironment = g;
    }

    /**
     * Function to move the ball and keep it inside the surface.
     */
    public void moveOneStep() {
        int newX, newY;
        if (this.gameEnvironment == null) {
            return;
        }
        Line trLine = new Line(this.center, this.velocity.applyToPoint(this.center));
        CollisionInfo collInfo = this.gameEnvironment.getClosestCollision(trLine);
        if (collInfo == null) {
            this.center = trLine.end();
        } else {
            /* Option 1
            newX = (int) (collInfo.collisionPoint().getX() - this.velocity.getDx());
            newY = (int) (collInfo.collisionPoint().getY() - this.velocity.getDy());
            */
            // Option 2 (Smoother)
            newX = (int) (collInfo.collisionPoint().getX() - this.radius / 2);
            newY = (int) (collInfo.collisionPoint().getY() - this.radius / 2);
            if (this.velocity.getDx() < 0) { // If dx is negative
                newX = (int) (collInfo.collisionPoint().getX() + this.radius / 2);
            }
            if (this.velocity.getDy() < 0) { // If dy is negative
                newY = (int) (collInfo.collisionPoint().getY() + this.radius / 2);
            }
            if (Math.abs(this.velocity.getDx()) == (this.radius / 2)) {
                newX = (int) (collInfo.collisionPoint().getX() - this.radius / 3 - 2);
                if (this.velocity.getDx() < 0) {
                    newX = (int) (collInfo.collisionPoint().getX() + this.radius / 3 + 2);
                }
            }
            if (Math.abs(this.velocity.getDy()) == (this.radius / 2)) {
                newY = (int) (collInfo.collisionPoint().getY() - this.radius / 3 - 2);
                if (this.velocity.getDy() < 0) {
                    newY = (int) (collInfo.collisionPoint().getY() + this.radius / 3 + 2);
                }
            }
            this.center = new Point(newX, newY);
            setVelocity(collInfo.collisionObject().hit(this, collInfo.collisionPoint(), this.velocity));
        }
    }

    /**
     * Method to remove this ball from SpriteList.
     *
     * @param g Game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}