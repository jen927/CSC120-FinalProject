import java.util.ArrayList;
import java.io.File; // Import the File class
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader; //Import to print specific lines in a file

public class Area {

    String name;
    int maxEnemies;
    ArrayList<Enemies> enemies;
    // file containing info on Location
    File areaFile;
    String powerLevel;
    int levelRequired;

    // constructor
    public Area(String name, int maxEnemies) { // will move maxEnemies variable to description file instead.
        this.enemies = new ArrayList<Enemies>(maxEnemies);
        this.name = name;
        this.areaFile = new File("AreaDescriptions.txt");
    }

    private String getName() { // gets name of area
        return name;
    }

    private String getLevel() { //Need to find a more efficent way of getting info from file
        
        try {
            BufferedReader areaBuffReader = new BufferedReader(new FileReader(areaFile));

            // area name
            String areaName = "Area: " + getName();

            // current line
            String line = areaBuffReader.readLine();

            //String powerLevel = "";

            while (line != null) { // reads whole file until empty
                if (line.startsWith(areaName)) { // looks for the name of current area
                    while (!line.startsWith("Level")) { //Looks for Level line
                        line = areaBuffReader.readLine();
                    }
                    powerLevel = (line.substring(15)); //stores Level
                    line = null; //ends loop
                } else { // reads file until the area name is located.
                    line = areaBuffReader.readLine();
                }
            }
            // closes scanners
            areaBuffReader.close();
            return powerLevel;

        } catch (IOException e) { // incase of error
            System.out.println("An error occurred.");
            e.printStackTrace();
        } 
        return powerLevel;
    }

    public int getLevelRequired(){
        String strLevel = getLevel();
        int levelRequired = Integer.parseInt(strLevel);
        return levelRequired;
    }

    public void getDesc() { // will file read the descriptions of area based on the name given

        // Will read Description of Area file
        try {
            BufferedReader areaBuffReader = new BufferedReader(new FileReader(areaFile));

            // area name
            String areaName = "Area: " + getName();

            // current line
            String line = areaBuffReader.readLine();

            while (line != null) { // reads whole file until empty
                if (line.startsWith(areaName)) { // looks for the name of current area
                    System.out.println("Current " + line);
                    line = areaBuffReader.readLine(); // goes to next line
                    while (!line.startsWith("Level")) { // will read the file line by line until '-' is presented
                        System.out.println(line);
                        line = areaBuffReader.readLine();
                    }
                    line = null; // ends loop after the description is read.
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

    public static void main(String[] args) {
        Area area1 = new Area("Weeping Mountains", 0);
        int num = area1.getLevelRequired();
        System.out.println(num);
    }

}
