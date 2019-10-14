import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AnimationArea extends JPanel {
    private Army redTeam;
    private Army blueTeam;
    private int armySize;
    private static int WINDOW_WIDTH = 715;
    private static int WINDOW_HEIGHT = 475;

    public AnimationArea(){
        super();
        armySize = 10;
        redTeam = new Army(1, armySize, WINDOW_WIDTH, WINDOW_HEIGHT);
        blueTeam = new Army(2, armySize, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    public void animate() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        for(int i = 0; i < this.armySize; i++){
            redTeam.getSoldier(i).move(blueTeam, redTeam);
            blueTeam.getSoldier(i).move(redTeam, blueTeam);
            redTeam.getSoldier(i).attack(blueTeam.getArmy());
            blueTeam.getSoldier(i).attack(redTeam.getArmy());
        }
    }

    public void paint(Graphics g){

        // clear the background
        g.clearRect(0, 0, this.getWidth(), this.getHeight());

        // draw the dot
        redTeam.drawArmy(g);
        blueTeam.drawArmy(g);

        this.repaint();	// make updated graphics visible

    }

    public Army getArmy(int team){
        if(team == 1){
            return redTeam;
        }
        else{
            return blueTeam;
        }
    }
}
