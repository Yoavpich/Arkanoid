/**
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-05-30
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor.
     *
     * @param scoreCounter Counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Return the value of currentScore.
     *
     * @return currentScore.
     */
    public Counter getCurrentScore() {
        return this.currentScore;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit Block.
     * @param hitter   Ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}