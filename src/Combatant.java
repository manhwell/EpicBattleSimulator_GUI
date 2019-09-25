import java.awt.*;

public class Combatant {

    private static Graphics2D g = null;
    private static int windowWidth = 800;
    private static int windowHeight = 600;

    private int size;
    private int speed;
    private int strength;
    private int courage;
    private int xPos;
    private int yPos;
    private java.awt.Color team;

    public Combatant(){
        this.size = 10;
        this.speed = 5;
        this.strength = 10;
        this.courage = 50;
        this.xPos = (int) (Math.random() * (windowWidth-100));
        this.yPos = (int) (Math.random() * (windowHeight-100));
        this.team = Color.RED;
    }

    public int getX(){
        return this.xPos;
    }

    public int getY(){
        return this.yPos;
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

}
