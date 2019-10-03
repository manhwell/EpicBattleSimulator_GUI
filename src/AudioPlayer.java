/*
 * Code based off example found at https://www.geeksforgeeks.org/play-audio-file-using-java/
 * Used as a base, modified slightly to fit my needs.
 */
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * AudioPlayer() provides capabilities to play a .wav file.
 * @author C2C Manuel Riolo
 * @author https://www.geeksforgeeks.org/play-audio-file-using-java/
 */
public class AudioPlayer {

    // Path to the .wav file to be used.
    private static String filePath;

    // to store current position
    private Long currentFrame;
    private Clip clip;

    // current status of clip
    private String status;

    private AudioInputStream audioInputStream;

    /**
     * Generates a new audio player for a certain audio file.
     * @param filePath The file to play.
     * @throws UnsupportedAudioFileException For errors in the audio stream.
     * @throws IOException For a file not found
     * @throws LineUnavailableException For a part of the file not findable.
     */
    public AudioPlayer(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // create AudioInputStream object
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

        // create clip reference
        clip = AudioSystem.getClip();

        // open audioInputStream to the clip
        clip.open(audioInputStream);
    }

    /**
     * Starts an audio stream.
     */
    public void play() {
        //start the clip
        clip.start();

        this.status = "play";
    }

    /**
     * Stops an audio stream.
     */
    public void stop() {
        currentFrame = 0L; // Stop frame
        clip.stop();
        clip.close();
    }
}
