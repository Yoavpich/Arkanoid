/**
 * @param <T> Type.
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-06-24
 */
public interface Menu<T> extends Animation {
    /**
     * Create another selection in the menu.
     *
     * @param key       String.
     * @param message   String.
     * @param returnVal T.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * Return the status of the menu
     * (if a key was pressed).
     *
     * @return status.
     */
    T getStatus();
}