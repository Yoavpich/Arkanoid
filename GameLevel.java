import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-05-02
 */
public class GameLevel implements Animation {
    private LevelInformation level;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter remainBlocks;
    private Counter remainBalls;
    private Counter scoreBalance;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private ScoreTrackingListener scoreTracker;
    private ScoreIndicator scoreIndicator;
    private AnimationRunner runner;
    private boolean running;

    /**
     * Constructor.
     *
     * @param l  LevelInformation.
     * @param c  Counter.
     * @param ar AnimationRunner.
     */
    public GameLevel(LevelInformation l, Counter c, AnimationRunner ar) {
        this.level = l;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainBlocks = new Counter(this.level.numberOfBlocksToRemove());
        this.remainBalls = new Counter(this.level.numberOfBalls());
        this.scoreBalance = c;
        this.blockRemover = new BlockRemover(this, this.remainBlocks);
        this.ballRemover = new BallRemover(this, this.remainBalls);
        this.scoreTracker = new ScoreTrackingListener(this.scoreBalance);
        this.scoreIndicator = new ScoreIndicator(this.scoreBalance);
        this.scoreIndicator.setLevelName(this.level.levelName());
        this.runner = ar;
    }

    /**
     * Method to add a Collidable to GameEnvironment.
     *
     * @param c Collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Method to add a Sprite to SpriteCollection.
     *
     * @param s Sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Method to remove a Collidable from GameEnvironment.
     *
     * @param c Collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Method to remove a Sprite from SpriteCollection.
     *
     * @param s Sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Set the limits of the game.
     */
    public void initLimits() {
        // Initialize the death-region block
        Block deathRegion = new Block(new Point(0, 600), 800, 10);
        deathRegion.setColor(Color.BLACK);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(this.ballRemover);
        Block right = new Block(new Point(800, 0), 1, 600);
        right.setColor(Color.BLACK);
        right.addToGame(this);
        Block left = new Block(new Point(0, 0), 1, 600);
        left.setColor(Color.BLACK);
        left.addToGame(this);
        Block upper = new Block(new Point(0, 0), 800, 1);
        upper.setColor(Color.BLACK);
        upper.addToGame(this);
        Block upperLow = new Block(new Point(0, 30), 800, 1);
        upperLow.setColor(Color.BLACK);
        upperLow.addToGame(this);
        // Initialize the scoreIndicator block
        Block score = new Block(new Point(0, 0), 800, 30);
        score.setColor(Color.WHITE);
        score.addToGame(this);
    }

    /**
     * Set balls.
     */
    public void initBalls() {
        for (Ball ball : this.level.initialBalls()) {
            ball.setGameEnvironment(this.environment);
            this.addSprite(ball);
        }
    }

    /**
     * Set blocks for the game.
     */
    public void initBlocks() {
        for (Block block : this.level.blocks()) {
            block.addToGame(this);
            block.addHitListener(this.blockRemover);
            block.addHitListener(this.scoreTracker);
        }
    }

    /**
     * Set the paddle.
     */
    public void initPaddle() {
        Paddle paddle = this.level.createPaddle();
        paddle.addToGame(this);
        paddle.setKeyboard(this.runner.getGui().getKeyboardSensor());
    }

    /**
     * Set the background.
     */
    public void initBackground() {
        for (Sprite sprite : this.level.getBackground().getSprites()) {
            addSprite(sprite);
        }
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        // Initialize background
        initBackground();
        // Initialize bounds
        initLimits();
        // Initialize paddle
        initPaddle();
        // Initialize the blocks
        initBlocks();
        // Initialize balls
        initBalls();
        // Initialize scoreIndicator
        addSprite(this.scoreIndicator);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites)); // countdown before turn starts.
        this.running = true;
        this.runner.run(this);
    }

    /**
     * Process one frame.
     *
     * @param d DrawSurface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.remainBlocks.getValue() == 0) { // Out of blocks
            this.scoreBalance.increase(100);
            this.running = false;
            // this.runner.getGui().close();
        }
        if (this.remainBalls.getValue() == 0) { // Out of balls
            this.running = false;
            GameOverScreen screen = new GameOverScreen(this.scoreBalance);
            this.runner.run(new KeyPressStoppableAnimation(this.runner.getGui().getKeyboardSensor(), screen));
            // this.runner.getGui().close();
        }
        if (this.runner.getGui().getKeyboardSensor().isPressed("p")) {
            PauseScreen screen = new PauseScreen(this.scoreBalance);
            this.runner.run(new KeyPressStoppableAnimation(this.runner.getGui().getKeyboardSensor(), screen));
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
    }

    /**
     * Checks if the animation should stop.
     *
     * @return true if should stop, otherwise false.
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Get how many balls remain in the game.
     *
     * @return remain balls.
     */
    public int getRemainBalls() {
        return this.remainBalls.getValue();
    }
}