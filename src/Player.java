import java.util.ArrayList;

public class Player {
    private final ArrayList<Item> inventory;

    public Player() {
        this.inventory = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Player{" +
                "inventory=" + inventory +
                '}';
    }

    public void addItemToInventory(Item item) {
        inventory.add(item);
    }

    public void removeItemFromInventory(Item item) {
        inventory.remove(item);
    }


    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("Items in your inventory:");
            for (Item item : inventory) {
                System.out.println(item.getName());
            }
        }
    }
}

