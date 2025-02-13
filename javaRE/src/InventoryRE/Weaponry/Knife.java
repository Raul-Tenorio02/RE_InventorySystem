package InventoryRE.Weaponry;

import InventoryRE.Item;
import InventoryRE.ItemType;

public final class Knife extends Item {

    public Knife(String name, String description, ItemType type) {
        super(name, description, type);
    }

    @Override
    public String toString() {
        return "{" + getName() + ", " + getDescription() + "}";
    }

}
