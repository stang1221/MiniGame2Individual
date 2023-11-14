Welcome to the Map of The House!

The MOTH is simple Java text-based adventure game where players can explore different rooms and navigate through the game world.
The program read in a text file "room.txt" and constructs the map based on the input.

First -> Open the file "room.txt".

You will notice that each room's details are seperated by '----'
Data for Each Room: For each room, the following information is provided:

<Id> 
Id is the identification integer representing the room's unique identifier.

<name> 
A string representing the name or title of the room, item, or puzzle.

<description>
A string containing a detailed description of the room, item, or puzzle.

<exits> 
A list of exits available from the room, along with the corresponding room IDs. Exits are represented in the format Direction:RoomID, separated by commas.

<visited>
A boolean value (true or false) indicating whether the room has been visited by the player.

<isPuzzleSolved>
A boolean that indicates whether a puzzle has been solved.

-----
CLASSES AND THEIR FUNCTIONS:

Game Class
The Game class represents the main logic of your text-based adventure game. It contains methods for loading rooms from a file, managing player actions, handling puzzles, and controlling the flow of the game.

Room Class
The Room class encapsulates the properties of a room in the game. Each room has a unique ID, a name, a description, a list of directions leading to other rooms, a list of items present in the room, and an optional puzzle. Rooms can be marked as visited and puzzles can be marked as solved.

Player Class
The Player class represents the player in the game. It manages the player's inventory, allowing items to be added and removed. The class also provides methods to check if the player has a specific item and displays the items in the player's inventory.

Item Class
The Item class represents an item that can be found in a room or carried by the player. Each item has a name and a description. Items can be picked up, dropped, and inspected by the player.

Puzzle Class
The Puzzle class represents a puzzle that the player can encounter in a room. Puzzles have a name, a description, an answer, and a limited number of attempts allowed to solve them. Players can attempt to solve puzzles by entering answers, and the puzzle keeps track of remaining attempts.

File Structure
Your game reads room data from a file named room.txt. This file contains information about each room, including its ID, name, description, directions, items, and puzzles. The game loads this data to create the game environment.


The default map is set in the format of:

[5] - [2] 
|      |
[6] - [1] - [4]
|
[3]

[1] - Living Room
[2] - Kitchen:
      Items: Knife
      Puzzles: Backward Puzzle
[3] - Bedroom
[4] - Bathroom
[5] - Garden
      Items: Trowel
[6] - Study Room
      Items: Book



HOW TO PLAY
User Manual--
List of Commands to follow:
Enter (N,S,W,E or n,s,w,e) to navigate throughout the rooms.
N or n..........moves to the north adjacent room
S or s..........moves to the south adjacent room
W or e...........moves to the east adjacent room
W or w ..........moves to the adjacent west room
E
- explore: provides a description of objects that are in the room
- pick up: allows player to pickup the first item within that room
- inspect: displays a description of the object that is inspected
- drop: allows player to drop item they have from their inventory 
- inventory: shows the player what items they have on them
