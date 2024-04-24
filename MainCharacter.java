import java.util.ArrayList;

public class MainCharacter extends Person{
    
    int newPowerLevel;
    Weapon weapon;
    ArrayList<Weapon> weaponsCollection;

    public MainCharacter(String name, String desc, int powerLevel){
        super(name, desc, powerLevel); 
        this.weaponsCollection = new ArrayList<Weapon>();

    }
    // Updates the power level of user/player
    public void setPowerLevel(int newPowerLevel){
        powerLevel = newPowerLevel;
    }

    public void getWeapon(Weapon weapon){
        //get name maybe??
    }

    public void addWeapon(Weapon weapon){
        weaponsCollection.add(weapon);
        System.out.println("You have added " + weapon + "to your Collection!");
    }

    public void removeWeapon(Weapon weapon){
        weaponsCollection.remove(weapon);
        System.out.println("You have lost " + weapon + "from your Collection...");
    }

    // 

}
