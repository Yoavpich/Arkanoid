import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-05-02
 */
public class SpriteCollection {
    private List<Sprite> spriteList;

    /**
     * Constructor.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<>();
    }

    /**
     * Add the given Sprite to the list.
     *
     * @param s Sprite
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * Remove the given Sprite from the list.
     *
     * @param s Sprite
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }

    /**
     * Get sprites list.
     *
     * @return sprites list.
     */
    public List<Sprite> getSprites() {
        return this.spriteList;
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        // Make a copy of the hitListeners before iterating over them.
        List<Sprite> sprites = new ArrayList<>(this.spriteList);
        for (Sprite sprite : sprites) {
            sprite.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d DrawSurface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.spriteList) {
            sprite.drawOn(d);
        }
    }
}