import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SubLocation {
    
    String locationName;
    String description;
    Area currentArea;
    boolean northPath;
    boolean southPath;
    boolean westPath;
    boolean eastPath;
    String weapon;
    String areaName;
    File areaFile;
    ArrayList<String> locationInfo;

    public SubLocation(String areaName, String locationName){
        this.areaFile = new File("AreaDescriptions.txt");
        this.areaName = areaName;
        this.locationName = locationName;
        this.description = "Didn't work";
        this.northPath = false;
        this.southPath = false;
        this.westPath = false;
        this.eastPath = false;
        this.weapon = "Dull Sword";
    }

    private void setDesc(String info){
        try {

            BufferedReader locationBuffReader = new BufferedReader(new FileReader(areaFile));

            // current line
            String line = locationBuffReader.readLine();

            while (line != null) { // reads whole file until empty
                if (line.startsWith(areaName)) { // looks for the name of current area
                    while (!line.startsWith(locationName)) {
                        line = locationBuffReader.readLine();
                    }
                    while(!line.startsWith(info)){
                        line = locationBuffReader.readLine();
                    }
                    description = line; //substring the info after label
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

    public String getLocation(){
        setDesc("Description");
        return description;
    }

    //  WORK IN PROGRESS    
    public String getWeapon() { // will file read the descriptions of area based on the name given
        setDesc("Weapon");
        return description;
    }

    
    public static void main(String[] args) {
        SubLocation location0 = new SubLocation("Area: Golden Luck", "Location0");
        System.out.println(location0.getLocation());
        System.out.println(location0.getWeapon());
    }

}
