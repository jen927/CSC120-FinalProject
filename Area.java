import java.util.ArrayList;
import java.io.File; // Import the File class
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader; //Import to print specific lines in a file

public class Area {

    String name;

    File areaFile; // file containing info on Area
    int requiredLevel;
    ArrayList<SubLocation> locations;
    String description;

    // constructor
    public Area(String name) { // will move maxEnemies variable to description
                               // file instead.
        this.name = name;
        this.areaFile = new File("AreaDescriptions.txt");
        this.requiredLevel = 0;
        this.locations = new ArrayList<SubLocation>();
        this.description = null;
    }

    private String getName() { // gets name of area
        return name;
    }

    private void setDescripton(String info) {
        try {
            BufferedReader areaBuffReader = new BufferedReader(new FileReader(areaFile));

            // area name
            String areaName = "Area: " + getName();

            // current line
            String line = areaBuffReader.readLine();

            while (line != null) { // reads whole file until empty
                if (line.startsWith(areaName)) { // looks for the name of current area
                    while (!line.startsWith(info)) { // Looks for Level line
                        line = areaBuffReader.readLine();
                    }
                    StringBuilder onlyInfo = new StringBuilder(line);
                    description = onlyInfo.substring(onlyInfo.indexOf(":") + 2); // substring the info after label
                    line = null; // ends loop
                } else { // reads file until the area name is located.
                    line = areaBuffReader.readLine();
                }
            }
            // closes scanners
            areaBuffReader.close();

        } catch (IOException e) { // incase of error
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public int getRequiredLevel() { // gets required level
        setDescripton("Level");
        requiredLevel = Integer.parseInt(description.substring(15));
        return requiredLevel;
    }

    // stores location
    private void setLocations() {
        try {
            BufferedReader areaBuffReader = new BufferedReader(new FileReader(areaFile));

            // area name
            String areaName = "Area: " + getName();

            // current line
            String line = areaBuffReader.readLine();

            while (line != null) { // reads whole file until empty
                if (line.startsWith(areaName)) { // looks for the name of current area
                    line = areaBuffReader.readLine();
                    while (!line.startsWith("-")) { // Looks for Level line
                        if (line.startsWith("Location")) {
                            locations.add(new SubLocation(areaName, line.substring(0, 9)));
                        }
                        line = areaBuffReader.readLine();
                    }

                } else { // reads file until the area name is located.
                    line = areaBuffReader.readLine();
                }
            }
            // closes scanners
            areaBuffReader.close();

        } catch (IOException e) { // incase of error
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // create locations of area
    public ArrayList<SubLocation> getLocations() {
        setLocations();
        return locations;
    }

    // 
    public ArrayList<String> getNextLocation(SubLocation currentLocation, String direction) {
        return currentLocation.getNextLocation(direction);

        
    }

    // testing
    public static void main(String[] args) {
        Area area1 = new Area("Golden Luck");
        area1.setLocations();
        SubLocation loc0 = new SubLocation("Golden Luck", "Location0");
        area1.getNextLocation(loc0, "South");
        System.out.println(area1.locations.size());
    }

}
