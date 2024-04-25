import java.util.ArrayList;
import java.io.File; // Import the File class
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader; //Import to print specific lines in a file
import java.util.Hashtable;

public class Area {

    String name;
    int totalEnemies;
    Hashtable<String, Integer> enemies;
    File areaFile; // file containing info on Area
    int requiredLevel;
    ArrayList<SubLocation> locations;

    // constructor
    public Area(String name, int totalEnemies) { // will move maxEnemies variable to description file instead.
        this.enemies = new Hashtable<>(totalEnemies);
        this.name = name;
        this.areaFile = new File("AreaDescriptions.txt");
        this.requiredLevel = 0;
    }

    private String getName() { // gets name of area
        return name;
    }

    private void setEnemies() { //file reads to get enemy name and their power level into a hashtable
        
        try { // Will read Description of Area file
            BufferedReader areaBuffReader = new BufferedReader(new FileReader(areaFile));

            // area name
            String areaName = "Area: " + getName();

            // current line
            String line = areaBuffReader.readLine();

            while (line != null) { // reads whole file until a line is empty
                if (line.startsWith(areaName)) { // looks for the name of current area
                    line = areaBuffReader.readLine(); // goes to next line
                    while (!line.startsWith("Enemies:")) { // looks for enemies line
                        line = areaBuffReader.readLine();
                    }
                    line = line.substring(8); //only uses info after 'Enemies:'
                    //split line at '=', seperating type of enemy and their power level
                    String[] typeAndLevel = line.split("=");
                    String[] type = typeAndLevel[0].split(",");
                    String[] level = typeAndLevel[1].split(",");
                    for (int i = 0; i < type.length; i++ ){
                        String name = type[i];
                        int powerLevel = Integer.parseInt(level[i]);
                        Character enemy = new Character(name, powerLevel);
                        enemies.put(enemy.getName(), enemy.getPowerLevel());
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

    public void getEnemies(){
        setEnemies();

    }

    private void setRequiredLevel() {
        //reads file to find the required level to enter area
        try {
            BufferedReader areaBuffReader = new BufferedReader(new FileReader(areaFile));

            // area name
            String areaName = "Area: " + getName();

            // current line
            String line = areaBuffReader.readLine();

            while (line != null) { // reads whole file until empty
                if (line.startsWith(areaName)) { // looks for the name of current area
                    while (!line.startsWith("Level")) { //Looks for Level line
                        line = areaBuffReader.readLine();
                    }
                    requiredLevel = Integer.parseInt(line.substring(15)); //stores Level as int
                    line = null; //ends loop
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

    //gets required level
    public int getRequiredLevel(){
        setRequiredLevel();
        return requiredLevel;
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
                    while (!line.startsWith("Level")) { // will read the file line by line until 'Level' is presented
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

    public void goNorth(){

    }

    //testing
    public static void main(String[] args) {
        Area area1 = new Area("Weeping Mountains", 0);
        int num = area1.getRequiredLevel();
        System.out.println(num);
    }

}
