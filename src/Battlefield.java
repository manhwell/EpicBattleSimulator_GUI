import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Battlefield {
    List<Army> armiesOnField = new ArrayList<>();
    private int armySize;

    public Battlefield(){
        armiesOnField.add(new Army(0, 10, 715, 475));
        armiesOnField.add(new Army(1, 10, 715, 475));
        this.armySize = 10;
    }

    public Battlefield(int numArmies, int armySize, int windowWidth, int windowHeight){
        for(int i = 0; i < numArmies; i++){
            armiesOnField.add(new Army(i, armySize, windowWidth, windowHeight));
        }
        this.armySize = armySize;
    }

    public void draw(Graphics g){
        for(int i = 0; i < armiesOnField.size(); i++){
            armiesOnField.get(i).drawArmy(g);
        }
    }

    public void runRound(){
        for(int i = 1; i < this.armySize; i++){
            for(int j = 0; j < armiesOnField.size(); i++){
                armiesOnField.get(j).getSoldier(i).move();
            }
        }
    }
}
