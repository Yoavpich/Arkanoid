import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-06-13
 */
public class Level4 implements LevelInformation {
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
        // Left
        Ball left = new Ball(new Point(300, 500), 7, new Velocity(-2, -3), Color.WHITE);
        left.setEdgeColor(Color.BLACK);
        list.add(left);
        // Right
        Ball right = new Ball(new Point(500, 500), 7, new Velocity(2, -3), Color.WHITE);
        right.setEdgeColor(Color.BLACK);
        list.add(right);
        // Middle
        Ball middle = new Ball(new Point(400, 400), 7, new Velocity(3, -3), Color.WHITE);
        middle.setEdgeColor(Color.BLACK);
        list.add(middle);
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
        return "Final Four";
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
        block.setColor(new Color(0, 128, 255));
        collection.addSprite(block);
        // Lines
        for (int i = 0; i < 10; i++) {
            Line leftLine = new Line(new Point(70 + 10 * i, 400), new Point(50 + 10 * i, 600));
            leftLine.setColor(Color.WHITE);
            collection.addSprite(leftLine);
            Line rightLine = new Line(new Point(620 + 10 * i, 500), new Point(600 + 10 * i, 600));
            rightLine.setColor(Color.WHITE);
            collection.addSprite(rightLine);
        }
        // Left cloud
        Ball ball1 = new Ball(new Point(150, 385), 32, new Color(140, 140, 140));
        collection.addSprite(ball1);
        Ball ball2 = new Ball(new Point(110, 370), 30, new Color(170, 170, 170));
        collection.addSprite(ball2);
        Ball ball3 = new Ball(new Point(136, 400), 25, new Color(180, 180, 180));
        collection.addSprite(ball3);
        Ball ball4 = new Ball(new Point(95, 405), 19, new Color(150, 150, 150));
        collection.addSprite(ball4);
        Ball ball5 = new Ball(new Point(77, 390), 21, new Color(160, 160, 160));
        collection.addSprite(ball5);
        // Right cloud
        Ball ball6 = new Ball(new Point(700, 485), 32, new Color(140, 140, 140));
        collection.addSprite(ball6);
        Ball ball7 = new Ball(new Point(660, 470), 30, new Color(170, 170, 170));
        collection.addSprite(ball7);
        Ball ball8 = new Ball(new Point(686, 500), 25, new Color(180, 180, 180));
        collection.addSprite(ball8);
        Ball ball9 = new Ball(new Point(645, 505), 19, new Color(150, 150, 150));
        collection.addSprite(ball9);
        Ball ball10 = new Ball(new Point(627, 490), 21, new Color(160, 160, 160));
        collection.addSprite(ball10);
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
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 16; j++) {
                Block block = new Block(new Point(j * 50, 100 + i * 20), 50, 20);
                if (i == 0) {
                    block.setColor(Color.GRAY);
                } else if (i == 1) {
                    block.setColor(Color.RED);
                } else if (i == 2) {
                    block.setColor(Color.YELLOW);
                } else if (i == 3) {
                    block.setColor(Color.GREEN);
                } else if (i == 4) {
                    block.setColor(Color.LIGHT_GRAY);
                } else if (i == 5) {
                    block.setColor(Color.PINK);
                } else {
                    block.setColor(Color.CYAN);
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
