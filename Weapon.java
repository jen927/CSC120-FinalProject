public class Weapon { //WORK IN PROGRESS

    String name;
    int powerLevel;
    String desc;

    public Weapon(String name){ 
        this.name = name;
        this.powerLevel = 0;
        this.desc = "";
    }

    public String getName(){
        return name;
    }

    public String getDesc(){ 
        return "Description: " + desc;
    }

    //testing
    public static void main(String[] args) {
        //Weapon wepOne = new Weapon("Wolf's Bane", 1, "Materials made from blah blah blah.");
        //wepOne.getStats();
    }
}
