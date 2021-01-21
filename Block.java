import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-04-25
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private List<HitListener> hitListeners;
    private Color color;

    /**
     * Constructor.
     *
     * @param rect Rectangle
     */
    public Block(Rectangle rect) {
        this.rect = rect;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructor.
     *
     * @param upperLeft Point
     * @param width     double
     * @param height    double
     */
    public Block(Point upperLeft, double width, double height) {
        this(new Rectangle(upperLeft, width, height));
        this.hitListeners = new ArrayList<>();
    }

    /**
     * The method returns the "collision shape" of the object.
     *
     * @return the "collision shape" of the object.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
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
        double dx = currentVelocity.getDx(), dy = currentVelocity.getDy();
        double cX = collisionPoint.getX(), cY = collisionPoint.getY();
        // There are 4 options for collision (i.e. new velocity)
        if (cY <= this.rect.getUpperLeft().getY() + this.rect.getHeight() && cY >= this.rect.getUpperLeft().getY()) {
            // Left side
            if (cX == this.rect.getUpperLeft().getX()) {
                dx = -currentVelocity.getDx();
            }
            // Right side
            if (cX == this.rect.getUpperLeft().getX() + this.rect.getWidth()) {
                dx = -currentVelocity.getDx();
            }
        }
        if (cX <= this.rect.getUpperLeft().getX() + this.rect.getWidth() && cX >= this.rect.getUpperLeft().getX()) {
            // Upper side
            if (cY == this.rect.getUpperLeft().getY()) {
                dy = -currentVelocity.getDy();
            }
            // Lower side
            if (cY == this.rect.getUpperLeft().getY() + this.rect.getHeight()) {
                dy = -currentVelocity.getDy();
            }
        }
        if (dx != currentVelocity.getDx() || dy != currentVelocity.getDy()) {
            this.notifyHit(hitter);
        }
        return new Velocity(dx, dy);
    }


    /**
     * Draw this block in given surface.
     *
     * @param surface DrawSurface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        // ColorGenerator c = new ColorGenerator();
        int x = (int) this.getCollisionRectangle().getUpperLeft().getX();
        int y = (int) this.getCollisionRectangle().getUpperLeft().getY();
        int width = (int) this.getCollisionRectangle().getWidth();
        int height = (int) this.getCollisionRectangle().getHeight();
        // surface.setColor(c.getColor((int) y / height));
        surface.setColor(this.color);
        surface.fillRectangle(x, y, width, height);
        surface.setColor(Color.BLACK);
        surface.drawRectangle(x, y, width, height);
    }

    /**
     * notify the block that time has passed.
     */
    @Override
    public void timePassed() {
    }

    /**
     * Method to add this block to both collidableList and SpriteList.
     *
     * @param g Game
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
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
     * Set the block's color.
     *
     * @param c Color.
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * Method to remove this block from both collidableList and SpriteList.
     *
     * @param g Game
     */
    public void removeFromGame(GameLevel g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }

    /**
     * Notifiers all the registered HitListener objects when a hit occurs.
     *
     * @param hitter Ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl HitListener.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl HitListener.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
