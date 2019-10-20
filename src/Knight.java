import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

/**
 * Knight() provides the generation and manipulation of a Knight for an Army.
 * @author C2C Manuel Riolo
 */
public class Knight extends Combatant{

    private int armour;

    /**
     * Generates a new Knight class based off default values.
     */
    public Knight(){
        super();
        this.armour = 15;
        this.setName("Knight");
    }

    /**
     * Generates a new Knight class based off passed values.
     * @param team The team # to base the Knights color on.
     */
    public Knight(int team, int power, int windowWidth, int windowHeight){
        super(team, power, windowWidth, windowHeight);
        this.setName("Knight");
        this.armour = 15;
    }

    /**
     * Gets the armour of a Knight.
     * @return Gets the integer value of a Knight's armour.
     */
    public int getArmour(){
        return this.armour;
    }

    /**
     * Sets the armour value of a Knight
     * @param armour The new armour value ot be set.
     */
    public void setArmour(int armour){
        this.armour = armour;
    }

    /**
     * Allows a knight to take damage with a reduction based on its armour.
     * @param damage The amount of damage to deal.
     * @throws UnsupportedAudioFileException For errors in the audio stream.
     * @throws IOException For a file not found
     * @throws LineUnavailableException For a part of the file not findable.
     */
    public void damage(int damage) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        damage = damage - this.getArmour(); // Nullify the damage based on armour.
        this.setHealth(this.getHealth() - damage); // Deal damage to this combatants health.
        // If they are killed from the attack, let 'em scream.
        if(this.getHealth() <= 0){
            AudioPlayer deathScream = new AudioPlayer("WilhelmScream.wav");
            deathScream.play();
        }
    }
}
