import java.util.ArrayList;

public class MainCharacter extends Character{
    
    int newPowerLevel;
    Weapon weapon;
    ArrayList<Weapon> weaponsCollection;
    ArrayList<Character> enemiesDefeated;

    //main constructor
    public MainCharacter(String name){
        super(name, 0); // player who has...
        this.weaponsCollection = new ArrayList<Weapon>();
        this.enemiesDefeated = new ArrayList<Character>();
    }

    // Updates the power level of user/player
    private void updatePowerLevel(){
        powerLevel = powerLevel + 2;
    }

    //List of enemies defeated.
    public void addEnemy(Character Enemy){
        enemiesDefeated.add(Enemy);
        updatePowerLevel();
    }

    //Gets the current number of weapons in collection
    public int getWeaponNum(){
        return weaponsCollection.size();
    }
    //Adds Weapon to collection
    public void addWeapon(Weapon weapon){
        weaponsCollection.add(weapon);
        updatePowerLevel();
        System.out.println("You have added " + weapon.getName() + " to your Collection!");
        System.out.println("Weapon Description: " + weapon.getDescription());
    }
    //removes weapon from collection
    public void removeWeapon(Weapon weapon){
        weaponsCollection.remove(weapon);
        System.out.println("You have lost " + weapon + "from your Collection...");
        powerLevel = powerLevel - 2;
    }

    //List weapon inventory
    public void listWeapons(){
        for(Weapon weapon : weaponsCollection){
            String name = weapon.getName();
            System.out.println(name + ": " + weapon.getDescription());
        }
    } 

}
