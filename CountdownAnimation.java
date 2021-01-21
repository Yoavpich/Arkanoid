import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/*
 The CountdownAnimation will display the given gameScreen,
 for numOfSeconds seconds, and on top of them it will show
 a countdown from countFrom back to 1, where each number will
 appear on the screen for (numOfSeconds / countFrom) seconds, before
 it is replaced with the next one.
 */

/**
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-06-09
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private int countLeft;
    private SpriteCollection gameScreen;
    private boolean stop;
    private Sleeper sleeper;

    /**
     * Constructor.
     *
     * @param numOfSeconds double.
     * @param countFrom    int.
     * @param gameScreen   SpriteCollection.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.countLeft = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.sleeper = new Sleeper();
    }

    /**
     * Proses one frame.
     *
     * @param d DrawSurface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.WHITE);
        d.drawText(d.getWidth() / 2 - 8, d.getHeight() / 2, Integer.toString(this.countLeft), 32);
        this.sleeper.sleepFor((long) (1000 * this.numOfSeconds / this.countFrom));
        if (this.countLeft-- == 0) {
            this.stop = true;
        }
    }

    /**
     * Checks if the animation should stop.
     *
     * @return true if should stop, otherwise false.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}