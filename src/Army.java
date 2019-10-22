import java.awt.*;

/**
 * Army() provides the generation and manipulation of an army of Combatants().
 * @author C2C Manuel Riolo
 */
public class Army {

    // Initializing variables.
    private Combatant[] Army;
    private int armySize;

    /**
     * Generates a new Army class based on default values.
     */
    public Army(){
        Army = new Combatant[10]; // Allocate space for an army of 10
        for(int i = 0; i < 10; i++){
            int combatantClass = (int) (Math.random() * 2) + 1; // Random variable to choose what to put in the army.
            if(combatantClass == 1){
                this.Army[i] = new Combatant(); // Create a combatant.
            }
            else {
                this.Army[i] = new Archer(); // Create a knight
            }
        }
        this.armySize = 10;
    }

    /**
     * Generates a new Army class based on passed variables.
     * @param team is the team # of the army.
     * @param armySize is the size of an army.
     * @param power is the power of an army.
     * @param speed is the speed of an army.
     * @param windowWidth is the width of the battlefield.
     * @param windowHeight is the height of the battlefield.
     */
    public Army(int team, int armySize, int power, int speed, int windowWidth, int windowHeight){
        Army = new Combatant[armySize]; // Allocate space for a proper army
        for(int i = 0; i < armySize; i++){
            int combatantClass = (int) (Math.random() * 2) + 1; // Random variable to choose what to put in the army.
            if(combatantClass == 1){  // Generate a melee class
                int meleeClass = (int) (Math.random() * 2) + 1; // Random variable to choose what melee class to put in the army
                if(meleeClass == 1) { // Create a combatant.
                    this.Army[i] = new Combatant(team, power, speed, windowWidth, windowHeight);
                }
                else{ // Create a knight
                    this.Army[i] = new Knight(team, power, speed, windowWidth, windowHeight);
                }
            }
            else { // Create a ranger class
                this.Army[i] = new Archer(team, power, speed, windowWidth, windowHeight);
            }
        }
        this.armySize = armySize;
    }

    /**
     * Gets the members of an army
     * @return The array containing the members of an Army.
     */
    public Combatant[] getArmy(){
        return this.Army;
    }

    /**
     * Gets the soldier in a Combatant[]
     * @param indexNum The index number of the soldier to get.
     * @return The Combatant found at the index.
     */
    public Combatant getSoldier(int indexNum){
        return this.Army[indexNum];
    }

    /**
     * Gets the size of a army.
     * @return The integer value of an Army's size.
     */
    public int getArmySize(){
        return this.armySize;
    }

    /**
     * Draws an entire Army to a graphics window.
     */
    public void drawArmy(Graphics g){
        // Go through the Combatant array and draw each combatant.
        for(int i = 0; i < this.armySize; i++){
            this.Army[i].draw(g);
        }
    }

    /**
     * Counts the number of dead Combatants in an Army.
     * @return The integer number of dead Combatants in an Army.
     */
    public int checkDead(){
        int numDead = 0;
        // Go through the Combatant array and if a combatants health is 0, increase dead count.
        for(int i = 0; i < this.armySize; i++){
            if(this.Army[i].getHealth() <= 0){
                numDead++;
            }
        }
        return numDead;
    }

    /**
     * Gets the string value of an armies color
     * @return the string value of an armies color
     */
    public String getArmyColor(){
        // Return the string associated with a color.
        if(this.getSoldier(0).getColor() == Color.red){
            return "Red";
        }
        else if(this.getSoldier(0).getColor() == Color.blue){
            return "Blue";
        }
        else if(this.getSoldier(0).getColor() == Color.yellow){
            return "Yellow";
        }
        else if(this.getSoldier(0).getColor() == Color.green){
            return "Green";
        }
        else{
            return "Black";
        }
    }

    /**
     * Sets the speed of an army to a passed value.
     * @param speed is the new speed value for the army.
     */
    public void setArmySpeed(int speed){
        // Go through an army and set all the combatants speed values.
        for(int i = 0; i < this.Army.length; i++){
            this.Army[i].setSpeed(speed);
        }
    }

    /**
     * Sets the strength of an army to a passed value.
     * @param strength is the new strength of an army.
     */
    public void setArmyStrength(int strength){
        // Go through an army and set all the combatants strength values.
        for(int i = 0; i < this.Army.length; i++){
            this.Army[i].setStrength(strength);
        }
    }

    /**
     * Sets the color of an army to a passed value.
     * @param chosenColor is the new color for the army.
     */
    public void setArmyColor(String chosenColor){
        Color newColor;
        // Determine an armies new color based off a passed string.
        switch (chosenColor) {
            case "Blue":
                 newColor = Color.blue;
                break;
            case "Red":
                newColor = Color.red;
                break;
            case "Green":
                newColor = Color.green;
                break;
            case "Yellow":
                newColor = Color.yellow;
                break;
            default:
                newColor = Color.black;
                break;
        }
        // Go through an army and set all the combatants color.
        for(int i = 0; i < this.Army.length; i++){
            this.Army[i].setColor(newColor);
        }
    }
}
