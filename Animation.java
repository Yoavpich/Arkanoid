import biuoop.DrawSurface;

/**
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-06-06
 */
public interface Animation {
    /**
     * Proses one frame.
     *
     * @param d DrawSurface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * Checks if the animation should stop.
     *
     * @return true if should stop, otherwise false.
     */
    boolean shouldStop();
}