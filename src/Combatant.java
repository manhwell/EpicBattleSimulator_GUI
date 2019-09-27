import java.awt.*;

public class Combatant {

    private static Graphics2D g = null;
    private static int windowWidth = 100;
    private static int windowHeight = 100;

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

    public Combatant(int team){
        this.size = 10;
        this.speed = 5;
        this.strength = 10;
        this.courage = 50;
        if( team == 1) {
            int min = (int) (windowHeight * (2.0/3.0));
            int max = windowHeight-20;
            this.team = Color.RED;
            this.xPos = (int) (Math.random() * (windowWidth-40)) + 20;
            this.yPos = (int) (Math.random() * ((max - min) + 1)) + min;
        }
        else{
            int min = 20;
            int max = (int) (windowHeight * (1.0/3.0));
            this.team = Color.BLUE;
            this.xPos = (int) (Math.random() * (windowWidth-40)) +20;
            this.yPos = (int) (Math.random() * ((max - min) + 1)) + min;
        }
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
