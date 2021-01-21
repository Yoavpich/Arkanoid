import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-06-10
 */
public class Level1 implements LevelInformation {
    /**
     * Get how many balls is in the game.
     *
     * @return number of balls.
     */
    @Override
    public int numberOfBalls() {
        return initialBalls().size();
    }

    /**
     * The initial of each ball.
     *
     * @return balls list.
     */
    @Override
    public List<Ball> initialBalls() {
        List<Ball> list = new ArrayList<>();
        list.add(new Ball(new Point(400, 500), 7, new Velocity(0, -3), Color.WHITE)); // Ball1
        return list;
    }

    /**
     * get the paddle speed.
     *
     * @return paddle speed.
     */
    @Override
    public int paddleSpeed() {
        return 5;
    }

    /**
     * get the paddle width.
     *
     * @return paddle width.
     */
    @Override
    public int paddleWidth() {
        return 80;
    }

    /**
     * Create the paddle.
     *
     * @return paddle.
     */
    @Override
    public Paddle createPaddle() {
        return new Paddle(new Point(360, 585), paddleWidth(), 10, paddleSpeed());
    }

    /**
     * get the level name.
     * the level name will be displayed at the top of the screen.
     *
     * @return level name.
     */
    @Override
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return background of the level.
     */
    @Override
    public SpriteCollection getBackground() {
        SpriteCollection collection = new SpriteCollection();
        // Create background color
        Block backgroundColor = new Block(new Point(0, 0), 800, 600);
        backgroundColor.setColor(Color.BLACK);
        collection.addSprite(backgroundColor);
        // Create the circles
        for (int i = 0; i < 3; i++) {
            Ball ball = new Ball(new Point(400, 150), 90 - 20 * i, Color.BLACK);
            ball.setEdgeColor(Color.BLUE);
            ball.setVelocity(0, 0);
            collection.addSprite(ball);
        }
        // Create the lines
        collection.addSprite(new Line(280, 150, 520, 150, Color.BLUE));
        collection.addSprite(new Line(400, 30, 400, 280, Color.BLUE));
        return collection;
    }

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return The Blocks that make up this level.
     */
    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        Block block = new Block(new Point(390, 140), 20, 20);
        block.setColor(Color.RED);
        list.add(block);
        return list;
    }

    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size().
     *
     * @return Number of blocks that should be removed.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
