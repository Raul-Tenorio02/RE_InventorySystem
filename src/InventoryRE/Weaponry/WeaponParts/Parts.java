package InventoryRE.Weaponry.WeaponParts;

import InventoryRE.Item;
import InventoryRE.ItemType;

public final class Parts extends Item {

    PartType typePart;

    public Parts(String name, String description, ItemType type, PartType typePart) {
        super(name, description, type);
        this.typePart = typePart;
    }

    public PartType getTypePart() {
        return typePart;
    }

    public void setTypePart(PartType typePart) {
        this.typePart = typePart;
    }

    @Override
    public String toString() {
        return "{" + getName() + ", " + getDescription() + "}";
    }

}
