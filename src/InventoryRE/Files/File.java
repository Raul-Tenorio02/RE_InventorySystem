package InventoryRE.Files;

import InventoryRE.Item;
import InventoryRE.ItemType;

public final class File extends Item {

    public File(String name, String description, ItemType type) {
        super(name, description, type);
    }

    @Override
    public String toString() {
        return "\n{" + getName() + ", " + getType() + "}";
    }

}
