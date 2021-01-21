/**
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-05-29
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Constructor.
     *
     * @param game         Game
     * @param removedBalls Counter.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    /**
     * Remove blocks that are hit.
     *
     * @param beingHit Block.
     * @param hitter   Ball.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
        // For tests:
        // System.out.println("Balls left: " + this.remainingBalls.getValue());
    }
}