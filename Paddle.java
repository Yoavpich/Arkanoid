import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-05-02
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Block block;
    private int speed;
    private Color color;

    /**
     * Constructor.
     *
     * @param upperLeft Point
     * @param width     double
     * @param height    double
     * @param speed     int.
     */
    public Paddle(Point upperLeft, double width, double height, int speed) {
        this.block = new Block(new Rectangle(upperLeft, width, height));
        this.color = Color.YELLOW;
        this.speed = speed;
    }

    /**
     * Constructor.
     *
     * @param upperLeft Point
     * @param width     double
     * @param height    double
     */
    public Paddle(Point upperLeft, double width, double height) {
        this(upperLeft, width, height, 5);
        this.block = new Block(new Rectangle(upperLeft, width, height));
        this.color = Color.YELLOW;
    }

    /**
     * Method to set a keyboard to this paddle.
     *
     * @param k KeyboardSensor
     */
    public void setKeyboard(KeyboardSensor k) {
        this.keyboard = k;
    }

    /**
     * Method to move the paddle one step to the left.
     */
    public void moveLeft() {
        // I chose 5 for the velocity of the paddle.
        int newX = (int) this.block.getCollisionRectangle().getUpperLeft().getX() - this.speed;
        int oldY = (int) this.block.getCollisionRectangle().getUpperLeft().getY();
        double width = this.block.getCollisionRectangle().getWidth();
        double height = this.block.getCollisionRectangle().getHeight();
        this.block = new Block(new Point(newX, oldY), width, height);
    }

    /**
     * Method to move the paddle one step to the right.
     */
    public void moveRight() {
        // I chose 5 for the velocity of the paddle.
        int newX = (int) this.block.getCollisionRectangle().getUpperLeft().getX() + this.speed;
        int oldY = (int) this.block.getCollisionRectangle().getUpperLeft().getY();
        double width = this.block.getCollisionRectangle().getWidth();
        double height = this.block.getCollisionRectangle().getHeight();
        this.block = new Block(new Point(newX, oldY), width, height);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        Point upperLeft = this.block.getCollisionRectangle().getUpperLeft();
        double y = upperLeft.getY();
        double width = this.block.getCollisionRectangle().getWidth();
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY) || keyboard.isPressed("a")) {
            if (upperLeft.equals(new Point(0, y))) { // Out of bounds
                return;
            }
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY) || keyboard.isPressed("d")) {
            if (upperLeft.equals(new Point(800 - width, y))) { // Out of bounds
                return;
            }
            moveRight();
        }
    }

    /**
     * Draw this block in given surface.
     *
     * @param surface DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        int x = (int) this.block.getCollisionRectangle().getUpperLeft().getX();
        int y = (int) this.block.getCollisionRectangle().getUpperLeft().getY();
        int width = (int) this.block.getCollisionRectangle().getWidth();
        int height = (int) this.block.getCollisionRectangle().getHeight();
        surface.setColor(this.color);
        surface.fillRectangle(x, y, width, height);
        surface.setColor(Color.BLACK);
        surface.drawRectangle(x, y, width, height);
    }

    /**
     * The method returns the "collision shape" of the object.
     *
     * @return the "collision shape" of the object.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.block.getCollisionRectangle();
    }

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     *
     * @param collisionPoint  Point
     * @param currentVelocity Velocity
     * @param hitter          Ball
     * @return new velocity expected after the hit.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Rectangle rect = this.block.getCollisionRectangle();
        Velocity newV = currentVelocity;
        double dx = currentVelocity.getDx(), dy = currentVelocity.getDy();
        double cX = collisionPoint.getX(), cY = collisionPoint.getY();
        double currentX = rect.getUpperLeft().getX();
        double currentY = rect.getUpperLeft().getY();
        int partLength = (int) (rect.getWidth() / 5);
        double newSpeed = Math.sqrt(dx * dx + dy * dy);
        // 5 equal parts => the size of each part is 80/5 = 16
        if (cY == currentY) {
            if (currentX <= cX && cX < currentX + partLength) { // Part 1 (From the left to the right)
                newV = newV.fromAngleAndSpeed(180 - 300, newSpeed);
            } else if (currentX + partLength <= cX && cX < currentX + 2 * partLength) { // Part 2
                newV = newV.fromAngleAndSpeed(180 - 330, newSpeed);
            } else if (currentX + 2 * partLength <= cX && cX < currentX + 3 * partLength) { // Part 3
                newV = new Velocity(dx, -dy);
            } else if (currentX + 3 * partLength <= cX && cX < currentX + 4 * partLength) { // Part 4
                newV = newV.fromAngleAndSpeed(-30, newSpeed);
            } else if (currentX + 4 * partLength <= cX && cX <= currentX + rect.getWidth()) { // Part 5
                newV = newV.fromAngleAndSpeed(-60, newSpeed);
            }
        }
        return newV;
    }

    /**
     * Add this paddle to the game.
     *
     * @param g Game
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
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
     * Set the paddle's color.
     *
     * @param c Color.
     */
    public void setColor(Color c) {
        this.color = c;
    }
}