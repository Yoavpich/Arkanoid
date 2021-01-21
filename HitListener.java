/**
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-05-27
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit Block.
     * @param hitter   Ball.
     */
    void hitEvent(Block beingHit, Ball hitter);
}