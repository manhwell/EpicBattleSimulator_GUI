import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Battlefield {
    private List<Army> armiesOnField = new ArrayList<>();
    private List<Combatant> enemiesOnField = new ArrayList<>();
    private int armySize;
    private int windowWidth;
    private int windowHeight;

    public Battlefield(){
        this.armiesOnField.add(new Army(0, 10, 715, 475));
        this.armiesOnField.add(new Army(1, 10, 715, 475));
        this.armySize = 10;
    }

    public Battlefield(int numArmies, int armySize, int windowWidth, int windowHeight){
        for(int i = 0; i < numArmies; i++){
            this.armiesOnField.add(new Army(i, armySize, windowWidth, windowHeight));
        }
        this.armySize = armySize;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    public void draw(Graphics g){
        for(int i = 0; i < this.armiesOnField.size(); i++){
            this.armiesOnField.get(i).drawArmy(g);
        }
    }

    public Army getArmy(int armyNum){
        return this.armiesOnField.get(armyNum);
    }

    public int getNumArmies(){
        return this.armiesOnField.size();
    }

    public int runRound(Graphics g) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        int numArmiesDead = 0;
        for(int combatant = 0; combatant < this.armySize; combatant++){ // Go through each combatant
            for(int currArmy = 0; currArmy < this.getNumArmies(); currArmy++){ // Go through each army
                for(int k = 0; k < this.armiesOnField.size(); k++){
                    if(k != currArmy){
                        for(int z = 0; z < this.getArmy(k).getArmySize(); z++) {
                            this.enemiesOnField.add(this.getArmy(k).getSoldier(z));
                        }
                    }
                }
                this.armiesOnField.get(currArmy).getSoldier(combatant).move((ArrayList<Combatant>) this.enemiesOnField, this.getArmy(currArmy));
                this.armiesOnField.get(currArmy).getSoldier(combatant).attack((ArrayList<Combatant>) this.enemiesOnField);
                this.enemiesOnField.clear();
            }
        }
        for(int i = 0; i < this.getNumArmies(); i++) {
            if(this.getArmy(i).checkDead() >= this.armySize){
                numArmiesDead++;
            }
        }
        if(numArmiesDead == this.getNumArmies() - 1){
            return 1;
        }
        else return 0;
    }

    public List<Army> getArmiesOnField() {
        return armiesOnField;
    }

    public void addArmy(){
        if(this.getNumArmies() < 4) {
            this.armiesOnField.add(new Army(2, this.armySize, this.windowWidth, this.windowHeight));
        }
    }
}
