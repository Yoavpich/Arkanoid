import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-06-17
 */
public class YouWinScreen implements Animation {
    private Counter score;
    private boolean stop;

    /**
     * Constructor.
     *
     * @param s Counter.
     */
    public YouWinScreen(Counter s) {
        this.score = s;
        this.stop = false;
    }

    /**
     * Proses one frame.
     *
     * @param d DrawSurface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        String message = "You Win!";
        // Print message
        int size = 75;
        int width = (d.getWidth() - (size + 4) / 2 * message.length()) / 2;
        d.setColor(Color.BLACK);
        d.drawText(width, d.getHeight() / 2, message, size);
        d.setColor(Color.DARK_GRAY);
        d.drawText(width - 2, d.getHeight() / 2, message, size);
        d.setColor(Color.GRAY);
        d.drawText(width - 4, d.getHeight() / 2, message, size);
        // Print score
        size = 18;
        d.setColor(Color.BLACK);
        String yourScore = "Final score: " + this.score.getValue();
        d.drawText(1, size, yourScore, size);
        // Print continue...
        size = 20;
        String pressTo = "Press space to continue";
        width = (d.getWidth() - (size - 2) / 2 * pressTo.length()) / 2;
        d.drawText(width, 3 * d.getHeight() / 4, pressTo, size);
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