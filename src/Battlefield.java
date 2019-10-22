import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Battlefield() provides the aspects of a battle to run the animation.
 * @author C2C Manuel Riolo
 */
public class Battlefield {

    // Initializing variables.
    private List<Army> armiesOnField = new ArrayList<>();
    private List<Combatant> enemiesOnField = new ArrayList<>();
    private int armySize;
    private int windowWidth;
    private int windowHeight;

    /**
     * Creates a new Battlefield object based on default values.
     */
    public Battlefield(){
        this.armiesOnField.add(new Army(0, 10, 25, 2, 715, 475));
        this.armiesOnField.add(new Army(1, 10, 25, 2, 715, 475));
        this.armySize = 10;
    }

    /**
     * Creates a new Battlefield object based on passed values.
     * @param numArmies is the number of armies on the battlefield.
     * @param armySize is the size fo teh armies on the battlefield.
     * @param power is the power of armies on the battlefield.
     * @param windowWidth is the width on the battlefield.
     * @param windowHeight is the height of the battlefield
     */
    public Battlefield(int numArmies, int armySize, int power, int speed, int windowWidth, int windowHeight){
        this.armySize = armySize;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        for(int i = 0; i < numArmies; i++){ // For however many armies are passed, create them based on other passed values.
            this.armiesOnField.add(new Army(i, this.armySize, power, speed, this.windowWidth, this.windowHeight));
        }
    }

    /**
     * Gets an army based on passed values.
     * @param armyNum is the index number of the army selected.
     * @return The army selected at the index value selected.
     */
    public Army getArmy(int armyNum){
        return this.armiesOnField.get(armyNum);
    }

    /**
     * Gets the number of armies on the battlefield.
     * @return the number of armies on the battlefield.
     */
    public int getNumArmies(){
        return this.armiesOnField.size();
    }

    /**
     * Runs a round of the battle.
     * @param g is the graphics the battlefield is running on.
     * @return if the battle is over or not.
     * @throws UnsupportedAudioFileException For errors in the audio stream.
     * @throws IOException For a file not found
     * @throws LineUnavailableException For a part of the file not findable.
     */
    public int runRound(Graphics g) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        for(int currArmy = 0; currArmy < this.getNumArmies(); currArmy++){ // Go through each army
            for(int combatant = 0; combatant < this.getArmiesOnField().get(currArmy).getArmySize(); combatant++){ // Go through each combatant
                for(int k = 0; k < this.armiesOnField.size(); k++){
                    if(k != currArmy){ // For each army that isn't your own, add its combatants to the list of enemies.
                        for(int z = 0; z < this.getArmy(k).getArmySize(); z++) {
                            this.enemiesOnField.add(this.getArmy(k).getSoldier(z));
                        }
                    }
                }
                this.armiesOnField.get(currArmy).getSoldier(combatant).move((ArrayList<Combatant>) this.enemiesOnField, this.getArmy(currArmy)); // Move
                this.armiesOnField.get(currArmy).getSoldier(combatant).attack((ArrayList<Combatant>) this.enemiesOnField); // Attack
                this.enemiesOnField.clear(); // reset the array of enemies for the next combatant to fill it.
            }
        }
        for(int i = 0; i < this.getNumArmies(); i++) { // Check to see if an army is dead.
            if(this.getArmy(i).checkDead() >= this.getArmy(i).getArmySize()){
                this.armiesOnField.remove(this.armiesOnField.get(i)); // If the army has no combatants alive, remove it from battle.
            }
        }
        if(this.armiesOnField.size() == 1){
            return 1; // If only one army is alive, they win, end the simulation.
        }
        else return 0;
    }

    /**
     * Gets all the armies on the battlefield.
     * @return all the armies on the battlefield.
     */
    public List<Army> getArmiesOnField() {
        return armiesOnField;
    }
}
