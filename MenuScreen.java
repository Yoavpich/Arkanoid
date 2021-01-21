import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-06-24
 */
public class MenuScreen implements Menu<Task<Void>> {
    private Map<String, String> keyMessage; // <Key, message>
    private Map<String, Task<Void>> keyReturnVal; // <key, returnVal>
    private AnimationRunner runner;
    private KeyboardSensor sensor;
    private String title;
    private boolean stop;

    /**
     * Constructor.
     *
     * @param title String.
     */
    public MenuScreen(String title) {
        this.title = title;
        this.runner = new AnimationRunner(new GUI("Arkanoid", 800, 600));
        this.sensor = this.runner.getGui().getKeyboardSensor();
        this.keyMessage = new TreeMap<>();
        this.keyReturnVal = new TreeMap<>();
        this.stop = false;
    }

    /**
     * Create another selection in the menu.
     *
     * @param key       String.
     * @param message   String.
     * @param returnVal T.
     */
    @Override
    public void addSelection(String key, String message, Task<Void> returnVal) {
        this.keyMessage.put(key, message);
        this.keyReturnVal.put(key, returnVal);
    }

    /**
     * Return the status of the menu
     * (if a key was pressed).
     *
     * @return status.
     */
    @Override
    public Task<Void> getStatus() {
        for (String key : this.keyReturnVal.keySet()) {
            if (this.sensor.isPressed(key)) {
                return this.keyReturnVal.get(key);
            }
        }
        return null;
    }

    /**
     * Get background blocks by sprite collection.
     *
     * @return background.
     */
    public SpriteCollection getBackgroundBlocks() {
        SpriteCollection blocks = new SpriteCollection();
        for (int i = 0; i < 7; i++) { // Rows
            for (int j = 0; j < 16; j++) { // Columns
                Block block = new Block(new Point(j * 50, i * 20), 50, 20);
                if (i == 0) {
                    block.setColor(Color.GRAY);
                } else if (i == 1) {
                    block.setColor(Color.RED);
                } else if (i == 2) {
                    block.setColor(Color.YELLOW);
                } else if (i == 3) {
                    block.setColor(Color.GREEN);
                } else if (i == 4) {
                    block.setColor(Color.LIGHT_GRAY);
                } else if (i == 5) {
                    block.setColor(Color.PINK);
                } else {
                    block.setColor(Color.CYAN);
                }
                blocks.addSprite(block);
            }
        }
        return blocks;
    }

    /**
     * Print background buttons.
     *
     * @param d DrawSurface.
     */
    public void printButtons(DrawSurface d) {
        int letterSize = 22;
        int width = 5 * d.getWidth() / 8;
        int height = 2 * letterSize;
        int extra = 10;
        int xValue = (d.getWidth() - width) / 2;
        int yValue = d.getHeight() / 3;
        for (String key : this.keyMessage.keySet()) {
            Block blockExtra = new Block(new Point(xValue - extra / 2, yValue - extra / 2),
                    width + extra, height + extra);
            blockExtra.setColor(Color.DARK_GRAY);
            blockExtra.drawOn(d);
            Block block = new Block(new Point(xValue, yValue),
                    width + extra, height + extra);
            block.setColor(Color.BLACK);
            block.drawOn(d);
            d.setColor(Color.WHITE);
            d.drawText(xValue + extra, yValue + 3 * letterSize / 2, this.keyMessage.get(key), letterSize);
            yValue += d.getHeight() / 8;
        }
    }

    /**
     * Proses one frame.
     *
     * @param d DrawSurface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        // Background
        getBackgroundBlocks().drawAllOn(d);
        // Buttons
        printButtons(d);
        // Game name (Arkanoid)
        int size = 90;
        int width = (d.getWidth() - size / 2 * this.title.length()) / 2;
        d.setColor(Color.BLACK);
        d.drawText(width, 4 * size / 3, this.title, size);
        d.drawText(width - 2, 4 * size / 3, this.title, size);
        d.drawText(width - 4, 4 * size / 3, this.title, size);
        if (getStatus() != null) {
            getStatus().run();
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

    /**
     * Get this animation runner.
     *
     * @return this animation runner.
     */
    public AnimationRunner getAnimationRunner() {
        return this.runner;
    }

    /**
     * Get this KeyboardSensor.
     *
     * @return this KeyboardSensor.
     */
    public KeyboardSensor getKeyboardSensor() {
        return this.sensor;
    }
}
