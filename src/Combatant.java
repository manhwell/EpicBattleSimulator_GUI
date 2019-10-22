import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Combatant() provides the generation and manipulation of a Combatant for an Army.
 * @author C2C Manuel Riolo
 */
public class Combatant {

    //private static Graphics g;
    private int windowWidth;
    private int windowHeight;

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
     * @param team is the team to base the Combatants color on.
     * @param power is the power of a combatant.
     * @param windowWidth is the width of the battlefield.
     * @param windowHeight is the height of the battlefield.
     */
    public Combatant(int team, int power, int speed, int windowWidth, int windowHeight){
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
        this.size = (int) (Math.random() * 10) + 10; // vary size
        this.speed = speed;
        this.strength = power;
        this.courage = 50;
        this.health = 100 + this.size; // fi you are bigger you can take more hits
        this.movementDir = new Vector330Class(1, 1);
        this.name = "Combatant";
        if(team == 0) {
            int minY = this.size;
            int maxY = (int) (windowHeight / 2.5);
            int minX = this.size;
            int maxX = (int) (windowWidth / 2.5);
            this.color = Color.blue;
            this.currPos = new Vector330Class((Math.random() * (maxX - minX) + minX), (Math.random() * (maxY - minY) + minY));
        }
        else if(team == 1){
            int minY = (int) (windowHeight / 1.5);
            int maxY = windowHeight - this.size;
            int minX = (int) (windowWidth / 1.5);
            int maxX = windowWidth - this.size;
            this.color = Color.red;
            this.currPos = new Vector330Class((Math.random() * (maxX - minX) + minX), (Math.random() * (maxY - minY) + minY));
        }
        else if(team == 2){
            int minY = (int) (windowHeight / 1.5);
            int maxY = windowHeight - this.size;;
            int minX = this.size;
            int maxX = (int) (windowWidth / 2.5);
            this.color = Color.green;
            this.currPos = new Vector330Class((Math.random() * (maxX - minX) + minX), (Math.random() * (maxY - minY) + minY));
        }
        else if(team == 3){
            int minY = this.size;
            int maxY = (int) (windowHeight / 2.5);
            int minX = (int) (windowWidth / 1.5);
            int maxX = windowWidth - this.size;
            this.color = Color.yellow;
            this.currPos = new Vector330Class((Math.random() * (maxX - minX) + minX), (Math.random() * (maxY - minY) + minY));
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

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public void setStrength(int strength){
        this.strength = strength;
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
    public void setWindowWidth( int width ) { this.windowWidth = width; }

    /**
     * Gets the width of the battlefield.
     * @return the width of the battlefield.
     */
    public int getWindowWidth(){
        return this.windowWidth;
    }

    /**
     * Sets the graphics window height.
     * @param height The new height of the graphics window
     */
    public void setWindowHeight( int height ) { this.windowHeight = height; }

    /**
     * Gets the height of the battlefield.
     * @return the height of the battlefield.
     */
    public int getWindowHeight(){
        return this.windowHeight;
    }

    /**
     * Draws a Combatant on the graphics window.
     */
    public void draw(Graphics g){
        // Code taken from bouncing balls in class example.
        if(this.getHealth() > 0) {
            g.setColor(this.color);
            g.fillOval((this.getX() - this.size), (this.getY() - this.size),
                    (2 * this.size), (2 * this.size));
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
    public void move(ArrayList<Combatant> enemyArmy, Army friendlyArmy){
        int speedToken = (int) (Math.random() * 100); // Chance that they will move this turn.
        double smallestDistance = 10000; // Initial set very large.
        int closestEnemy = 0;
        // Find the closest enemy.
        if(enemyArmy.size() == 0){ // If there are no enemies left, just skip it all.
            return;
        }
        for(int i = 0; i < enemyArmy.size(); i++){
            if(this.getHealth() > 0 && enemyArmy.get(i).getHealth() > 0) { // Confirm they are both alive.
                Vector330Class findDist = enemyArmy.get(i).currPos.subtract(this.currPos); // Finding vector between the 2
                // If new distance is smaller than the previous distance, update new smallest distance and store that index.
                if (findDist.magnitude() < smallestDistance) {
                    closestEnemy = i;
                    smallestDistance = findDist.magnitude();
                }
            }
        }
        // Get that vector between you and the closest enemy and move toward them based on your speed.
        Vector330Class d = enemyArmy.get(closestEnemy).currPos.subtract(this.currPos);
        this.movementDir = d.normalize().scale(2);
        if(this.speed > speedToken) { // A higher speed = a greater chance to move this turn.
            // If the enemy army is twice your size, run away.
            if(8 * (friendlyArmy.getArmySize() - friendlyArmy.checkDead()) < (enemyArmy.size() - this.checkEnemyDead(enemyArmy))) {
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
    public void attack(ArrayList<Combatant> enemyArmy) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        for(int i = 0; i < enemyArmy.size(); i++) {
            if(this.getHealth() > 0 && enemyArmy.get(i).getHealth() > 0) { // Check that both of you are alive
                // If the enemy is within range of you, attack them.
                if (Math.abs(enemyArmy.get(i).getX() - this.getX()) <= this.getSize() && Math.abs(enemyArmy.get(i).getY() - this.getY()) <= this.getSize()) {
                    enemyArmy.get(i).damage(this.getStrength()); // Deal damage to the enemy based off your strength.
                }
            }
        }
    }

    /**
     * Checks the number of enemies that are dead on the battlefield.
     * @param enemyArmy is the array list of enemy combatants.
     * @return the number of dead enemies.
     */
    public int checkEnemyDead(ArrayList<Combatant> enemyArmy){
        int numDead = 0;
        for(int i = 0; i < enemyArmy.size(); i++){
            if(enemyArmy.get(i).getHealth() <= 0){
                numDead++;
            }
        }
        return numDead;
    }
}
