import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-06-14
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private Animation animation;
    private String key;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Constructor.
     *
     * @param sensor KeyboardSensor.
     * @param anim   Animation.
     * @param k      String.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, Animation anim, String k) {
        this.keyboard = sensor;
        this.animation = anim;
        this.key = k;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * Constructor.
     *
     * @param sensor KeyboardSensor.
     * @param anim   Animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, Animation anim) {
        this(sensor, anim, "space");
    }

    /**
     * Proses one frame.
     *
     * @param d DrawSurface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.key.equals("space") && this.keyboard.isPressed(KeyboardSensor.SPACE_KEY) && !this.isAlreadyPressed) {
            this.stop = true;
        }
        if (this.keyboard.isPressed(this.key) && !this.isAlreadyPressed) {
            this.stop = true;
        }
        if (this.isAlreadyPressed) {
            this.isAlreadyPressed = false;
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
