import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Main() provides a platform to launch battles off of.
 * Documentation: None, outside of those outlined in AudioPlayer.java
 * @author C2C Manuel Riolo
 */
public class Main {

    // Set initial variables
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int ARMY_SIZE = 10;

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // Initialize and run the battle
        Battle myBattle = new Battle(WINDOW_WIDTH, WINDOW_HEIGHT, ARMY_SIZE);
        myBattle.runBattle();
    }
}
