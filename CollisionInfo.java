/**
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-05-01
 */
public class CollisionInfo {
    private Point point;
    private Collidable collidable;

    /**
     * Constructor.
     *
     * @param point      Point
     * @param collidable Collidable
     */
    public CollisionInfo(Point point, Collidable collidable) {
        this.point = point;
        this.collidable = collidable;
    }

    /**
     * Method to get the collision point.
     *
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.point;
    }

    /**
     * Method to get the collision object.
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collidable;
    }
}
