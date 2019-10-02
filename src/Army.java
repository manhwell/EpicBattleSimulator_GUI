public class Army {

    private Combatant[] Army;
    private int armySize;

    public Army(){
        Army = new Combatant[10];
        for(int i = 0; i < 10; i++){
            int combatantClass = (int) (Math.random() * 2) + 1;
            if(combatantClass == 1){
                this.Army[i] = new Combatant();
            }
            else {
                this.Army[i] = new Archer();
            }
        }
        this.armySize = 10;
    }

    public Army(int team, int armySize){
        Army = new Combatant[armySize];
        for(int i = 0; i < armySize; i++){
            int combatantClass = (int) (Math.random() * 2) + 1;
            if(combatantClass == 1){  // Generate a melee class
                int meleeClass = (int) (Math.random() * 2) + 1;
                if(meleeClass == 1) {
                    this.Army[i] = new Combatant(team);
                }
                else{
                    this.Army[i] = new Knight(team);
                }
            }
            else { // Generate a ranger class
                this.Army[i] = new Archer(team);
            }
        }
        this.armySize = armySize;
    }

    public Combatant[] getArmy(){
        return this.Army;
    }

    public Combatant getSoldier(int indexNum){
        return this.Army[indexNum];
    }

    public int getArmySize(){
        return this.armySize;
    }

    public void drawArmy(){
        for(int i = 0; i < this.armySize; i++){
            this.Army[i].draw();
        }
    }

    public int checkDead(){
        int numDead = 0;
        for(int i = 0; i < this.armySize; i++){
            if(this.Army[i].getHealth() <= 0){
                numDead++;
            }
        }
        return numDead;
    }
}
