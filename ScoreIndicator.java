import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-05-30
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private String levelName;

    /**
     * Constructor.
     *
     * @param c Counter
     */
    public ScoreIndicator(Counter c) {
        this.score = c;
    }

    /**
     * Set a level name.
     *
     * @param name String.
     */
    public void setLevelName(String name) {
        this.levelName = name;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        String scoreBalance = "Score: " + this.score.getValue();
        int x = d.getWidth() / 2 - scoreBalance.length() - 30;
        d.setColor(Color.BLACK);
        d.drawText(x, 23, scoreBalance, 20);
        String name = "Level Name: " + this.levelName;
        d.drawText(x + 170, 23, name, 20);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }

    /**
     * Method to add this sprite to both collidableList and SpriteList (if necessary).
     *
     * @param g Game
     */
    @Override
    public void addToGame(GameLevel g) {
    }

    /**
     * Get the sprite's color.
     *
     * @return color.
     */
    @Override
    public Color getColor() {
        return null;
    }
}
