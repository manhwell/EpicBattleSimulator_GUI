import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Battle {

    private int windowWidth;
    private int windowHeight;
    private int armySize;

    public Battle(){
        this.windowHeight = 600;
        this.windowWidth = 800;
        this.armySize = 10;
    }

    public Battle(int windowWidth, int windowHeight, int armySize){
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
        this.armySize = armySize;
    }

    public int getWindowWidth(){
        return this.windowWidth;
    }

    public int getWindowHeight(){
        return this.windowHeight;
    }

    public int getArmySize(){
        return this.armySize;
    }

    public void runBattle() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        DrawingPanel battlefield = new DrawingPanel(this.getWindowWidth(), this.getWindowHeight());
        battlefield.setWindowTitle("Helms Deep");
        Graphics2D g = battlefield.getGraphics();
        battlefield.setBackground(Color.LIGHT_GRAY);
        battlefield.copyGraphicsToScreen();

        Combatant.setWindowWidth(this.getWindowWidth());
        Combatant.setWindowHeight(this.getWindowHeight());
        Combatant.setGraphics2D(g);

        Army redTeam = new Army(1, this.getArmySize());
        Army blueTeam = new Army(2, this.getArmySize());
        redTeam.drawArmy();
        blueTeam.drawArmy();
        battlefield.copyGraphicsToScreen();

        int dialogOption = JOptionPane.YES_NO_OPTION;
        int battleOver = (JOptionPane.showConfirmDialog(null, "Let the battle begin?", "The Battle of CompSci Deep", dialogOption));
        int winner = -1;
        while(!battlefield.mouseClickHasOccurred(DrawingPanel.LEFT_BUTTON) && battleOver == 0){
            if(battlefield.keyHasBeenHit(DrawingPanel.SPACE_KEY)){
                this.pauseBattle(battlefield, g);
            }
            battlefield.setBackground(Color.LIGHT_GRAY);
            g.drawString("Left click to end the battle early", 0, 12);
            for(int i = 0; i < this.armySize; i++){
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

    private void pauseBattle(DrawingPanel battlefield, Graphics2D g){
        while(!battlefield.keyHasBeenHit(DrawingPanel.SPACE_KEY)){
            g.drawString("PAUSED", this.getWindowWidth() / 2, this.getWindowHeight() / 2);
            battlefield.copyGraphicsToScreen();
        }
    }
}
