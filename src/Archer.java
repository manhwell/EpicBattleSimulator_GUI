import java.awt.*;

public class Archer extends Combatant {

    private int range;
    private int accuracy;

    public Archer(){
        super();
        this.setName("Archer");
        this.range = 75;
        this.accuracy = 25;
        this.setColor(this.getTeam());
    }

    public Archer(int team){
        super(team);
        this.setTeam(team);
        this.setName("Archer");
        this.range = 75;
        this.accuracy = 1;
        this.setColor(this.getTeam());
    }

    public int getRange(){
        return this.range;
    }

    public int getAccuracy(){
        return this.accuracy;
    }

    public void setColor(int team){
        if(this.getTeam() == 1){
            this.setColor(Color.orange);
        }
        else{
            this.setColor(Color.green);
        }
    }

    public void attack(Combatant[] enemyArmy) {
        int accuracyToken = (int) (Math.random() * 99) + 1;
        for(int i = 0; i < enemyArmy.length; i++) {
            if(this.getHealth() > 0 && enemyArmy[i].getHealth() > 0) {
                if (Math.abs(enemyArmy[i].getX() - this.getX()) <= this.getRange() &&
                        Math.abs(enemyArmy[i].getY() - this.getY()) <= this.getRange()) {
                    if(this.getAccuracy() > accuracyToken) {
                        enemyArmy[i].damage(this.getStrength());
                    }
                }
            }
        }
    }
}
