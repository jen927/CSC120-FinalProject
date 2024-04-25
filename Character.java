public class Character {

    String name; //make private or public
    //String desc;
    int powerLevel;

    Character(String name, int powerLevel){
        this.name = name;
        this.powerLevel = powerLevel;
    }

    //returns name of character
    public String getName(){
        return name;
    }

    //return level of character
    public int getPowerLevel(){
        return powerLevel;
    }
}