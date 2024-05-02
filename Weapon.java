import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Weapon { //WORK IN PROGRESS

    String name;
    int powerLevel;
    String description;
    File weaponFile;

    public Weapon(String name){ 
        this.weaponFile = new File("Weapons.txt");
        this.name = name;
        this.powerLevel = 0;
        this.description = "";
    }

    public String getName(){
        return name;
    }

    private void getDesc(String info){ 
        try {
            BufferedReader weaponBuffReader = new BufferedReader(new FileReader(weaponFile));

            // area name
            String weaponName = "Name: " + getName();

            // current line
            String line = weaponBuffReader.readLine();

            while (line != null) { // reads whole file until empty
                if (line.startsWith(weaponName)) { // looks for the name of current area
                    while (!line.startsWith(info)) { // Looks for specific info
                        line = weaponBuffReader.readLine();
                    }
                    StringBuilder onlyInfo = new StringBuilder(line);
                    description = onlyInfo.substring(onlyInfo.indexOf(":") + 2); // substring the info after label
                    line = null; // ends loop
                } else { // reads file until the area name is located.
                    line = weaponBuffReader.readLine();
                }
            }
            // closes scanners
            weaponBuffReader.close();

        } catch (IOException e) { // incase of error
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String getAttack(){
        getDesc("Attack:");
        return description;
    }

    public int getPowerLevel(){
        getDesc("Level:");
        return Integer.parseInt(description);
    }

    public String getDescription(){
        getDesc("Description:");
        return description;
    }

    //testing
    public static void main(String[] args) {
        Weapon wep1 = new Weapon("Dull Sword");
        System.out.println(wep1.getPowerLevel());
        System.out.println(wep1.getDescription());
    }
}
