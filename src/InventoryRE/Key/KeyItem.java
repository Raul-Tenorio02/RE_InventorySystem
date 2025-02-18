package InventoryRE.Key;

import InventoryRE.Item;
import InventoryRE.ItemType;

public final class KeyItem extends Item {

    KeyType typeKey;
    private int uses;

    public KeyItem(String name, String description, ItemType type, KeyType typeKey , int uses) {
        super(name, description, type);
        this.typeKey = typeKey;
        this.uses = uses;
    }

    public int getUses() {
        return uses;
    }

    @Override
    public String toString() {
        if (typeKey == KeyType.INK_RIBBON) {
            return "{" + getName() + ", " + getDescription() + ", " + getUses() + "}";
        }
        return "{" + getName() + ", " + getDescription() + "}";
    }
}
