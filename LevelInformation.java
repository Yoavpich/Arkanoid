import java.util.List;

/**
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-06-10
 */
public interface LevelInformation {
    /**
     * Get how many balls is in the game.
     *
     * @return number of balls.
     */
    int numberOfBalls();

    /**
     * The initial of each ball.
     *
     * @return balls list.
     */
    List<Ball> initialBalls();

    /**
     * get the paddle speed.
     *
     * @return paddle speed.
     */
    int paddleSpeed();

    /**
     * get the paddle width.
     *
     * @return paddle width.
     */
    int paddleWidth();

    /**
     * Create the paddle.
     *
     * @return paddle.
     */
    Paddle createPaddle();

    /**
     * get the level name.
     * the level name will be displayed at the top of the screen.
     *
     * @return level name.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return background of the level.
     */
    SpriteCollection getBackground();

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return The Blocks that make up this level.
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size().
     *
     * @return Number of blocks that should be removed.
     */
    int numberOfBlocksToRemove();
}