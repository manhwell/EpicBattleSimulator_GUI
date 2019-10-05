import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

/**
 * Archer() provides the generation and manipulation of an Archer for an Army.
 * @author C2C Manuel Riolo
 */
public class Archer extends Combatant {

    // Initializing variables
    private int range;
    private int accuracy;

    /**
     * Generates a new Archer class based on default variables.
     */
    public Archer(){
        super();
        this.setName("Archer");
        this.range = 75;
        this.accuracy = 22;
        this.setColor(this.getTeam());
    }

    /**
     * Generates a new Archer class based on passed variables.
     * @param team The team to base the Archer's color on.
     */
    public Archer(int team, int windowWidth, int windowHeight){
        // Setup an archer and give them a team color.
        super(team, windowWidth, windowHeight);
        this.setTeam(team);
        this.setName("Archer");
        this.range = 75;
        this.accuracy = 2;
        this.setColor(this.getTeam());
    }

    /**
     * Gets the range of an Archer.
     * @return The integer value of an archer's range.
     */
    public int getRange(){
        return this.range;
    }

    /**
     * Gets the accuracy of an Archer.
     * @return The integer value of an Archer's accuracy.
     */
    public int getAccuracy(){
        return this.accuracy;
    }

    /**
     * Sets the color on an Archer based off a team #
     * @param team The team # to base the color assignment off of.
     */
    public void setColor(int team){
        // Use team # to decide which color to get.
        if(this.getTeam() == 1){
            this.setColor(Color.orange);
        }
        else{
            this.setColor(Color.green);
        }
    }

    /**
     * Allows an Archer to attack based off a range around it.
     * @param enemyArmy The Army() for the enemy.
     * @throws UnsupportedAudioFileException For errors in the audio stream.
     * @throws IOException For a file not found
     * @throws LineUnavailableException For a part of the file not findable.
     */
    public void attack(Combatant[] enemyArmy) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        int accuracyToken = (int) (Math.random() * 99) + 1; // Chance of hitting your shot
        for(int i = 0; i < enemyArmy.length; i++) {
            if(this.getHealth() > 0 && enemyArmy[i].getHealth() > 0) { // Make sure both combatants are alive.
                // If the enemy is within your range, fire
                if (Math.abs(enemyArmy[i].getX() - this.getX()) <= this.getRange() &&
                        Math.abs(enemyArmy[i].getY() - this.getY()) <= this.getRange()) {
                    // If your accuracy is greater than the token, you hit.
                    if(this.getAccuracy() > accuracyToken) {
                        enemyArmy[i].damage(this.getStrength());
                    }
                }
            }
        }
    }
}
