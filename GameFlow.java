import biuoop.KeyboardSensor;

import java.util.List;

/**
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-06-14
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter score;

    /**
     * Constructor.
     *
     * @param ar AnimationRunner
     */
    public GameFlow(AnimationRunner ar) {
        this.animationRunner = ar;
        this.keyboardSensor = this.animationRunner.getGui().getKeyboardSensor();
        this.score = new Counter();
    }

    /**
     * Run the game with the following levels.
     *
     * @param levels List<LevelInformation>.
     */
    public void runLevels(List<LevelInformation> levels) {
        HighestScoreFile highScores = new HighestScoreFile();
        int maxScore = 0;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.score, this.animationRunner);
            level.initialize();
            level.run();
            highScores.make(this.score.getValue()); // Update the highest score file.
            maxScore += levelInfo.numberOfBlocksToRemove() * 5 + 100;
            if (level.getRemainBalls() == 0) {
                break;
            }
        }
        if (this.score.getValue() == maxScore) { // If you won
            YouWinScreen screen = new YouWinScreen(this.score);
            animationRunner.run(new KeyPressStoppableAnimation(animationRunner.getGui().getKeyboardSensor(), screen));
        }
    }
}
