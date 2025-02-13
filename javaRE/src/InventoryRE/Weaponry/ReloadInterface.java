package InventoryRE.Weaponry;

public interface ReloadInterface {

    Weapon reloadWeapon(Ammunition ammo);
    int updateAmmo(int quantity, int ammoNeeded);

}
