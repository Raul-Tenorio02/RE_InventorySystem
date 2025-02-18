package InventoryRE;

public abstract class Item {

    // i'm still getting used on commenting my projects, sorry for that :v
    private String name, description;
    ItemType type;

    public Item(String name, String description, ItemType type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ItemType getType() {
        return type;
    }


}
