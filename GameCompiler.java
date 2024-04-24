import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.Reader;
//import java.io.FileReader;
import java.util.Scanner; // Import the Scanner class to read text files
//import java.io.BufferedReader; //Import to print specific lines in a file

public class GameCompiler {
    public static void main(String[] args) {

        // Prints out premise of game.
        try {
            File myFile = new File("gameOpening.txt");

            Scanner fileReader = new Scanner(myFile); // reads file

            // prints the whole file line by line
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                System.out.println(data);
            }

            // closes scanner
            fileReader.close();

        } catch (FileNotFoundException e) { // incase of error
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // flag to indicate status of game
        boolean stillPlaying = true;

        // Allows for User input
        Scanner userInput = new Scanner(System.in);

        // Stores User Input
        String userResponse = "";

        // Responses indicates whether they'll continue on journey
        userResponse = userInput.nextLine().toUpperCase();
        while (!userResponse.equals("YES") && !userResponse.equals("NO")) {
            System.out.println("Invalid answer. Please say Yes or No.");
            userResponse = userInput.nextLine().toUpperCase();
        }
        if (userResponse.equals("NO")) {
            System.out.println(
                    "You chose not to go on this journey. You are a failure and your family disowned you.. You forever regretted not going beyond these walls...");
            stillPlaying = false;
        } else if (userResponse.equals("YES")) {
            System.out.println("Let you life changing journey begin...");
        }
        // game loop
        while (stillPlaying) {
            // Starting Location
            Area currentArea = new Area("Golden Luck", 0);

            // Reads area0 info
            currentArea.getDesc();

            //Lets user decide action.
            userResponse = userInput.nextLine().toUpperCase();
            
            while(!userResponse.contains("NORTH")){
                if(userResponse.contains("SOUTH") || userResponse.contains("WEST") || userResponse.contains("EAST")){
                    System.out.println("Only a wall as tall as the clouds meets your eye...");
                    userResponse = userInput.nextLine().toUpperCase();
                }
                else{
                    System.out.println("Invalid choice...\n");
                    currentArea.getDesc();
                    userResponse = userInput.nextLine().toUpperCase();
                }
            }
            //new area unlocked!!
            currentArea = new Area("Flowery Field", 3);
            
            currentArea.getDesc();

            stillPlaying = false; //placeholder for end of game/loop...
            
        }

        // closes scanner
        userInput.close();
    }
}
