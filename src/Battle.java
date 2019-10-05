import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Battle() provides the generation and combat of two armies on a 2D graphics window.
 * @author C2C Manuel Riolo
 */
public class Battle {

    private int windowWidth;
    private int windowHeight;
    private int armySize;

    /**
     * Generates a new Battle class based on default variables.
     */
    public Battle(){
        this.windowHeight = 600;
        this.windowWidth = 800;
        this.armySize = 10;
    }

    /**
     * Generates a new Battle class based on passed variables.
     * @param windowWidth The width of the graphics window.
     * @param windowHeight  The height of the graphics window.
     * @param armySize The size of armies on the battlefield.
     */
    public Battle(int windowWidth, int windowHeight, int armySize){
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
        this.armySize = armySize;
    }

    /**
     * Gets the width of the graphics window.
     * @return The integer value of the graphics window width.
     */
    public int getWindowWidth(){
        return this.windowWidth;
    }

    /**
     * Gets the height of the graphics window.
     * @return The integer value of the graphics window height.
     */
    public int getWindowHeight(){
        return this.windowHeight;
    }

    /**
     * Gets the size of an army
     * @return The integer value of the army size.
     */
    public int getArmySize(){
        return this.armySize;
    }

    /**
     * Runs the Battle class and pits the two armies against each other on a graphics window.
     * @throws UnsupportedAudioFileException For errors in the audio stream.
     * @throws IOException For a file not found
     * @throws LineUnavailableException For a part of the file not findable.
     */
    public void runBattle() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // Initialize the graphics window.
        DrawingPanel battlefield = new DrawingPanel(this.getWindowWidth(), this.getWindowHeight());
        battlefield.setWindowTitle("Helms Deep");
        Graphics2D g = battlefield.getGraphics();
        battlefield.setBackground(Color.LIGHT_GRAY);
        battlefield.copyGraphicsToScreen();
        // Initialize both armies and draw them.
        Army redTeam = new Army(1, this.getArmySize(), windowWidth, windowHeight);
        Army blueTeam = new Army(2, this.getArmySize(), windowWidth, windowHeight);
        redTeam.drawArmy(g);
        blueTeam.drawArmy(g);
        battlefield.copyGraphicsToScreen();
        // Display opening message.
        int dialogOption = JOptionPane.YES_NO_OPTION;
        int battleOver = (JOptionPane.showConfirmDialog(null, "Let the battle begin?", "The Battle of CompSci Deep", dialogOption));
        int winner = -1; // Initially there is no winner.
        while(!battlefield.mouseClickHasOccurred(DrawingPanel.LEFT_BUTTON) && battleOver == 0){
            if(battlefield.keyHasBeenHit(DrawingPanel.SPACE_KEY)){ // Pause battle functionality
                this.pauseBattle(battlefield, g);
            }
            battlefield.setBackground(Color.LIGHT_GRAY);
            g.drawString("Left click to end the battle early", 0, 12);
            // Move each combatant and then let them attack.
            for(int i = 0; i < this.armySize; i++){
                redTeam.getSoldier(i).move(blueTeam, redTeam);
                blueTeam.getSoldier(i).move(redTeam, blueTeam);
                redTeam.getSoldier(i).attack(blueTeam.getArmy());
                blueTeam.getSoldier(i).attack(redTeam.getArmy());
            }
            // If both red team and blue team are completely dead, its a tie.
            if(redTeam.checkDead() == redTeam.getArmySize() && blueTeam.checkDead() == blueTeam.getArmySize()){
                battleOver = 1;
                winner = 0;
            }
            // If red team is completely dead, blue team wins
            else if(redTeam.checkDead() == redTeam.getArmySize()){
                battleOver = 1;
                winner = 1;
            }

            // If blue team is completely dead, red team wins.
            else if(blueTeam.checkDead() == blueTeam.getArmySize()){
                battleOver = 1;
                winner = 2;
            }
            // Draw the combatants after attacks are made then display the remaining number for each army.
            redTeam.drawArmy(g);
            blueTeam.drawArmy(g);
            g.drawString("# of Red Team left: " + (redTeam.getArmySize() - redTeam.checkDead()), 0, 24);
            g.drawString("# of Blue Team left: " + (blueTeam.getArmySize() - blueTeam.checkDead()), 0, 36);
            battlefield.copyGraphicsToScreen();
        }
        // Tie
        if(winner == 0){
            JOptionPane.showMessageDialog(null, "Its a tie! both armies eliminated");
        }
        // Red team victory.
        else if(winner == 2) {
            JOptionPane.showMessageDialog(null, "Red team wins!");
        }
        // Blue team victory.
        else if(winner == 1){
            JOptionPane.showMessageDialog(null, "Blue team wins!");
        }
        // For any other reason, battle has been ended early.
        else{
            JOptionPane.showMessageDialog(null, "Battle ended early.");
        }
        battlefield.closeWindow();
    }

    /**
     * Pauses the battle and waits for input to unpause.
     * @param battlefield The graphics window that displays the battlefield.
     * @param g The graphics for the window.
     */
    private void pauseBattle(DrawingPanel battlefield, Graphics2D g){
        // Space key pressed to get here, until its pressed again, display "PAUSED" and halt run.
        while(!battlefield.keyHasBeenHit(DrawingPanel.SPACE_KEY)){
            g.drawString("PAUSED", this.getWindowWidth() / 2, this.getWindowHeight() / 2);
            battlefield.copyGraphicsToScreen();
        }
    }
}
