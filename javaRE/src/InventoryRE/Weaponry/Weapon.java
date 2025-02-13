package InventoryRE.Weaponry;

import InventoryRE.Item;
import InventoryRE.ItemType;

public class Weapon extends Item implements ReloadInterface, SpecialWeaponsInterface{

    private int magazine, maxCapacity;
    WeaponType typeWeapon;

    public Weapon(String name, String description, ItemType type, int magazine, int maxCapacity) {
        super(name, description, type);
        this.magazine = magazine;
        this.maxCapacity = maxCapacity;
    }

    //TODO: overloading constructor to add weapon type parameter
    public Weapon(String name, String description, ItemType type, int magazine, int maxCapacity, WeaponType typeWeapon) {
        super(name, description, type);
        this.magazine = magazine;
        this.maxCapacity = maxCapacity;
        this.typeWeapon = typeWeapon;
    }

    public int getMagazine() {
        return magazine;
    }

    public void setMagazine(int magazine) {
        this.magazine = magazine;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    @Override
    public String toString() {
        return "{" + getName() + ", " + getDescription()
                + ", Magazine: " + getMagazine() + ", Capacity: " + getMaxCapacity()
                + "}";
    }

    @Override
    public int updateAmmo(int quantity, int ammoNeeded){
        quantity -= ammoNeeded;
        return quantity;
    }
    
    //TODO: reload weapon system
    @Override
    public Weapon reloadWeapon(Ammunition ammo){
        if (this.getType() == ItemType.WEAPON && ammo.getType() == ItemType.AMMUNITION){
            if (getMagazine() < getMaxCapacity()){
                if (this.typeWeapon == WeaponType.HANDGUN && ammo.typeAmmo == AmmoType.HANDGUN_BULLETS){
                    if (ammo.getQuantity() > 0){
                        int ammoNeeded = getMaxCapacity() - getMagazine();
                        this.magazine += ammo.getQuantity();
                        ammo.setQuantity(updateAmmo(ammo.getQuantity(), ammoNeeded));
                        if (this.magazine > this.maxCapacity){
                            setMagazine(this.maxCapacity);
                        } else {
                            setMagazine(this.magazine);
                        }
                    }
                } else if (this.typeWeapon == WeaponType.SHOTGUN && ammo.typeAmmo == AmmoType.SHOTGUN_SHELLS) {
                    if (ammo.getQuantity() > 0){
                        int ammoNeeded = getMaxCapacity() - getMagazine();
                        this.magazine += ammo.getQuantity();
                        ammo.setQuantity(updateAmmo(ammo.getQuantity(), ammoNeeded));
                        if (this.magazine > this.maxCapacity){
                            setMagazine(this.maxCapacity);
                        } else {
                            setMagazine(this.magazine);
                        }
                    }
                } else if (this.typeWeapon == WeaponType.BOWGUN && ammo.typeAmmo == AmmoType.BOWGUN_BOLTS) {
                    if (ammo.getQuantity() > 0){
                        int ammoNeeded = getMaxCapacity() - getMagazine();
                        this.magazine += ammo.getQuantity();
                        ammo.setQuantity(updateAmmo(ammo.getQuantity(), ammoNeeded));
                        if (this.magazine > this.maxCapacity){
                            setMagazine(this.maxCapacity);
                        } else {
                            setMagazine(this.magazine);
                        }
                    }
                    //TODO: system for going through different types of grenade launcher's ammo
                } else if (this.typeWeapon == WeaponType.GRENADE_LAUNCHER && ammo.typeAmmo == AmmoType.GRENADE_ROUNDS){
                    if (ammo.getQuantity() > 0){
                        int ammoNeeded = getMaxCapacity() - getMagazine();
                        this.magazine += ammo.getQuantity();
                        ammo.setQuantity(updateAmmo(ammo.getQuantity(), ammoNeeded));
                        if (this.magazine > this.maxCapacity){
                            setMagazine(this.maxCapacity);
                        } else {
                            setMagazine(this.magazine);
                        }
                    }
                } else if (this.typeWeapon == WeaponType.GRENADE_LAUNCHER && ammo.typeAmmo == AmmoType.ACID_ROUNDS){
                    if (ammo.getQuantity() > 0){
                        int ammoNeeded = getMaxCapacity() - getMagazine();
                        this.magazine += ammo.getQuantity();
                        ammo.setQuantity(updateAmmo(ammo.getQuantity(), ammoNeeded));
                        if (this.magazine > this.maxCapacity){
                            setMagazine(this.maxCapacity);
                        } else {
                            setMagazine(this.magazine);
                        }
                    }
                } else if (this.typeWeapon == WeaponType.GRENADE_LAUNCHER && ammo.typeAmmo == AmmoType.FLAME_ROUNDS){
                    if (ammo.getQuantity() > 0){
                        int ammoNeeded = getMaxCapacity() - getMagazine();
                        this.magazine += ammo.getQuantity();
                        ammo.setQuantity(updateAmmo(ammo.getQuantity(), ammoNeeded));
                        if (this.magazine > this.maxCapacity){
                            setMagazine(this.maxCapacity);
                        } else {
                            setMagazine(this.magazine);
                        }
                    }
                } else if (this.typeWeapon == WeaponType.MAGNUM && ammo.typeAmmo == AmmoType.MAGNUM_BULLETS) {
                    if (ammo.getQuantity() > 0){
                        int ammoNeeded = getMaxCapacity() - getMagazine();
                        this.magazine += ammo.getQuantity();
                        ammo.setQuantity(updateAmmo(ammo.getQuantity(), ammoNeeded));
                        if (this.magazine > this.maxCapacity){
                            setMagazine(this.maxCapacity);
                        } else {
                            setMagazine(this.magazine);
                        }
                    }
                } else if (this.typeWeapon == WeaponType.SPECIAL && ammo.typeAmmo == AmmoType.MACHINEGUN_BULLETS){
                    if (ammo.getQuantity() > 0){
                        int ammoNeeded = getMaxCapacity() - getMagazine();
                        this.magazine += ammo.getQuantity();
                        ammo.setQuantity(updateAmmo(ammo.getQuantity(), ammoNeeded));
                        if (this.magazine > this.maxCapacity){
                            setMagazine(this.maxCapacity);
                        } else {
                            setMagazine(this.magazine);
                        }
                    }
                }
            } else {
                System.out.println("\nWeapon already full.");
            }
        } else {
            System.out.println("Cannot combine these items.");
        }
        return null;
    }

    //TODO: upgrading weapons with parts
    @Override
    public Weapon upgradeWeapon(Parts part){
        if (this.getType() == ItemType.WEAPON && part.getType() == ItemType.PART){
            // in OG Resident Evil 2, only Leon's weapons had custom parts so... fewer validations :)
            if (this.typeWeapon == WeaponType.HANDGUN && part.typePart == PartType.HANDGUN_PARTS){
                //OG RE2 would also full reload weapon's capacity if a part was combined to it, without spending player's ammo
                return new Weapon("H&K VP70 Burst", "\"VP70 with a stock holster. Capable of firing three round auto bursts.\"", ItemType.WEAPON, 18 , 18, WeaponType.SPECIAL);
            } else if (this.typeWeapon == WeaponType.SHOTGUN && part.typePart == PartType.SHOTGUN_PARTS){
                return new Weapon("Remington M1100", "\"M1100 full size semi-automatic. The longer barrel results in more concentrated blasts.\"", ItemType.WEAPON, 7 , 7, WeaponType.SPECIAL);
            } else if (this.typeWeapon == WeaponType.MAGNUM && part.typePart == PartType.MAGNUM_PARTS){
                return new Weapon("Desert Eagle 50A.E Custom", "\"10 inch barrel is put on to D.E.50A.E. It can fire DOT50A.E. rounds more powerfully.\"", ItemType.WEAPON, 8 , 8, WeaponType.SPECIAL);
            }
        } else {
            System.out.println("These items cannot be combined.");
        }
        return null;
    }
}
