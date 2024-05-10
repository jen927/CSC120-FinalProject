import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public class SubLocation {

    String locationName;
    String description;
    String weapon;
    String areaName;
    File areaFile;
    ArrayList<String> locationInfo;
    Hashtable<String, Integer> enemies;

    public SubLocation(String areaName, String locationName) {
        this.areaFile = new File("AreaDescriptions.txt");
        this.areaName = areaName;
        this.locationName = locationName;
        this.description = "placeholder";
        this.weapon = "Dull Sword";
        this.enemies = new Hashtable<String, Integer>();
    }

    /**
     * Sets the variable description to the specific info we are looking for in text
     * file
     * 
     * @param info the subject we are locating from the text file, ex. the next
     *             location
     */
    private void setDesc(String info) {
        try {

            BufferedReader locationBuffReader = new BufferedReader(new FileReader(areaFile));

            // current line
            String line = locationBuffReader.readLine();

            while (line != null) { // reads whole file until empty
                if (line.startsWith(areaName)) { // looks for the name of current area
                    while (!line.startsWith(locationName)) {
                        line = locationBuffReader.readLine();
                    }
                    while (!line.startsWith(info)) {
                        line = locationBuffReader.readLine();
                    }
                    StringBuilder onlyInfo = new StringBuilder(line);
                    description = onlyInfo.substring(onlyInfo.indexOf(":") + 2); // substring the info after label
                    line = null; // ends loop after the description is read.
                } else { // reads file until the area name is located.
                    line = locationBuffReader.readLine();
                }
            }

            // closes scanners
            locationBuffReader.close();

        } catch (IOException e) { // incase of error
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Gets description of the current Location from text file
     * 
     * @return descripton
     */
    public String getCurrentLocation() {
        if (!locationName.contains("Location")) {
            return locationName;
        } else {
            setDesc("Description");
            return description;
        }

    }

    /**
     * gets the current Area of the player from text file based on location
     * 
     * @return the name of the area
     */
    public String getCurrentArea() {
        return areaName;
    }

    /**
     * gets name of weapon from text file based on location
     * 
     * @return the name of weapon
     */
    public String getWeapon() {
        setDesc("Weapon");
        return description;
    }

    /**
     * sets the enemies in a hashtable, paired with their power level
     * 
     */
    public void setEnemies() {
        setDesc("Enemies");
        // if there are enemies present:
        if (!description.contains("N/A")) {
            String[] typeAndLevel = description.split("=");
            String[] type = typeAndLevel[0].split(",");
            String[] level = typeAndLevel[1].split(",");
            for (int i = 0; i < type.length; i++) {
                String name = type[i];
                int powerLevel = Integer.parseInt(level[i]);
                Character enemy = new Character(name, powerLevel);
                enemies.put(enemy.getName(), enemy.getPowerLevel());
            }
        }
    }

    /**
     * gets the number of enemies in location
     * 
     * @return the number of enemies
     */
    public int getNumEnemies() {
        return enemies.size();
    }

    /**
     * gets winning statement from file if player defeats enemy
     * 
     * @return statement
     */
    public String getWonStatement() {
        setDesc("Won:");
        return description;
    }

    /**
     * gets the area and location description of the next location in a arraylist
     * 
     * @param direction choice of direction ex. north, south, etc.
     * @return the arraylist with the area name and location description
     */
    public ArrayList<String> getNextLocation(String direction) {
        setDesc(direction);
        ArrayList<String> newLocation = new ArrayList<>(2);
        if (description.contains("-")) {
            String[] locationInfo = description.split("-");
            newLocation.add("Area: " + locationInfo[0]);
            newLocation.add(locationInfo[1]);
            return newLocation;
        } else {
            newLocation.add(areaName);
            newLocation.add(description);
            return newLocation;

        }
    }

    // TESTING
    public static void main(String[] args) {
        Area currentArea = new Area("Golden Luck");
        SubLocation currentLocation = new SubLocation(currentArea.getName(), "Location0");
        // System.out.println(currentLocation.getCurrentLocation());
        ArrayList<String> nextlocation = currentLocation.getNextLocation("South");
        System.out.println("line 126: Area: " + nextlocation.get(0));
        System.out.println("line 126: Location: " + nextlocation.get(1));
        currentLocation = new SubLocation(nextlocation.get(0), nextlocation.get(1));
        System.out.println(currentLocation.getCurrentLocation());
    }

}
