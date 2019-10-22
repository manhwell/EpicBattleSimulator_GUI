import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * AnimationArea() provides a JPanel to display a battle simulation inside a JFrame.
 * @author C2C Manuel Riolo
 */
public class AnimationArea extends JPanel {

    // Initializing variables.
    private Battlefield myBattlefield;
    private int armySize;
    private int numArmies;
    private int armyStrength;
    private int speed;
    private static int WINDOW_WIDTH = 715;
    private static int WINDOW_HEIGHT = 475;
    private int gameOver = 0;

    /**
     * Creates a new AnimationArea with default values.
     */
    public AnimationArea(){
        // Initialize default values.
        super();
        this.armySize = 10;
        this.numArmies = 2;
        this.armyStrength = 35;
        this.speed = 7;
        // Create a battlefield with default values.
        myBattlefield = new Battlefield(this.numArmies, this.armySize, this.armyStrength, this.speed, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    /**
     * Animates a round on the battle.
     * @param g is the graphics that are being used for the simulation.
     * @return a boolean value for whether the battle is over.
     * @throws UnsupportedAudioFileException For errors in the audio stream.
     * @throws IOException For a file not found
     * @throws LineUnavailableException For a part of the file not findable.
     */
    public int animate(Graphics g) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        gameOver = myBattlefield.runRound(g); // Run one round and return if someone has won.
        return gameOver;
    }

    /**
     * Draws the battle animation to the pane.
     * @param g is the graphics that the animation is using.
     */
    public void paint(Graphics g){

        // clear the background
        g.clearRect(0, 0, this.getWidth(), this.getHeight());

        // draw the dot
        for(int i = 0; i < myBattlefield.getNumArmies(); i++){
            myBattlefield.getArmy(i).drawArmy(g);
        }

        this.repaint();	// make updated graphics visible

    }

    /**
     * Restarts the animation to its default values.
     */
    public void restart(){
        // Replace the current battlefield with a new one.
        myBattlefield = new Battlefield(this.numArmies, this.armySize, this.armyStrength, this.speed, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    /**
     * Gets the battlefield currently running.
     * @return the battlefield object that has the simulation status in it.
     */
    public Battlefield getBattlefield(){
        return this.myBattlefield;
    }

    /**
     * Sets the current number of armies to the number of armies on the battlefield.
     */
    public void setNumArmies(){
        this.myBattlefield.getNumArmies();
    }

    /**
     * Gets the window width of the simulation.
     * @return the simulation window width.
     */
    public int getWindowWidth(){
        return WINDOW_WIDTH;
    }

    /**
     * Gets the window height of the simulation.
     * @return the simulation window height.
     */
    public int getWindowHeight(){
        return WINDOW_HEIGHT;
    }
}
