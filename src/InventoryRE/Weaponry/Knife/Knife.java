package InventoryRE.Weaponry.Knife;

import InventoryRE.Item;
import InventoryRE.ItemType;
import InventoryRE.Weaponry.Weapons.WeaponType;

public final class Knife extends Item implements KnifeInterface{

    WeaponType typeWeapon;

    public Knife(String name, String description, ItemType type, WeaponType typeWeapon) {
        super(name, description, type);
        this.typeWeapon = typeWeapon;
    }

    @Override
    public String toString() {
        return "{" + getName() + ", " + getDescription() + "}";
    }

    @Override
    public void swingCount(String name, int count){
        System.out.println("\nYou've stabbed an enemy " + count + "x!");
    }
}
