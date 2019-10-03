import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Main {

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int ARMY_SIZE = 10;

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Battle myBattle = new Battle(WINDOW_WIDTH, WINDOW_HEIGHT, ARMY_SIZE);
        myBattle.runBattle();
    }
}
