import java.util.ArrayList;
import java.util.List;

/**
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-05-01
 */
public class GameEnvironment {
    private List<Collidable> collidableList = new ArrayList<>();

    /**
     * Constructor.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<>();
    }

    /**
     * Add the given collidable to the environment.
     *
     * @param c Collidable
     */
    public void addCollidable(Collidable c) {
        this.collidableList.add(c);
    }

    /**
     * Remove the given collidable from the environment.
     *
     * @param c Collidable
     */
    public void removeCollidable(Collidable c) {
        this.collidableList.remove(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory Line
     * @return return the information about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Point> cPoints = new ArrayList<>();
        Rectangle thisRectangle;
        Point closestPoint;
        int index = -1;
        // Create a list of collision points
        for (int i = 0; i < this.collidableList.size(); i++) {
            thisRectangle = this.collidableList.get(i).getCollisionRectangle();
            closestPoint = trajectory.closestIntersectionToStartOfLine(thisRectangle);
            // If closestPoint is on the line
            if (closestPoint != null && trajectory.onLine(closestPoint)) {
                cPoints.add(trajectory.closestIntersectionToStartOfLine(thisRectangle));
            }
        }
        // If there are no collision points
        if (cPoints.isEmpty()) {
            return null;
        }
        // The collision point is the closest point to the start of the line
        Point collisionPoint = trajectory.closestToStart(cPoints);
        // Complete the rest of the collidableInfo of collidablePoint
        // i.e. search for Collidable of closestPoint
        for (int i = 0; i < this.collidableList.size(); i++) {
            thisRectangle = this.collidableList.get(i).getCollisionRectangle();
            closestPoint = trajectory.closestIntersectionToStartOfLine(thisRectangle);
            // If collisionPoint equals to closestPoint
            if (closestPoint != null && collisionPoint.equals(closestPoint)) {
                index = i;
                break;
            }
        }
        return new CollisionInfo(collisionPoint, this.collidableList.get(index));
    }
}