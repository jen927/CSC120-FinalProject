import java.util.ArrayList;

public class MainCharacter extends Character {

    int newPowerLevel;
    Weapon weapon;
    ArrayList<Weapon> weaponsCollection;
    int enemiesDefeated;

    // main constructor
    public MainCharacter(String name) {
        super(name, 0); // player who has...
        this.weaponsCollection = new ArrayList<Weapon>();
        this.enemiesDefeated = 0;
    }

    /**
     * updates power level of player
     * 
     * @param factor power level updates differently depending on a factor, ex.
     *               weapon or num of enemies
     */
    private void updatePowerLevel(int factor) {
        powerLevel = powerLevel + (2 * factor);
    }

    /**
     * updates the num of enemies defeated and updates the player's level based on
     * the new count
     * 
     * @param numEnemies number of enemies in the location
     */
    public void updateEnemiesDefeated(int numEnemies) {
        enemiesDefeated = enemiesDefeated + numEnemies;
        updatePowerLevel(numEnemies);
    }

    /**
     * gets the number of weapons in collection
     * 
     * @return number of weapons in collection
     */
    public int getWeaponNum() {
        return weaponsCollection.size();
    }

    /**
     * Adds weapon to collection
     * 
     * @param weapon a weapon object
     */
    public void addWeapon(Weapon weapon) {
        weaponsCollection.add(weapon);
        if (weapon.getName().equals("Swift Lock Sword")){
            updatePowerLevel(6);
        }
        else{
            updatePowerLevel(1);
        }
        System.out.println("You have added " + weapon.getName() + " to your Collection!");
        System.out.println("Weapon Description: " + weapon.getDescription());
    }

    /**
     * Removes weapon from collection
     * 
     * @param weapon weapon object
     */
    public void removeWeapon(Weapon weapon) {
        weaponsCollection.remove(weapon);
        System.out.println("You have lost " + weapon + "from your Collection...");
        powerLevel = powerLevel - 2;
    }

    /**
     * Lists inventory of weapon collection
     * 
     */
    public void listWeapons() {
        for (Weapon weapon : weaponsCollection) {
            String name = weapon.getName();
            System.out.println(name + ": " + weapon.getDescription());
        }
    }

    /**
     * gets the number of enemies defeated
     * 
     * @return the number of enemies defeated
     */
    public int getEnemiesDefeated(){
        return enemiesDefeated;
    }

}
