package InventoryRE.Weaponry;

import InventoryRE.Item;
import InventoryRE.ItemType;

public final class Ammunition extends Item {

    private int quantity;
    AmmoType typeAmmo;

    public Ammunition(String name, String description, ItemType type, AmmoType typeAmmo , int quantity) {
        super(name, description, type);
        this.typeAmmo = typeAmmo;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "{" + getName() + ", " + getDescription()
                + ", Quantity: " + getQuantity() + "}";
    }



}
