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
    public void updatePowerLevel(int newPowerLevel){
        powerLevel = newPowerLevel;
    }

    //Gets the current number of weapons in collection
    public int getWeaponNum(){
        return weaponsCollection.size();
    }
    //Adds Weapon to collection
    public void addWeapon(Weapon weapon){
        weaponsCollection.add(weapon);
        System.out.println("You have added " + weapon + "to your Collection!");
        System.out.println("Weapon Description: " + weapon.getDescription());
    }
    //removes weapon from collection
    public void removeWeapon(Weapon weapon){
        weaponsCollection.remove(weapon);
        System.out.println("You have lost " + weapon + "from your Collection...");
    }

    //List weapon inventory
    public void listWeapons(){
        for(Weapon weapon : weaponsCollection){
            String name = weapon.getName();
            //String description = weapon.getDesc();
            System.out.println(name);
            //System.out.println("\n" + description);
        }
    } 

}
