import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {

    private static String filePath;
    // to store current position
    private Long currentFrame;
    private Clip clip;

    // current status of clip
    private String status;

    private AudioInputStream audioInputStream;

    // constructor to initialize streams and clip
    public AudioPlayer(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // create AudioInputStream object
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

        // create clip reference
        clip = AudioSystem.getClip();

        // open audioInputStream to the clip
        clip.open(audioInputStream);

        //clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    // Method to play the audio
    public void play() {
        //start the clip
        clip.start();

        this.status = "play";
    }

    // Method to stop the audio
    public void stop() {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }

    // Method to reset audio stream
    public void resetAudioStream() {
        clip.setFramePosition(0);
    }

}
