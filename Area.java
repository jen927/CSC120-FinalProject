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
        this.name = "Area: " + name;
        this.areaFile = new File("AreaDescriptions.txt");
        this.requiredLevel = 0;
        this.locations = new ArrayList<SubLocation>();
        this.description = null;
    }

    public String getName() { // gets name of area
        return name;
    }

    private void setDescripton(String info) {
        try {
            BufferedReader areaBuffReader = new BufferedReader(new FileReader(areaFile));

            // current line
            String line = areaBuffReader.readLine();

            while (line != null) { // reads whole file until empty
                if (line.startsWith(name)) { // looks for the name of current area
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
        requiredLevel = Integer.parseInt(description);
        return requiredLevel;
    }

    

    // testing
    public static void main(String[] args) {
        Area currentArea = new Area("Golden Luck");

            // Starting Location
            SubLocation currentLocation = new SubLocation(currentArea.getName(), "Location0");
            System.out.println(currentLocation.getCurrentLocation());

            ArrayList<String> nextlocation = currentLocation.getNextLocation("North");
            System.out.println(nextlocation);
            currentLocation = new SubLocation(nextlocation.get(0), nextlocation.get(1));
    }

}
