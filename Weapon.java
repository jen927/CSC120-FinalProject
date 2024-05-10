import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Weapon {

    String name;
    String description;
    File weaponFile;

    public Weapon(String name) {
        this.weaponFile = new File("Weapons.txt");
        this.name = name;
        this.description = "";
    }

    /**
     * gets name of weapon
     * 
     * @return the name of weapon
     */
    public String getName() {
        return name;
    }

    /**
     * gets info about weapon from weapons.txt
     * 
     * @param info the subject, ex. description
     * @return the info provided of the subject
     */
    private void getDesc(String info) {
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

    /**
     * gets attack move from text file
     * 
     * @return description of attack move
     */
    public String getAttack() {
        getDesc("Attack:");
        return description;
    }

    /**
     * gets description of weapon
     * 
     * @return description of weapon
     */
    public String getDescription() {
        getDesc("Description:");
        return description;
    }

    // testing
    public static void main(String[] args) {
        Weapon wep1 = new Weapon("Dull Sword");
        System.out.println(wep1.getDescription());
    }
}
