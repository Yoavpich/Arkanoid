import java.util.ArrayList;
import java.util.List;

/**
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-06-17
 */
public class PlayGame {
    /**
     * Main method.
     *
     * @param args String[]
     */
    public static void main(String[] args) {
        MenuScreen menuScreen = new MenuScreen("Arkanoid");
        GameFlow game = new GameFlow(menuScreen.getAnimationRunner());
        // Create Levels
        List<LevelInformation> levels = new ArrayList<>();
        for (String str : args) {
            try {
                int level = Integer.parseInt(str);
                if (level == 1) {
                    levels.add(new Level1());
                } else if (level == 2) {
                    levels.add(new Level2());
                } else if (level == 3) {
                    levels.add(new Level3());
                } else if (level == 4) {
                    levels.add(new Level4());
                }
            } catch (Exception ignored) { // if level is not a number.
            }
        }
        if (levels.isEmpty()) { // Default levels
            levels.add(new Level1());
            levels.add(new Level2());
            levels.add(new Level3());
            levels.add(new Level4());
        }
        // Add menu options
        menuScreen.addSelection("s", "Press \"s\" to start a new game",
                new Task<Void>() {
                    /**
                     * Run the task.
                     *
                     * @return T.
                     */
                    @Override
                    public Void run() {
                        game.runLevels(levels);
                        return null;
                    }
                });
        menuScreen.addSelection("h", "Press \"h\" to see the highest score",
                new Task<Void>() {
                    /**
                     * Run the task.
                     *
                     * @return T.
                     */
                    @Override
                    public Void run() {
                        HighScoreScreen screen = new HighScoreScreen();
                        menuScreen.getAnimationRunner().run(
                                new KeyPressStoppableAnimation(menuScreen.getKeyboardSensor(), screen));
                        return null;
                    }
                });
        menuScreen.addSelection("q", "Press \"q\" to quit",
                new Task<Void>() {
                    /**
                     * Run the task.
                     *
                     * @return T.
                     */
                    @Override
                    public Void run() {
                        System.exit(0);
                        return null;
                    }
                });
        while (true) {
            menuScreen.getAnimationRunner().run(menuScreen);
        }
    }
}
