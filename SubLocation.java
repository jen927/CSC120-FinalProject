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
        this.description = "Didn't work";
        this.weapon = "Dull Sword";
        this.enemies = new Hashtable<String, Integer>();
    }

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

    // gets description of area
    public String getCurrentLocation() {
        setDesc("Description");
        return description;
    }

    public String getCurrentArea(){
        return areaName;
    }

    public String getWeapon() { // will file read the descriptions of area based on the name given
        setDesc("Weapon");
        return description;
    }

    public void setEnemies() {
        setDesc("Enemies");
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

    public String getWonStatement(){
        setDesc("Won:");
        return description;
    }

    public ArrayList<String> getNextLocation(String direction) {
        setDesc(direction);
        String[] nextLocation = description.split("=");
        ArrayList<String> newLocation= new ArrayList<>(2);
        if (nextLocation[1].contains("-")) {
            String[] locationInfo = nextLocation[1].split("-");
            newLocation.add(locationInfo[0]);
            newLocation.add(locationInfo[1]);
            return newLocation;
        } else {
            newLocation.add(areaName);
            newLocation.add(nextLocation[1]);
            return newLocation;
        }
    }



    // TESTING
    public static void main(String[] args) {
        SubLocation location0 = new SubLocation("Area: Golden Luck", "Location0");
        System.out.println(location0.getCurrentLocation());
        System.out.println(location0.getWeapon());
        System.out.println(location0.getNextLocation("South"));
    }

}
