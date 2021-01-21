import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-06-06
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Constructor.
     *
     * @param gui             Gui
     * @param framesPerSecond Int.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
        this.sleeper = new Sleeper();
    }

    /**
     * Default constructor.
     *
     * @param gui GUI
     */
    public AnimationRunner(GUI gui) {
        this(gui, 60);
    }

    /**
     * Get this gui.
     *
     * @return gui.
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * Run the game -- start the animation loop.
     *
     * @param animation Animation.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();

            animation.doOneFrame(d);

            this.getGui().show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}