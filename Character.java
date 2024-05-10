public class Character {

    String name;
    int powerLevel;

    Character(String name, int powerLevel) {
        this.name = name;
        this.powerLevel = powerLevel;
    }

    /**
     * gets name of character
     * 
     * @return name of character
     */
    public String getName() {
        return name;
    }

    /**
     * gets power level
     * 
     * @return current power level of character
     */
    public int getPowerLevel() {
        return powerLevel;
    }
}