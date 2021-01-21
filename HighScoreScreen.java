import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-06-17
 */
public class HighScoreScreen implements Animation {
    private int score;
    private boolean stop;

    /**
     * Constructor.
     */
    public HighScoreScreen() {
        this.score = new HighestScoreFile().getHighestScore();
        this.stop = false;
    }

    /**
     * Proses one frame.
     *
     * @param d DrawSurface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        String message = "Highest score: ";
        // Print message
        int size = 75;
        int width = (d.getWidth() - (size - 12) / 2 * message.length()) / 2;
        d.setColor(Color.BLACK);
        d.drawText(width, d.getHeight() / 4, message, size);
        d.setColor(Color.DARK_GRAY);
        d.drawText(width - 2, d.getHeight() / 4, message, size);
        d.setColor(Color.GRAY);
        d.drawText(width - 4, d.getHeight() / 4, message, size);
        // Print score
        size = 50;
        width = (d.getWidth() - 7 * message.length()) / 2;
        d.setColor(Color.BLACK);
        d.drawText(width, d.getHeight() / 2, Integer.toString(this.score), size);
        d.drawText(width - 1, d.getHeight() / 2, Integer.toString(this.score), size);
        // Print continue...
        size = 20;
        String pressTo = "Press space to continue";
        width = (d.getWidth() - (size - 2) / 2 * pressTo.length()) / 2;
        d.drawText(width, 7 * d.getHeight() / 8, pressTo, size);
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