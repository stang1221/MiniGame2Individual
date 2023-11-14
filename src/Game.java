import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Game {
    private static final HashMap<Integer, Room> roomsMap = new HashMap();
    private static int currentRoomId;
    private static Player player1 = new Player(); // Create an instance of the Player class


    public Game() {
    }

//    private static void displayRooms() {
//        Iterator var0 = roomsMap.values().iterator();
//
//        while(var0.hasNext()) {
//            Room room = (Room)var0.next();
//            System.out.println(room);
//            System.out.println("----");
//        }
//
//    }

    public static void main(String[] args) {

        try {
            loadRoomsFromFile("room.txt");
//            displayRooms();
            playGame();
        } catch (IOException var2) {
            System.out.println("Error reading room data file: " + var2.getMessage());
        }

    }

    private static void loadRoomsFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = reader.readLine()) != null) {
            if (line.equals("----")) { // Detect the start of a new room entry
                int roomId = Integer.parseInt(reader.readLine()); // Read room ID
                String roomName = reader.readLine(); // Read room name
                String roomDescription = reader.readLine(); // Read room description
                reader.readLine(); // Skip the "false" line
                reader.readLine(); // Skip the "false" line

                String directionLine = reader.readLine(); // Read room directions
                HashMap<String, Integer> directions = new HashMap<>();// Stores directions and IDs in a hashmap

                String[] elements = directionLine.split(",\\s*");// Splits the directionLine into an array of
                // strings based on a comma-- separates the directions and their associated room IDs.


                for (String element : elements) { // Loop processes each element in the elements array, which represents a direction and a room ID:
                    String[] keyValue = element.split(":\\s*"); //Splits each element into a key (the direction) and a value (the room ID).


                    if (keyValue.length == 2) {
                        String key = keyValue[0].trim();
                        int value = Integer.parseInt(keyValue[1].trim());
                        directions.put(key, value);
                    }
                }

                boolean visited = Boolean.parseBoolean(reader.readLine()); // Read and assign the visited value
                Puzzle puzzle = null;
                ArrayList<Item> listOfItems = new ArrayList<>();
                reader.readLine();
                String itemsLine = reader.readLine(); // Read the item line

                if (!itemsLine.equals("")) {
                    String itemDesc = reader.readLine();
                    Item item = new Item(itemsLine, itemDesc);
                    listOfItems.add(item);
                    String puzzleName = reader.readLine();

                    // Check if puzzleName is empty
                    if (!puzzleName.isEmpty()) {
                        String puzzleDescription = reader.readLine();
                        String puzzleAnswer = reader.readLine();

                        // Check if attemptsAllowed line is not empty before parsing it
                        String attemptsAllowedString = reader.readLine();
                        int attemptsAllowed = 0;
                        if (!attemptsAllowedString.isEmpty()) {
                            attemptsAllowed = Integer.parseInt(attemptsAllowedString);
                        }

                        // Create a Puzzle object
                        puzzle = new Puzzle(puzzleName, puzzleDescription, puzzleAnswer, attemptsAllowed);
                    } else {
                        // Set puzzle to null if puzzleName is empty
                        puzzle = null;
                    }
                }

// Now you have `puzzle` set to `null` if puzzleName is empty.


                Room room = new Room(roomId, roomName, roomDescription, directions, listOfItems, puzzle);
                room.setVisited(visited);


                roomsMap.put(roomId, room); // Add the room to the map
//                System.out.println(room);
            }
        }

        reader.close();
    }


    private static void playGame() {
        currentRoomId = 1;
        Room currentRoom = roomsMap.get(currentRoomId);
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the Adventure Game!");
        System.out.println("You are currently in the " + currentRoom.getName());
        System.out.println(currentRoom.getDescription());

        while (true) {

            Puzzle puzzle = currentRoom.getPuzzle();
            if (puzzle != null && !currentRoom.isPuzzleSolved()) {
                System.out.println("You have encountered a puzzle!");
                System.out.println(puzzle.getDescription());

                // Check if the puzzle is already solved
                while (puzzle.getRemainingAttempts() > 0) {
                    System.out.println("Remaining attempts: " + puzzle.getRemainingAttempts());
                    System.out.println("Try to solve the puzzle. Enter your answer:");
                    String playerAnswer = input.nextLine();

                    if (puzzle.checkAnswer(playerAnswer)) {
                        System.out.println("Congratulations! You solved the puzzle.");
                        currentRoom.setPuzzleSolved(true); // Mark the puzzle as solved for this room
                        break;
                    } else {
                        System.out.println("Wrong answer. Try again.");
                    }
                }

                if (puzzle.getRemainingAttempts() == 0) {
                    System.out.println("Sorry, you have failed the puzzle. Better luck next time!");
                    puzzle.setRemainingAttempts(puzzle.getAttemptsAllowed());
                    }
                }


            System.out.println("Enter a command (N, S, E, W, explore, quit):"); // Add "explore" as a command
            String command = input.nextLine().toLowerCase();

            // Check if the current room has a puzzle



            switch (command) {
                case "n":
                case "s":
                case "e":
                case "w":
                    currentRoom = move(command);
                    break;
                case "explore":
                    // Display the items in the room when the "explore" command is used
                    ArrayList<Item> itemsInRoom = currentRoom.getItems();
                    if (!itemsInRoom.isEmpty()) {
                        System.out.println("Items in the room:");
                        for (Item item : itemsInRoom) {
                            System.out.println(item);
                        }
                    } else {
                        System.out.println("There are no items in this room.");
                    }
                    System.out.println(itemsInRoom);

                    break;
                case "pick up":
                    if(!currentRoom.getItems().isEmpty()){
                        pickup(currentRoom.getItems());
                        System.out.println(currentRoom);
                    }
                    break;
                case "inventory":
                    player1.displayInventory();
                    break;
                case "drop":
                    if (!player1.getInventory().isEmpty()) {
                        player1.displayInventory();
                        System.out.println("Enter the name of the item you want to drop:");
                        String itemName = input.nextLine();
                        boolean itemFound = false;

                        for (Item item : player1.getInventory()) {
                            if (item.getName().equalsIgnoreCase(itemName)) {
                                player1.removeItemFromInventory(item);
                                currentRoom.getItems().add(item);
                                itemFound = true;
                                System.out.println("You dropped: " + item.getName());
                                break;
                            }
                        }

                        if (!itemFound) {
                            System.out.println("Item not found in your inventory.");
                        }
                    } else {
                        System.out.println("Your inventory is empty.");
                    }
                    break;
                case "inspect":
                    player1.displayInventory();
                    System.out.println("Enter the name of the item you want to inspect (or type 'room' to inspect the current room):");
                    String itemNameToInspect = input.nextLine();
                    boolean itemFound = false;

                    // Check if the item is in the player's inventory
                    for (Item item : player1.getInventory()) {
                        if (item.getName().equalsIgnoreCase(itemNameToInspect)) {
                            System.out.println(item.getDescription());
                            itemFound = true;
                            break;
                        }
                    }

                    // If the item is not in the player's inventory, check if it's in the current room
                    if (!itemFound) {
                        for (Item item : currentRoom.getItems()) {
                            if (item.getName().equalsIgnoreCase(itemNameToInspect)) {
                                System.out.println(item.getDescription());
                                itemFound = true;
                                break;
                            }
                        }
                    }

                    // If the item is not found in both inventory and room, inform the player
                    if (!itemFound) {
                        System.out.println("Item not found.");
                    }
                    break;
                case "quit":
                    System.out.println("Thank you for playing!");
                    input.close();
                    return;

                default:
                    System.out.println("Invalid command. Please try again.");


            }
        }
    }

    private static Player pickup(ArrayList<Item> items) {
        if (!items.isEmpty()) {
            Item itemToPickUp = items.get(0); // Get the first item in the ArrayList
            player1.addItemToInventory(itemToPickUp); // Add the item to the player's inventory
            items.remove(itemToPickUp); // Remove the item from the room's items list
            System.out.println("You picked up: " + itemToPickUp.getName());
        } else {
            System.out.println("There are no items in this room.");
        }
        return player1;
    }


    private static Room move(String direction) {
        Room currentRoom = roomsMap.get(currentRoomId);
        int nextRoomId = 0;
        boolean validMove = false;
        switch (direction) {
            case "n":
                if (currentRoom.getDirections().containsKey("North")) {
                    nextRoomId = currentRoom.getDirections().get("North");
                    validMove = true;
                }
                break;
            case "s":
                if (currentRoom.getDirections().containsKey("South")) {
                    nextRoomId = currentRoom.getDirections().get("South");
                    validMove = true;
                }
                break;
            case "e":
                if (currentRoom.getDirections().containsKey("East")) {
                    nextRoomId = currentRoom.getDirections().get("East");
                    validMove = true;
                }
                break;
            case "w":
                if (currentRoom.getDirections().containsKey("West")) {
                    nextRoomId = currentRoom.getDirections().get("West");
                    validMove = true;
                }
                break;
            default:
                System.out.println("Invalid direction. Please try again.");
        }

        if (validMove) {
            Room nextRoom = roomsMap.get(nextRoomId);
            if (nextRoom != null) {
                if (nextRoom.isVisited()) {
                    System.out.println("You have returned to the " + nextRoom.getName() + ".");
                }

                currentRoomId = nextRoomId;
                currentRoom = nextRoom;
            } else {
                System.out.println("Invalid exit in that direction. Please choose another direction.");
            }
        } else {
            System.out.println("There is no exit in that direction. Please choose another direction.");
        }

        currentRoom.setVisited(true);
        System.out.println("You are now in the " + currentRoom.getName());
        System.out.println(currentRoom.getDescription());


        return currentRoom;
    }

}



