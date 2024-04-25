import java.util.ArrayList;

public class MainCharacter extends Person{
    
    int newPowerLevel;
    Weapon weapon;
    ArrayList<Weapon> weaponsCollection;
    ArrayList<Enemies> enemiesDefeated;

    //main constructor
    public MainCharacter(String name, int powerLevel){
        super(name, powerLevel); // a person who has...
        this.weaponsCollection = new ArrayList<Weapon>();
        this.enemiesDefeated = new ArrayList<Enemies>();
    }

    //overload constructor
    public MainCharacter(String name){
        super(name, 0); 
        this.weaponsCollection = new ArrayList<Weapon>();
        this.enemiesDefeated = new ArrayList<Enemies>();
    }

    //gets name of player
    public String getName(){
        return name;
    }

    // Updates the power level of user/player
    public void setPowerLevel(int newPowerLevel){
        powerLevel = newPowerLevel;
    }
    //Gets current power level of user
    public int getPowerLevel(){
        return powerLevel;
    }
    //Gets the current number of weapons in collection
    public int getWeaponNum(){
        return weaponsCollection.size();
    }
    //Adds Weapon to collection
    public void addWeapon(Weapon weapon){
        weaponsCollection.add(weapon);
        System.out.println("You have added " + weapon + "to your Collection!");
    }
    //removes weapon from collection
    public void removeWeapon(Weapon weapon){
        weaponsCollection.remove(weapon);
        System.out.println("You have lost " + weapon + "from your Collection...");
    }

    // 

}
