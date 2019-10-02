import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Battle {

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int ARMY_SIZE = 10;

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        DrawingPanel battlefield = new DrawingPanel(WINDOW_WIDTH, WINDOW_HEIGHT);
        AudioPlayer myAudioPlayer = new AudioPlayer("WilhelmScream.wav");
        battlefield.setWindowTitle("Helms Deep");
        Graphics2D g = battlefield.getGraphics();
        battlefield.setBackground(Color.LIGHT_GRAY);
        battlefield.copyGraphicsToScreen();

        Combatant.setWindowWidth(WINDOW_WIDTH);
        Combatant.setWindowHeight(WINDOW_HEIGHT);
        Combatant.setGraphics2D(g);

        Army redTeam = new Army(1, ARMY_SIZE);
        Army blueTeam = new Army(2, ARMY_SIZE);
        redTeam.drawArmy();
        blueTeam.drawArmy();
        battlefield.copyGraphicsToScreen();

        int dialogOption = JOptionPane.YES_NO_OPTION;
        int battleOver = (JOptionPane.showConfirmDialog(null, "Let the battle begin?", "The Battle of CompSci Deep", dialogOption));
        int winner = -1;
        while(!battlefield.mouseClickHasOccurred(DrawingPanel.LEFT_BUTTON) && battleOver == 0){
            battlefield.setBackground(Color.LIGHT_GRAY);
            g.drawString("Left click to end the battle early", 0, 12);
            for(int i = 0; i < ARMY_SIZE; i++){
                redTeam.getSoldier(i).move(blueTeam, redTeam);
                blueTeam.getSoldier(i).move(redTeam, blueTeam);
                redTeam.getSoldier(i).attack(blueTeam.getArmy());
                blueTeam.getSoldier(i).attack(redTeam.getArmy());
            }
            if(redTeam.checkDead() == redTeam.getArmySize() && blueTeam.checkDead() == blueTeam.getArmySize()){
                battleOver = 1;
                winner = 0;
            }
            else if(redTeam.checkDead() == redTeam.getArmySize()){
                battleOver = 1;
                winner = 1;
            }
            else if(blueTeam.checkDead() == blueTeam.getArmySize()){
                battleOver = 1;
                winner = 2;
            }
            redTeam.drawArmy();
            blueTeam.drawArmy();
            g.drawString("# of Red Team left: " + (redTeam.getArmySize() - redTeam.checkDead()), 0, 24);
            g.drawString("# of Blue Team left: " + (blueTeam.getArmySize() - blueTeam.checkDead()), 0, 36);
            battlefield.copyGraphicsToScreen();
        }
        if(winner == 0){
            JOptionPane.showMessageDialog(null, "Its a tie! both armies eliminated");
        }
        else if(winner == 2) {
            JOptionPane.showMessageDialog(null, "Red team wins!");
        }
        else if(winner == 1){
            JOptionPane.showMessageDialog(null, "Blue team wins!");
        }
        else{
            JOptionPane.showMessageDialog(null, "Battle ended early.");
        }
        battlefield.closeWindow();
    }
}
