import biuoop.DrawSurface;

/**
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-05-03
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d DrawSurface
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * Method to add this sprite to both collidableList and SpriteList (if necessary).
     *
     * @param g Game
     */
    void addToGame(GameLevel g);

    /**
     * Get the sprite's color.
     *
     * @return color.
     */
    java.awt.Color getColor();
}