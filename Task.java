/**
 * @param <T> generic.
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-06-24
 */
public interface Task<T> {
    /**
     * Run the task.
     *
     * @return T.
     */
    T run();
}