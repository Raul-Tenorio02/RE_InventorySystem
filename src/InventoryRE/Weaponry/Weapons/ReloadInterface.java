package InventoryRE.Weaponry.Weapons;

import InventoryRE.Weaponry.Ammunition.Ammunition;

public interface ReloadInterface {

    ReloadResult reloadWeapon(Ammunition ammo);
    int updateAmmoLeft(int quantity, int ammoNeeded);
    // specific for grenade launchers
    Ammunition unloadAmmo();


}
