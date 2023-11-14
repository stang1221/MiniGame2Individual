public class Item {
    private final String name;
    private final String description;



    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name ='" + name + '\'' +
                ", description ='" + description;
    }

    public String getDescription() {
        return description;
    }

}

