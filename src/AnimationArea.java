import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AnimationArea extends JPanel {
    private Battlefield myBattlefield;
    private int armySize;
    private int numArmies;
    private static int WINDOW_WIDTH = 715;
    private static int WINDOW_HEIGHT = 475;
    private int gameOver = 0;

    public AnimationArea(){
        super();
        armySize = 10;
        numArmies = 4;
        myBattlefield = new Battlefield(numArmies, armySize, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    public int animate(Graphics g) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        gameOver = myBattlefield.runRound(g);
        return gameOver;
    }

    public void paint(Graphics g){

        // clear the background
        g.clearRect(0, 0, this.getWidth(), this.getHeight());

        // draw the dot
        for(int i = 0; i < myBattlefield.getNumArmies(); i++){
            myBattlefield.getArmy(i).drawArmy(g);
        }

        this.repaint();	// make updated graphics visible

    }

    /*public Army getArmy(int team){
        if(team == 1){
            return redTeam;
        }
        else{
            return blueTeam;
        }
    }*/
}
