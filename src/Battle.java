import java.awt.*;

public class Battle {

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    public static void main(String[] args){

        int numCombatants = 10;
        Combatant[] redTeam = new Combatant[numCombatants];

        DrawingPanel battlefield = new DrawingPanel( WINDOW_WIDTH, WINDOW_HEIGHT );
        battlefield.setWindowTitle("Helms Deep");
        Graphics2D g = battlefield.getGraphics();
        battlefield.setBackground(Color.LIGHT_GRAY);
        battlefield.copyGraphicsToScreen();

        Combatant.setWindowWidth(WINDOW_WIDTH);
        Combatant.setWindowHeight(WINDOW_HEIGHT);
        Combatant.setGraphics2D(g);

        for(int i = 0; i < numCombatants; i++){
            redTeam[i] = new Combatant();
            redTeam[i].draw();
        }
    }
}
