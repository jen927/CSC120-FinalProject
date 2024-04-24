import java.util.ArrayList;
import java.io.File; // Import the File class
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader; //Import to print specific lines in a file

public class Area {

    String name;
    int maxEnemies;
    ArrayList<Enemies> enemies;

    // constructor
    public Area(String name, int maxEnemies) { //will move maxEnemies variable to description file instead.
        this.enemies = new ArrayList<Enemies>(maxEnemies);
        this.name = name;
    }

    private String getName() { //gets name of area
        return name;
    }

    public void getDesc() { // will file read the descriptions of area based on the name given

        // file containing info on Location
        File areaFile = new File("AreaDescriptions.txt");

        //Will read Description of Area file
        try {
            BufferedReader areaBuffReader = new BufferedReader(new FileReader(areaFile));

            // area name
            String areaName = "Area: " + getName();

            // current line
            String line = areaBuffReader.readLine();

            while (line != null) { // reads whole file until empty
                if (line.startsWith(areaName)) { //looks for the name of current area
                    System.out.println("Current " + line);
                    line = areaBuffReader.readLine(); //goes to next line
                    while (!line.startsWith("-")) { //will read the file line by line until '-' is presented
                        System.out.println(line);
                        line = areaBuffReader.readLine();
                    }
                    if (line.startsWith("-")) { //ends loop after the description is read.
                        line = null;
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

}
