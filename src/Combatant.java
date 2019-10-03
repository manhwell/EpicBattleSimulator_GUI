import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

/**
 * Combatant() provides the generation and manipulation of a Combatant for an Army.
 * @author C2C Manuel Riolo
 */
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

    /**
     * Generates a new Combatant class based on default variables.
     */
    public Combatant(){
        this.size = 10;
        this.speed = 10;
        this.strength = (int) (Math.random() * 49) + 1; // Strength of 1-50.
        this.courage = 50;
        this.health = 100;
        this.team = 1;
        this.currPos = new Vector330Class(Math.random() * (windowWidth-100), Math.random() * (windowHeight-100));
        this.color = Color.RED;
        this.movementDir = new Vector330Class(1, 1);
        this.name = "Combatant";
    }

    /**
     * Generates a new Combatant class based on passed variables.
     * @param team The team to base the Combatants color on.
     */
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

    /**
     * Sets the color of a combatant.
     * @param teamColor The color of the combatant.
     */
    public void setColor(Color teamColor){
            this.color = teamColor;
    }

    /**
     * Sets the health of the combatant.
     * @param health New health value to set for Combatant.
     */
    public void setHealth(int health){
        this.health = health;
    }

    /**
     * Sets the team of a Combatant.
     * @param team The team # to set for the Combatant.
     */
    public void setTeam(int team){
        this.team = team;
    }

    /**
     * Gets the X-cord of a Combatant.
     * @return The integer value of a Combatant's X-cord.
     */
    public int getX(){
        return this.currPos.getXint();
    }

    /**
     * Gets the Y-cord of a Combatant.
     * @return The integer value of a Combatant's Y-cord.
     */
    public int getY(){
        return this.currPos.getYint();
    }

    /**
     * Gets the health of a Combatant.
     * @return the integer value of a Combatant's health
     */
    public int getHealth(){
        return this.health;
    }

    /**
     * Gets the strength value of a Combatant.
     * @return The integer value of a Combatant's strength.
     */
    public int getStrength(){
        return this.strength;
    }

    /**
     * Gets the size of a Combatant.
     * @return The integer value of a Combatant's size.
     */
    public int getSize(){
        return this.size;
    }

    /**
     * Gets the team # of a Combatant.
     * @return The integer value of a Combatant's team #.
     */
    public int getTeam(){
        return this.team;
    }

    /**
     * Gets the color of a Combatant.
     * @return The color value of a Combatant.
     */
    public Color getColor(){
        return this.color;
    }

    /**
     * Sets the name of a Combatant.
     * @param name The new name of a Combatant.
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Gets the name of a Combatant.
     * @return The String of a Combatant's name.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Deals damage to a Combatant.
     * @param damage The amount of damage to deal.
     * @throws UnsupportedAudioFileException For errors in the audio stream.
     * @throws IOException For a file not found
     * @throws LineUnavailableException For a part of the file not findable.
     */
    public void damage(int damage) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.setHealth(this.health - damage); // Deal damage to this combatants health.
        // If they are killed from the attack, let 'em scream.
        if(this.getHealth() <= 0){
            AudioPlayer deathScream = new AudioPlayer("WilhelmScream.wav");
            deathScream.play();
        }
    }

    /**
     * Sets the graphics window width.
     * @param width The new width of the graphics window.
     */
    public static void setWindowWidth( int width ) { Combatant.windowWidth = width; }

    /**
     * Sets the graphics window height.
     * @param height The new height of the graphics window
     */
    public static void setWindowHeight( int height ) { Combatant.windowHeight = height; }

    /**
     * Sets the graphics window to a certain graphics type.
     * @param g The type of graphics the window will display.
     */
    public static void setGraphics2D( Graphics2D g ) { Combatant.g = g; }

    /**
     * Draws a Combatant on the graphics window.
     */
    public void draw(){
        // Code taken from bouncing balls in class example.
        if(this.getHealth() > 0) {
            if (Combatant.g != null) {
                Combatant.g.setColor(this.color);
                Combatant.g.fillOval((this.getX() - this.size), (this.getY() - this.size),
                        (2 * this.size), (2 * this.size));
            }
            // Draw the class of each combatant on top of them for easier identification.
            g.setColor(Color.black);
            g.drawString(this.getName(), this.getX(), this.getY());
        }
    }

    /**
     * Moves a Combatant based on the status and location of both armies
     * @param enemyArmy The Army() for the enemy.
     * @param friendlyArmy The Army() for allies.
     */
    public void move(Army enemyArmy, Army friendlyArmy){
        int speedToken = (int) (Math.random() * 100); // Chance that they will move this turn.
        double smallestDistance = 10000; // Initial set very large.
        int closestEnemy = 0;
        // Find the closest enemy.
        for(int i = 0; i < enemyArmy.getArmySize(); i++){
            if(this.getHealth() > 0 && enemyArmy.getArmy()[i].getHealth() > 0) { // Confirm they are both alive.
                Vector330Class findDist = enemyArmy.getArmy()[i].currPos.subtract(this.currPos); // Finding vector between the 2
                // If new distance is smaller than the previous distance, update new smallest distance and store that index.
                if (findDist.magnitude() < smallestDistance) {
                    closestEnemy = i;
                    smallestDistance = findDist.magnitude();
                }
            }
        }
        // Get that vector between you and the closest enemy and move toward them based on your speed.
        Vector330Class d = enemyArmy.getArmy()[closestEnemy].currPos.subtract(this.currPos);
        this.movementDir = d.normalize().scale(this.speed); // Increases distance traveled by speed.
        if(this.speed > speedToken) { // A higher speed = a greater chance to move this turn.
            // If the enemy army is twice your size, run away.
            if(2 * (friendlyArmy.getArmySize() - friendlyArmy.checkDead()) < (enemyArmy.getArmySize() - enemyArmy.checkDead())) {
                this.currPos.setX(this.currPos.getXint() + (-1 * this.movementDir.getXint()));
                this.currPos.setY(this.currPos.getYint() + (-1 * this.movementDir.getYint()));
            }
            // Charge toward enemy.
            else{
                this.currPos.setX(this.currPos.getXint() + (this.movementDir.getXint()));
                this.currPos.setY(this.currPos.getYint() + (this.movementDir.getYint()));
            }
        }
        // If they leave the screen, "kill them," they have fled.
        if((this.getX() > windowWidth || this.getY() > windowHeight || this.getX() < 0 || this.getY() < 0) && this.getHealth() > 0){
            this.setHealth(0);
            System.out.println(this.getName() + " has fled from battle, coward.");
        }
    }

    /**
     * Lets a Combatant attack a certain enemy.
     * @param enemyArmy The Army() for the enemy.
     * @throws UnsupportedAudioFileException For errors in the audio stream.
     * @throws IOException For a file not found
     * @throws LineUnavailableException For a part of the file not findable.
     */
    public void attack(Combatant[] enemyArmy) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        for(int i = 0; i < enemyArmy.length; i++) {
            if(this.getHealth() > 0 && enemyArmy[i].getHealth() > 0) { // Check that both of you are alive
                // If the enemy is within range of you, attack them.
                if (Math.abs(enemyArmy[i].getX() - this.getX()) <= this.getSize() && Math.abs(enemyArmy[i].getY() - this.getY()) <= this.getSize()) {
                    enemyArmy[i].damage(this.getStrength()); // Deal damage to the enemy based off your strength.
                }
            }
        }
    }
}
