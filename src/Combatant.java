import java.awt.*;

public class Combatant {

    private static Graphics2D g = null;
    private static int windowWidth = 100;
    private static int windowHeight = 100;

    private int size;
    private int speed;
    private int health;
    private int strength;
    private int courage;
    private Vector330Class currPos;
    private Vector330Class movementDir;
    private java.awt.Color color;
    private int team;
    private String name;

    public Combatant(){
        this.size = 10;
        this.speed = 10;
        this.strength = (int) (Math.random() * 49) + 1;
        this.courage = 50;
        this.health = 100;
        this.team = 1;
        this.currPos = new Vector330Class(Math.random() * (windowWidth-100), Math.random() * (windowHeight-100));
        this.color = Color.RED;
        this.movementDir = new Vector330Class(1, 1);
        this.name = "Combatant";
    }

    public Combatant(int team){
        this.size = (int) (Math.random() * 10) + 10; // vary size
        this.speed = ((int) (Math.random() * 4) + 3) - this.size; // the bigger you are the slower you go
        if(this.speed < 3){
            this.speed = 3; // slowest a combatant can be
        }
        this.strength = (int) (Math.random() * 50) + 1; // vary strength
        this.courage = 50;
        this.health = 100 + this.size; // fi you are bigger you can take more hits
        this.movementDir = new Vector330Class(1, 1);
        this.name = "Combatant";
        if(team == 1) {
            int min = (int) (windowHeight * (2.0/3.0));
            int max = windowHeight-20;
            int teamNum = team;
            this.color = Color.RED;
            this.currPos = new Vector330Class((Math.random() * (windowWidth-40)) + 20, (Math.random() * ((max - min) + 1)) + min);
        }
        else{
            int min = 20;
            int max = (int) (windowHeight * (1.0/3.0));
            int teamNum = team;
            this.color = Color.BLUE;
            this.currPos = new Vector330Class((Math.random() * (windowWidth-40)) +20, (Math.random() * ((max - min) + 1)) + min);
        }
    }

    public void setColor(Color teamColor){
            this.color = teamColor;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public void setTeam(int team){
        this.team = team;
    }

    public int getX(){
        return this.currPos.getXint();
    }

    public int getY(){
        return this.currPos.getYint();
    }

    public int getHealth(){
        return this.health;
    }

    public int getStrength(){
        return this.strength;
    }

    public int getSize(){
        return this.size;
    }

    public int getTeam(){
        return this.team;
    }

    public Color getColor(){
        return this.color;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void damage(int damage){
        this.setHealth(this.health - damage);
    }

    public static void setWindowWidth( int width ) { Combatant.windowWidth = width; }
    public static void setWindowHeight( int height ) { Combatant.windowHeight = height; }
    public static void setGraphics2D( Graphics2D g ) { Combatant.g = g; }

    public void draw(){
        if(this.getHealth() > 0) {
            if (Combatant.g != null) {
                Combatant.g.setColor(this.color);
                Combatant.g.fillOval((this.getX() - this.size), (this.getY() - this.size),
                        (2 * this.size), (2 * this.size));
            }
            g.setColor(Color.black);
            g.drawString(this.getName(), this.getX(), this.getY());
        }
    }

    public void move(Combatant[] enemyArmy, Combatant[] friendlyArmy){
        int numFriendly = this.countNumAround(friendlyArmy);
        int numEnemy = this.countNumAround(enemyArmy);
        int speedToken = (int) (Math.random() * 100);
        double smallestDistance = 10000;
        int closestEnemy = 0;
        for(int i = 0; i < enemyArmy.length; i++){
            if(this.getHealth() > 0 && enemyArmy[i].getHealth() > 0) {
                Vector330Class findDist = enemyArmy[i].currPos.subtract(this.currPos);
                if (findDist.magnitude() < smallestDistance) {
                    closestEnemy = i;
                    smallestDistance = findDist.magnitude();
                }
            }
        }
        Vector330Class d = enemyArmy[closestEnemy].currPos.subtract(this.currPos);
        this.movementDir = d.normalize().scale(this.speed);
        if(this.speed > speedToken) {
            if(numFriendly >= numEnemy) {
                this.currPos.setX(this.currPos.getXint() + (this.movementDir.getXint()));
                this.currPos.setY(this.currPos.getYint() + (this.movementDir.getYint()));
            }
            else{
                this.currPos.setX(this.currPos.getXint() + (-1 * this.movementDir.getXint()));
                this.currPos.setY(this.currPos.getYint() + (-1 * this.movementDir.getYint()));
            }
        }
        if((this.getX() > windowWidth || this.getY() > windowHeight || this.getX() < 0 || this.getY() < 0) && this.getHealth() > 0){
            this.setHealth(0);
            System.out.println(this.getName() + " has fled from battle, coward.");
        }
    }

    public void attack(Combatant[] enemyArmy){
        for(int i = 0; i < enemyArmy.length; i++) {
            if(this.getHealth() > 0 && enemyArmy[i].getHealth() > 0) {
                if (Math.abs(enemyArmy[i].getX() - this.getX()) <= this.getSize() && Math.abs(enemyArmy[i].getY() - this.getY()) <= this.getSize()) {
                    enemyArmy[i].damage(this.getStrength());
                }
            }
        }
    }

    public int countNumAround(Combatant[] army){
        int numAround = 0;
        for(int i = 0; i < army.length; i++) {
            if (Math.abs(army[i].getX() - this.getX()) <= (this.size * 3) &&
                    Math.abs(army[i].getY() - this.getY()) <= (this.size * 3)) {
                numAround++;
            }
        }
        return numAround;
    }
}
