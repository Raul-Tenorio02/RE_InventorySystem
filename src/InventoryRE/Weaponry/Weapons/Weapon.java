package InventoryRE.Weaponry.Weapons;

import InventoryRE.Item;
import InventoryRE.ItemType;
import InventoryRE.Weaponry.Ammunition.AmmoType;
import InventoryRE.Weaponry.Ammunition.Ammunition;
import InventoryRE.Weaponry.WeaponParts.PartType;
import InventoryRE.Weaponry.WeaponParts.Parts;
import InventoryRE.Weaponry.WeaponParts.UpgradeWeaponsInterface;

public class Weapon extends Item implements ReloadInterface, UpgradeWeaponsInterface, FireInterface {

    private int magazine, maxCapacity, fireRate;
    WeaponType typeWeapon;
    AmmoType loadedAmmo;

    public Weapon(String name, String description, ItemType type, WeaponType typeWeapon, int magazine, int maxCapacity) {
        super(name, description, type);
        this.magazine = magazine;
        this.maxCapacity = maxCapacity;
        this.typeWeapon = typeWeapon;
    }

    // overloading constructor to implement grenade launcher's specific reloading/switch ammo system
    public Weapon(String name, String description, ItemType type, WeaponType typeWeapon, int magazine, int maxCapacity, int fireRate, AmmoType loadedAmmo) {
        this(name, description, type, typeWeapon, magazine, maxCapacity);
        this.loadedAmmo = loadedAmmo;
    }

