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
            player.addWeapon(new Weapon("Dull Sword"));
            // Starting Area
            Area currentArea = new Area("Area: Golden Luck");
            // Starting Location
            SubLocation currentLocation = new SubLocation(currentArea.getName(), "Location0");
            // Arraylist of traveled locations
            ArrayList<SubLocation> locationsTraveled = new ArrayList<SubLocation>();
            locationsTraveled.add(currentLocation);
            // game loop
            while (stillPlaying) {

                // loops same choices until new area is established.
                boolean ifSameArea = (currentArea.getName()).equals(currentLocation.getCurrentArea());

                while (ifSameArea) {
                    // prints current area
                    System.out.println(currentArea.getName());

                    // prints current location in Area
                    System.out.println(currentLocation.getCurrentLocation());

                    // Player's next action
                    userResponse = userInput.nextLine().toUpperCase();

                    // choices
                    if (userResponse.contains("NORTH") || userResponse.contains("SOUTH")
                            || userResponse.contains("EAST")
                            || userResponse.contains("WEST")) {

                        if (userResponse.contains("NORTH")) {
                            // An arraylist with next location's info: area and location
                            ArrayList<String> nextLocation = currentLocation.getNextLocation("North");
                            if (nextLocation.get(1).contains("Location")) {
                                currentLocation = new SubLocation(nextLocation.get(0), nextLocation.get(1));
                                locationsTraveled.add(currentLocation);
                            } else {
                                System.out.println(nextLocation.get(1));
                            }
                        } else if (userResponse.contains("SOUTH")) {
                            // An arraylist with next location's info: area and location
                            ArrayList<String> nextLocation = currentLocation.getNextLocation("South");
                            if (nextLocation.get(1).contains("Location")) {
                                currentLocation = new SubLocation(nextLocation.get(0), nextLocation.get(1));
                                locationsTraveled.add(currentLocation);
                            } else {
                                System.out.println(nextLocation.get(1));
                            }
                        } else if (userResponse.contains("EAST")) {
                            // An arraylist with next location's info: area and location
                            ArrayList<String> nextLocation = currentLocation.getNextLocation("East");
                            if (nextLocation.get(1).contains("Location")) {
                                currentLocation = new SubLocation(nextLocation.get(0), nextLocation.get(1));
                                locationsTraveled.add(currentLocation);
                            } else {
                                System.out.println(nextLocation.get(1));
                            }
                        } else if (userResponse.contains("WEST")) {
                            // An arraylist with next location's info: area and location
                            ArrayList<String> nextLocation = currentLocation.getNextLocation("West");
                            if (nextLocation.get(1).contains("Location")) {
                                currentLocation = new SubLocation(nextLocation.get(0), nextLocation.get(1));
                                locationsTraveled.add(currentLocation);
                            } else {
                                System.out.println(nextLocation.get(1));
                            }
                        }
                        //battle keywords
                    } else if (userResponse.contains("GRAB")
                            || userResponse.contains("COLLECT")) {
                        //if no weapon in location        
                        if (currentLocation.getWeapon().equals("N/A")) {
                            System.out.println("There's nothing to grab...");
                        } else if(currentLocation.getWeapon().equals("Swift Lock Sword")){
                            //adds a special weapon to collection
                            player.weaponsCollection.clear();
                            player.addWeapon(new Weapon("Swift Lock Sword"));
                        } else {
                            //adds weapon to collection
                            player.addWeapon(new Weapon(currentLocation.getWeapon()));
                        }
                    } else if (userResponse.contains("ATTACK") || userResponse.contains("BATTLE") || userResponse.contains("FIGHT")) {
                        currentLocation.setEnemies();
                        if (currentLocation.enemies.isEmpty()) {
                            System.out.println("There's nothing to attack...");
                        } else {
                            if (player.getPowerLevel() < currentLocation.enemies.get("Wizard Bandit")
                                    || player.getPowerLevel() < currentLocation.enemies.get("Opponent")) {
                                System.out.println(
                                        "Oh, no you weren't strong enough! You tried with all you might but these wizards... Better luck next time!");
                                stillPlaying = false;
                                ifSameArea = false;
                            } else {
                                System.out.println(
                                        player.weaponsCollection.get(player.weaponsCollection.size() - 1).getAttack());
                                System.out.println(currentLocation.getWonStatement());
                            }
                        }
                      //gets player stats  
                    } else if (userResponse.contains("STATS")) {
                        System.out.println("Name: " + player.getName());
                        System.out.println("Level: " + player.getPowerLevel());
                        player.listWeapons();
                      //lists keywords/commands  
                    } else if (userResponse.contains("HELP") || userResponse.contains("MENU")) {
                        System.out.println("Available commands:");
                        System.out.println("    North");
                        System.out.println("    South");
                        System.out.println("    East");
                        System.out.println("    West");
                        System.out.println("    Stats");
                        System.out.println("    Attack");
                        System.out.println("    Battle");
                        System.out.println("    Fight");
                        System.out.println("    Grab");
                        System.out.println("    Collect");
                    }

                    else {
                        System.out.println("Invalid response.");
                    }
                    //determines if player enters a new area
                    ifSameArea = (currentArea.getName()).equals(currentLocation.getCurrentArea());
                }
                //determines whether player enters new area based on level
                int level = new Area(currentLocation.getCurrentArea()).getRequiredLevel();
                if (level > player.getPowerLevel()) {
                    System.out.println(
                            "Sorry this area is locked until you reach level " + level);
                    currentLocation = locationsTraveled.get(locationsTraveled.size() - 2);
                } else {
                    System.out.println("New Area Unlocked!");
                    currentArea = new Area(currentLocation.getCurrentArea());

                }

            }

            // closes scanner
            userInput.close();
        }
    }
}
