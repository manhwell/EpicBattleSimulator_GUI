import java.awt.*;

public class Knight extends Combatant{
    private int armour;


    public Knight(){
        super();
        this.armour = 15;
        this.setName("Knight");
    }

    public Knight(int team){
        super(team);
        this.setName("Knight");
        this.armour = 15;
        this.setTeam(team);
        this.setColor(this.getTeam());
    }

    public int getArmour(){
        return this.armour;
    }

    public void setArmour(int armour){
        this.armour = armour;
    }

    public void setColor(int team){
        if(this.getTeam() == 1){
            this.setColor(Color.pink);
        }
        else{
            this.setColor(Color.magenta);
        }
    }

    public void damage(int damage){
        damage = damage - this.getArmour();
        this.setHealth(this.getHealth() - damage);
    }
}
