import java.util.ArrayList;
import java.util.HashMap;
/*
 * Class: Room
 * Purpose: Sets up two constructors for creating Room objects. This class
 * will be used to create room objects from the scanner that reads the room.txt file.
 * There is one default constructor with example data in it, and one multi parameter
 * constructor used for inputting room information. Each variable has a getter and setter, and
 * the ToString has been overridden to display some that looks like this:
 *
 * ID= 1, name= test
 * Description:
 * test
 * exits:
 * You can go [WEST, EAST]
 *
 */

public class Room {

    //Integer for rooms Id number
    private final int Id;
    //String for rooms name
    private final String name;
    //String for rooms description (NOTE: may have to make this a CharArray at some point, idk)
    private final String description;
    //ArrayList for all the directions that a room can have
    private final HashMap<String, Integer> directions;
    //Marking for visited
    private boolean visited;

    private final ArrayList<Item> items; // Add this field for storing items in the room

    private final Puzzle puzzle;

    private boolean puzzleSolved;



    //Default Constructor
    public Room() {
        this.Id = 0;
        this.name = "Example Room";
        this.description = "An example room for a default constructor";
        this.directions = new HashMap<>();
        this.visited = false;
        this.items = new ArrayList<>(); // Initialize the items ArrayList
        this.puzzle = null; // Set the puzzle to null for rooms without a puzzle
    }

    //Multi-Parameter Constructor
    public Room(int Id, String name, String description, HashMap<String, Integer> directions,ArrayList<Item> items, Puzzle puzzle) {
        this.Id = Id;
        this.name = name;
        this.description = description;
        this.directions = directions;
        this.items = items;
        this.puzzle = puzzle;
        this.puzzleSolved = false; // Initialize puzzleSolved as false when the room is created.
    }

    //Getters

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public HashMap<String,Integer> getDirections() {
        return directions;
    }

    public Puzzle getPuzzle() {return puzzle; }



    //Setters

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {

        this.visited = visited;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public boolean isPuzzleSolved() {
        return puzzleSolved;
    }

    public void setPuzzleSolved(boolean puzzleSolved) {
        this.puzzleSolved = puzzleSolved;
    }


    @Override
    public String toString() {
        return "Room{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", directions=" + directions +
                ", visited=" + visited +
                ", items=" + items +
                ", puzzle=" + puzzle +
                '}';
    }
}



