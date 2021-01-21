import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-06-10
 */
public class Level3 implements LevelInformation {
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
        Ball left = new Ball(new Point(300, 500), 7, new Velocity(-2, -3), Color.WHITE);
        left.setEdgeColor(Color.BLACK);
        list.add(left);
        Ball right = new Ball(new Point(500, 500), 7, new Velocity(2, -3), Color.WHITE);
        right.setEdgeColor(Color.BLACK);
        list.add(right);
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
        return "Green 3";
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return background of the level.
     */
    @Override
    public SpriteCollection getBackground() {
        SpriteCollection collection = new SpriteCollection();
        // Background color
        Block block = new Block(new Point(0, 0), 800, 600);
        block.setColor(new Color(0, 100, 0));
        collection.addSprite(block);
        // Building
        // Main block
        Block mainB = new Block(new Point(70, 454), 96, 146);
        mainB.setColor(Color.BLACK);
        collection.addSprite(mainB);
        // Windows
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Block window = new Block(new Point(76 + 18 * i, 460 + 31 * j), 12, 25);
                window.setColor(Color.WHITE);
                collection.addSprite(window);
            }
        }
        // Antenna
        Block lower = new Block(new Point(103, 404), 30, 50);
        lower.setColor(Color.DARK_GRAY);
        collection.addSprite(lower);
        Block upper = new Block(new Point(113, 220), 10, 184);
        upper.setColor(Color.GRAY);
        collection.addSprite(upper);
        // Top
        Ball large = new Ball(new Point(118, 220), 15, Color.YELLOW);
        collection.addSprite(large);
        Ball med = new Ball(new Point(118, 220), 10, Color.orange);
        collection.addSprite(med);
        Ball small = new Ball(new Point(118, 220), 5, Color.WHITE);
        collection.addSprite(small);
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
        for (int i = 0; i < 5; i++) {
            for (int j = i; j < 10; j++) {
                Block block = new Block(new Point(300 + j * 50, 180 + i * 20), 50, 20);
                if (i == 0) {
                    block.setColor(Color.GRAY);
                } else if (i == 1) {
                    block.setColor(Color.RED);
                } else if (i == 2) {
                    block.setColor(Color.YELLOW);
                } else if (i == 3) {
                    block.setColor(Color.BLUE);
                } else {
                    block.setColor(Color.WHITE);
                }
                list.add(block);
            }
        }
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
