import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-06-13
 */
public class Level2 implements LevelInformation {
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
        for (int i = 0; i < 5; i++) {
            Ball ballLeft = new Ball(new Point(300 - 30 * i, 350 + 15 * i), 7, Color.WHITE);
            ballLeft.setEdgeColor(Color.BLACK);
            ballLeft.setVelocity(-i, -3);
            list.add(ballLeft);
            Ball ballRight = new Ball(new Point(500 + 30 * i, 350 + 15 * i), 7, Color.WHITE);
            ballRight.setEdgeColor(Color.BLACK);
            ballRight.setVelocity(i, -3);
            list.add(ballRight);
        }
        return list;
    }

    /**
     * get the paddle speed.
     *
     * @return paddle speed.
     */
    @Override
    public int paddleSpeed() {
        return 3;
    }

    /**
     * get the paddle width.
     *
     * @return paddle width.
     */
    @Override
    public int paddleWidth() {
        return 500;
    }

    /**
     * Create the paddle.
     *
     * @return paddle.
     */
    @Override
    public Paddle createPaddle() {
        return new Paddle(new Point(150, 585), paddleWidth(), 10, paddleSpeed());
    }

    /**
     * get the level name.
     * the level name will be displayed at the top of the screen.
     *
     * @return level name.
     */
    @Override
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return background of the level.
     */
    @Override
    public SpriteCollection getBackground() {
        SpriteCollection collection = new SpriteCollection();
        // Initialize lines
        for (int i = 0; i < 110; i++) {
            Line line = new Line(new Point(150, 200), new Point(i * 6, 300));
            line.setColor(new Color(255, 175, 0));
            collection.addSprite(line);
        }
        // Initialize sun
        Ball large = new Ball(new Point(150, 200), 60, new Color(255, 255, 153));
        collection.addSprite(large);
        Ball med = new Ball(new Point(150, 200), 52, new Color(255, 204, 0));
        collection.addSprite(med);
        Ball small = new Ball(new Point(150, 200), 44, new Color(255, 153, 0));
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
        for (int i = 0; i < 16; i++) {
            Block block = new Block(new Point(50 * i, 300), 50, 20);
            if (i > 13) {
                block.setColor(Color.CYAN);
            } else if (i > 11) {
                block.setColor(Color.PINK);
            } else if (i > 8) {
                block.setColor(Color.BLUE);
            } else if (i > 5) {
                block.setColor(Color.GREEN);
            } else if (i > 3) {
                block.setColor(Color.YELLOW);
            } else if (i > 1) {
                block.setColor(Color.ORANGE);
            } else {
                block.setColor(Color.RED);
            }
            list.add(block);
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
