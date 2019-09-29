import javax.swing.*;
import java.awt.*;

public class Battle {

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    public static void main(String[] args){

        int armySize = 10;
        Combatant[] redTeam = new Combatant[armySize];
        Combatant[] blueTeam = new Combatant[armySize];

        DrawingPanel battlefield = new DrawingPanel( WINDOW_WIDTH, WINDOW_HEIGHT );
        battlefield.setWindowTitle("Helms Deep");
        Graphics2D g = battlefield.getGraphics();
        battlefield.setBackground(Color.LIGHT_GRAY);
        battlefield.copyGraphicsToScreen();

        Combatant.setWindowWidth(WINDOW_WIDTH);
        Combatant.setWindowHeight(WINDOW_HEIGHT);
        Combatant.setGraphics2D(g);

        redTeam = Battle.GenerateArmy(1, armySize);
        blueTeam = Battle.GenerateArmy(2, armySize);
        for(int i = 0; i < armySize; i++){
            redTeam[i].draw();
            blueTeam[i].draw();
        }
        battlefield.copyGraphicsToScreen();

        int dialogOption = JOptionPane.YES_NO_OPTION;
        int battleOver = (JOptionPane.showConfirmDialog(null, "Let the battle begin?", "The Battle of CompSci Deep", dialogOption));
        while(!battlefield.mouseClickHasOccurred(DrawingPanel.LEFT_BUTTON) && battleOver == 0){
            battlefield.setBackground(Color.LIGHT_GRAY);
            for(int i = 0; i < armySize; i++){
                redTeam[i].move(blueTeam);
                blueTeam[i].move(redTeam);
                redTeam[i].attack(blueTeam);
                blueTeam[i].attack(redTeam);
            }
            if(Battle.checkDead(redTeam) == armySize || Battle.checkDead(blueTeam) == armySize){
                battleOver = 1;
            }
            Battle.drawArmy(redTeam);
            Battle.drawArmy(blueTeam);
            battlefield.copyGraphicsToScreen();
        }
    }

    public static Combatant[] GenerateArmy(int team, int armySize){
        Combatant[] army = new Combatant[armySize];
        for(int i = 0; i < armySize; i++){
            int combatantClass = (int) (Math.random() * 2) + 1;
            if(combatantClass == 1){
                army[i] = new Combatant(team);
            }
            else {
                army[i] = new Archer(team);
            }
        }
        return army;
    }

    public static int checkDead(Combatant[] army){
        int numDead = 0;
        for(int i = 0; i < army.length; i++){
            if(army[i].getHealth() <= 0){
                numDead++;
            }
        }
        return numDead;
    }

    public static void drawArmy(Combatant[] army){
        for(int i = 0; i < army.length; i++){
            army[i].draw();
        }
    }
}
