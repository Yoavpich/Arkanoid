/**
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-05-29
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructor.
     *
     * @param game          Game
     * @param removedBlocks Counter.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Remove blocks that are hit.
     *
     * @param beingHit Block.
     * @param hitter   Ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}