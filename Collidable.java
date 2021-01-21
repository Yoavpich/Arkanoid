/**
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-04-24
 */
public interface Collidable {
    /**
     * The method returns the "collision shape" of the object.
     *
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     *
     * @param collisionPoint  Point
     * @param currentVelocity Velocity
     * @param hitter          Ball
     * @return new velocity expected after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