    // overloading constructor to add fire rate attribute
    public Weapon(String name, String description, ItemType type, WeaponType typeWeapon, int magazine, int maxCapacity, int fireRate) {
        super(name, description, type);
        this.magazine = magazine;
        this.maxCapacity = maxCapacity;
        this.typeWeapon = typeWeapon;
        this.fireRate = fireRate;
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

    public WeaponType getTypeWeapon(){
        return typeWeapon;
    }

    @Override
    public String toString() {
        if (this.typeWeapon == WeaponType.INFINITE_WEAPON){
            return "{" + getName() + ", " + getDescription() + ", Magazine: ∞" + "}";
        }
        return "{" + getName() + ", " + getDescription()
                + ", Magazine: " + getMagazine() + ", Capacity: " + getMaxCapacity()
                + "}";
    }

    @Override
    public int updateAmmoLeft(int quantity, int ammoNeeded){
        quantity -= ammoNeeded;
        return quantity;
    }

    @Override
    public ReloadResult reloadWeapon(Ammunition ammo){
        Ammunition returnedAmmo = null;

        if (this.getType() == ItemType.WEAPON || this.getType() == ItemType.SPECIAL){
            if (this.typeWeapon == WeaponType.GRENADE_LAUNCHER && isCompatibleAmmo(ammo)) {
                if (loadedAmmo != ammo.getAmmoType()) {
                    returnedAmmo = unloadAmmo();
                    loadedAmmo = ammo.getAmmoType();
                }
                int ammoNeeded = getMaxCapacity() - getMagazine();
                this.magazine += Math.min(ammo.getQuantity(), ammoNeeded);
                ammo.setQuantity(updateAmmoLeft(ammo.getQuantity(), ammoNeeded));
            } else if (isCompatibleAmmo(ammo)) {
                    int ammoNeeded = getMaxCapacity() - getMagazine();
                    this.magazine += Math.min(ammo.getQuantity(), ammoNeeded);
                    ammo.setQuantity(updateAmmoLeft(ammo.getQuantity(), ammoNeeded));
                } else {
                    System.out.println("\nThere is no need to combine these");
                }
            } else {
            System.out.println("\nCannot combine these items.");
            }
        return new ReloadResult(this, returnedAmmo);
    }

    @Override
    public Weapon upgradeWeapon(Parts part){
        if (this.getType() == ItemType.WEAPON && part.getType() == ItemType.PART){
            // in OG Resident Evil 2, only Leon's weapons had custom parts so... fewer validations :)
            if (this.typeWeapon == WeaponType.HANDGUN && part.getTypePart() == PartType.HANDGUN_PARTS){
                //OG RE2 would also full reload weapon's capacity without spending player's ammo if a part was combined to it
                return new Weapon("H&K VP70 Burst", "\"VP70 with a stock holster. Capable of firing three round auto bursts.\"", ItemType.SPECIAL, WeaponType.HANDGUN , 18, 18, 3);
            } else if (this.typeWeapon == WeaponType.SHOTGUN && part.getTypePart() == PartType.SHOTGUN_PARTS){
                return new Weapon("Remington M1100", "\"M1100 full size semi-automatic. The longer barrel results in more concentrated blasts.\"", ItemType.SPECIAL, WeaponType.SHOTGUN, 7 , 7);
            } else if (this.typeWeapon == WeaponType.MAGNUM && part.getTypePart() == PartType.MAGNUM_PARTS){
                return new Weapon("Desert Eagle 50A.E Custom", "\"10 inch barrel is put on to D.E.50A.E. It can fire DOT50A.E. rounds more powerfully.\"", ItemType.SPECIAL, WeaponType.MAGNUM, 8 , 8);
            }
        } else {
            System.out.println("These items cannot be combined.");
        }
        return null;
    }

    private boolean isCompatibleAmmo(Ammunition ammo) {
        return (this.typeWeapon == WeaponType.HANDGUN && ammo.getAmmoType() == AmmoType.HANDGUN_BULLETS) ||
                (this.typeWeapon == WeaponType.SHOTGUN && ammo.getAmmoType() == AmmoType.SHOTGUN_SHELLS) ||
                (this.typeWeapon == WeaponType.BOWGUN && ammo.getAmmoType() == AmmoType.BOWGUN_BOLTS) ||
                (this.typeWeapon == WeaponType.GRENADE_LAUNCHER && ammo.getAmmoType() == AmmoType.GRENADE_ROUNDS
                        || this.typeWeapon == WeaponType.GRENADE_LAUNCHER && ammo.getAmmoType() == AmmoType.ACID_ROUNDS
                        || this.typeWeapon == WeaponType.GRENADE_LAUNCHER && ammo.getAmmoType() == AmmoType.FLAME_ROUNDS ) ||
                (this.typeWeapon == WeaponType.MAGNUM && ammo.getAmmoType() == AmmoType.MAGNUM_BULLETS) ||
                (this.typeWeapon == WeaponType.MACHINE_GUN && ammo.getAmmoType() == AmmoType.MACHINEGUN_BULLETS);
    }

    @Override
    public Ammunition unloadAmmo(){
        if (loadedAmmo != null){
            Ammunition returnedAmmo;
            if (loadedAmmo == AmmoType.GRENADE_ROUNDS) {
                returnedAmmo = new Ammunition("Grenade Rounds", "\"Grenade bearing Rounds. For the M79 Grenade Launcher.\"", ItemType.AMMUNITION, loadedAmmo, this.magazine);
                this.magazine = 0;
                return returnedAmmo;
            } else if (loadedAmmo == AmmoType.ACID_ROUNDS){
                returnedAmmo = new Ammunition("Acid Rounds", "\"Grenade Acid Rounds. For the M79 Grenade Launcher.\"", ItemType.AMMUNITION, loadedAmmo, this.magazine);
                this.magazine = 0;
                return returnedAmmo;
            } else {
                returnedAmmo = new Ammunition("Flame Rounds", "\"Grenade Flame Rounds. For the M79 Grenade Launcher.\"", ItemType.AMMUNITION, loadedAmmo, this.magazine);
                this.magazine = 0;
                return returnedAmmo;
            }
        }
        return null;
    }

    @Override
    public int fireCount(String name, int count){
        if (fireRate == 0){ // removing need of defining fire rate for common weapons
            fireRate = 1;
        } else if (this.typeWeapon == WeaponType.BOWGUN){
            fireRate = 3; // in OG RE2, the bowgun shoots 3 bolts in a row
        }

        if (this.typeWeapon == WeaponType.INFINITE_WEAPON) {
            System.out.println("\nYou've shot an enemy with your \"" + getName() + "\" " + count + "x!");
            setMagazine(this.fireRate); // never really updates magazine's ammo when it is supposed to be infinite ;) kind of useless now, but maybe it can help with defining the damage of each projectile
        } else {
            count *= this.fireRate;
            if (count > magazine) {
                count = magazine;
                setMagazine(getMagazine() - count);
                System.out.println("\nYou've shot an enemy with your \"" + getName() + "\" " + count + "x! Now your weapon is empty");
            } else {
                setMagazine(getMagazine() - count);
                System.out.println("\nYou've shot an enemy with your \"" + getName() + "\" " + count + "x!");
            }
        }
        return magazine;
    }
}