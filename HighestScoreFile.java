import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Yoav Pichoto
 * @version 1.0
 * @since 2020-06-24
 */
public class HighestScoreFile {
    private final File highScores = new File("highscores.txt");

    /**
     * Make / update the highscores file.
     *
     * @param currentScore int.
     */
    public void make(int currentScore) {
        try {
            // If the file already exist
            boolean isNotExist = this.highScores.createNewFile();
            if (isNotExist) { // We should create a new file
                BufferedWriter writer = new BufferedWriter(new FileWriter(this.highScores));
                writer.write("The highest score so far is: " + currentScore);
                writer.close();
            } else { // We should check which score is greater
                // First lets get the previous highest score
                BufferedReader reader = new BufferedReader(new FileReader(this.highScores));
                String text = reader.readLine();
                reader.close();
                // Get the highest score so far (the last word in the sentence)
                String scoreStr = text.substring(text.lastIndexOf(" ") + 1);
                int highestScore = Integer.parseInt(scoreStr);
                if (currentScore > highestScore) { // Change the highest score
                    BufferedWriter writer = new BufferedWriter(new FileWriter(this.highScores));
                    writer.write("The highest score so far is: " + currentScore);
                    writer.close();
                }
            }
        } catch (IOException ignored) {
        }
    }

    /**
     * Get the highest score so far.
     *
     * @return the highest score so far.
     */
    public int getHighestScore() {
        int highestScore = 0;
        try {
            // First lets get the previous highest score
            BufferedReader reader = new BufferedReader(new FileReader(this.highScores));
            String text = reader.readLine();
            reader.close();
            // Get the highest score so far (the last word in the sentence)
            String scoreStr = text.substring(text.lastIndexOf(" ") + 1);
            highestScore = Integer.parseInt(scoreStr);
        } catch (IOException ignored) {
        }
        return highestScore;
    }
}
