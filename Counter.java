/**
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-05-29
 */
public class Counter {
    private int count;

    /**
     * Constructor.
     *
     * @param num int
     */
    public Counter(int num) {
        this.count = num;
    }

    /**
     * Default constructor.
     */
    public Counter() {
        this(0);
    }

    /**
     * add number to current count.
     *
     * @param number int
     */
    void increase(int number) {
        this.count += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number int
     */
    void decrease(int number) {
        this.count -= number;
    }

    /**
     * get current count.
     *
     * @return counter value.
     */
    int getValue() {
        return this.count;
    }
}