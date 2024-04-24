public class Weapon {

    String name;
    int powerLevel;
    String desc;

    public Weapon(String name, int powerLevel, String desc){ 
        this.powerLevel = powerLevel;
        this.desc = desc;//store weapons in an arrayList
    }

    public String getName(){
        return name;
    }

    public void getStats(){ //Like a toString?
        System.out.println("\n Name: " + name + "\n Power Level: " + powerLevel + "\n Description: " + desc);
    }

    //testing
    public static void main(String[] args) {
        Weapon wepOne = new Weapon("Wolf's Bane", 1, "Materials made from blah blah blah.");
        wepOne.getStats();
    }
}
