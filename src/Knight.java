import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

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

    public void damage(int damage) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        damage = damage - this.getArmour();
        this.setHealth(this.getHealth() - damage);
        if(this.getHealth() <= 0){
            AudioPlayer deathScream = new AudioPlayer("WilhelmScream.wav");
            deathScream.play();
        }
    }
}
