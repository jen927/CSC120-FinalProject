import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

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
        }
        while (stillPlaying) {
            System.out.println("What is your name warrior?");
            userResponse = userInput.nextLine().toUpperCase();
            MainCharacter player = new MainCharacter(userResponse);
            System.out.println("Let you life changing journey begin " + player.getName() + "...");
            // Starting Area
            Area currentArea = new Area("Golden Luck");
            //System.out.println(currentArea.getName());
            // Starting Location
            SubLocation currentLocation = new SubLocation(currentArea.getName(), "Location0");
            //System.out.println(currentLocation.getCurrentLocation());

            // Let user decide action.
            //userResponse = userInput.nextLine().toUpperCase();

            // game loop
            while (stillPlaying) {

                boolean ifSameArea = (currentArea.getName()).equals(currentLocation.getCurrentArea());

                while (ifSameArea) {
                    System.out.println(currentArea.getName());
                    System.out.println(currentLocation.getCurrentLocation());
                    userResponse = userInput.nextLine().toUpperCase();

                    // choices
                    if (userResponse.contains("NORTH") || userResponse.contains("SOUTH")
                            || userResponse.contains("EAST")
                            || userResponse.contains("WEST")) {

                        if (userResponse.contains("NORTH")) {
                            ArrayList<String> nextLocation = currentLocation.getNextLocation("North");
                            if (nextLocation.get(1).contains("Location")) {
                                currentLocation = new SubLocation(nextLocation.get(0), nextLocation.get(1));
                            } else {
                                System.out.println(nextLocation.get(1));
                            }
                        } else if (userResponse.contains("SOUTH")) {
                            ArrayList<String> nextLocation = currentLocation.getNextLocation("South");
                            //System.out.println("Location: "+nextLocation.get(0));
                           // System.out.println("Location: "+nextLocation.get(1));
                            if (nextLocation.get(1).contains("Location")) {
                                currentLocation = new SubLocation(nextLocation.get(0), nextLocation.get(1));
                            } else {
                                System.out.println(nextLocation.get(1));
                            }
                        } else if (userResponse.contains("EAST")) {
                            ArrayList<String> nextLocation = currentLocation.getNextLocation("East");
                            //System.out.println("Location: "+nextLocation.get(0));
                            //System.out.println("Location: "+nextLocation.get(1));
                            if (nextLocation.get(1).contains("Location")) {
                                currentLocation = new SubLocation(nextLocation.get(0), nextLocation.get(1));
                            } else {
                                System.out.println(nextLocation.get(1));
                            }
                        } else if (userResponse.contains("WEST")) {
                            ArrayList<String> nextLocation = currentLocation.getNextLocation("West");
                            if (nextLocation.get(1).contains("Location")) {
                                currentLocation = new SubLocation(nextLocation.get(0), nextLocation.get(1));
                            } else {
                                System.out.println(nextLocation.get(1));
                            }
                        }

                    } else if (userResponse.contains("GRAB") || userResponse.contains("GET")
                            || userResponse.contains("COLLECT")) {
                        if (currentLocation.getWeapon().equals("N/A")) {
                            System.out.println("There's nothing to grab...");
                            //userResponse = userInput.nextLine().toUpperCase();
                        } else {
                            player.addWeapon(new Weapon(currentLocation.getWeapon()));
                            //userResponse = userInput.nextLine().toUpperCase();
                        }
                    } else if (userResponse.contains("ATTACK") || userResponse.contains("BATTLE")) {
                        currentLocation.setEnemies();
                        if (currentLocation.enemies.isEmpty()) {
                            System.out.println("There's nothing to attack...");
                            //userResponse = userInput.nextLine().toUpperCase();
                        } else {
                            if (player.getPowerLevel() < currentLocation.enemies.get("Wizard Bandit")) {
                                System.out.println(
                                        "Oh, no you weren't strong enough! You tried with all you might but these wizard bandits are level "
                                                + currentLocation.enemies.get("Wizard Bandit") + " and you are level "
                                                + player.getPowerLevel());
                                stillPlaying = false;
                                ifSameArea = false;
                            } else {
                                System.out.println(player.weaponsCollection.get(-1).getAttack());
                                System.out.println(currentLocation.getWonStatement());
                               // userResponse = userInput.nextLine().toUpperCase();
                            }
                        }
                    }

                    else {
                        System.out.println("Invalid response.");
                        System.out.println(currentLocation.getCurrentLocation());
                       // userResponse = userInput.nextLine().toUpperCase();
                    }
                    //System.out.println("line 129: " + currentLocation.getCurrentArea());
                   // System.out.println("line 130: " + currentArea.getName());
                    ifSameArea = (currentArea.getName()).equals(currentLocation.getCurrentArea());
                    //userResponse = userInput.nextLine().toUpperCase();

                }

                if (new Area(currentLocation.getCurrentArea()).getRequiredLevel() < player.getPowerLevel()) {
                    System.out.println(
                            "Sorry this area is locked until you reach level " + currentArea.getRequiredLevel());
                } else {
                    System.out.println("line 138: " + currentLocation.getCurrentArea());
                    currentArea = new Area(currentLocation.getCurrentArea());
                    System.out.println("line 140: " + currentArea.getName());
                    System.out.println(currentLocation.getCurrentLocation());
                    userResponse = userInput.nextLine().toUpperCase();
                }
            }

            // closes scanner
            userInput.close();
        }
    }
}
