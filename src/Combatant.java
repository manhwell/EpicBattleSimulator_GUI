import java.awt.*;

public class Combatant {

    private static Graphics2D g = null;
    private static int windowWidth = 100;
    private static int windowHeight = 100;

    private int size;
    private int speed;
    private int strength;
    private int courage;
    private Vector330Class currPos;
    private Vector330Class movementDir;
    private java.awt.Color team;

    public Combatant(){
        this.size = 10;
        this.speed = 10;
        this.strength = 10;
        this.courage = 50;
        this.currPos = new Vector330Class(Math.random() * (windowWidth-100), Math.random() * (windowHeight-100));
        this.team = Color.RED;
        this.movementDir = new Vector330Class(1, 1);
    }

    public Combatant(int team){
        this.size = 10;
        this.speed = 5;
        this.strength = 10;
        this.courage = 50;
        this.movementDir = new Vector330Class(1, 1);
        if(team == 1) {
            int min = (int) (windowHeight * (2.0/3.0));
            int max = windowHeight-20;
            this.team = Color.RED;
            this.currPos = new Vector330Class((Math.random() * (windowWidth-40)) + 20, (Math.random() * ((max - min) + 1)) + min);
        }
        else{
            int min = 20;
            int max = (int) (windowHeight * (1.0/3.0));
            this.team = Color.BLUE;
            this.currPos = new Vector330Class((Math.random() * (windowWidth-40)) +20, (Math.random() * ((max - min) + 1)) + min);
        }
    }

    public int getX(){
        return this.currPos.getXint();
    }

    public int getY(){
        return this.currPos.getYint();
    }

    public static void setWindowWidth( int width ) { Combatant.windowWidth = width; }
    public static void setWindowHeight( int height ) { Combatant.windowHeight = height; }
    public static void setGraphics2D( Graphics2D g ) { Combatant.g = g; }

    public void draw(){
        if (Combatant.g != null) {
            Combatant.g.setColor(this.team);
            Combatant.g.fillOval( (this.getX() - this.size), (this.getY() - this.size),
                    (2 * this.size), (2 * this.size) );
        }
    }

    public void move(Combatant[] enemyArmy){
        int speedToken = (int) (Math.random() * 100);
        Vector330Class d = enemyArmy[0].currPos.subtract(this.currPos);
        this.movementDir = d.normalize().scale(this.speed);
        if(this.speed > speedToken) {
            this.currPos.setX(this.currPos.getXint() + (this.movementDir.getXint()));
            this.currPos.setY(this.currPos.getYint() + (this.movementDir.getYint()));
        }
    }

}
