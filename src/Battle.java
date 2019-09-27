import java.awt.*;

public class Battle {

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    public static void main(String[] args){

        int armySize = 10;
        int numRounds = 0;
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

        while(numRounds < 2000){
            battlefield.setBackground(Color.LIGHT_GRAY);
            for(int i = 0; i < armySize; i++){
                redTeam[i].move();
                blueTeam[i].move();
                redTeam[i].draw();
                blueTeam[i].draw();
            }
            battlefield.copyGraphicsToScreen();
            numRounds++;
        }
    }

    public static Combatant[] GenerateArmy(int team, int armySize){
        Combatant[] army = new Combatant[armySize];
        for(int i = 0; i < armySize; i++){
            army[i] = new Combatant(team);
        }
        return army;
    }
}
