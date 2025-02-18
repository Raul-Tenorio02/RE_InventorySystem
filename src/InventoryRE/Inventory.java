package InventoryRE;

import InventoryRE.Files.File;
import InventoryRE.Recovery.RecoveryItem;
import InventoryRE.Weaponry.Ammunition.Ammunition;
import InventoryRE.Weaponry.Weapons.ReloadResult;
import InventoryRE.Weaponry.Weapons.Weapon;
import InventoryRE.Weaponry.Knife.Knife;
import InventoryRE.Weaponry.WeaponParts.Parts;
import InventoryRE.Weaponry.Weapons.WeaponType;

import java.util.ArrayList;
import java.util.List;

public class Inventory<T> implements InventoryInterface{

    private List<T> equipments;
    private List<T> itemBox;
    private List<T> archive;

    public Inventory(){
        this.equipments = new ArrayList<>();
        this.itemBox = new ArrayList<>();
        this.archive = new ArrayList<>();
    }

    public void addToInventory(T item) {
        equipments.add(item);
    }

    /*
    private because technically you should be able to remove an item
    from your inventory only by using it or by sending it to the item box
    */
    private void removeFromInventory(T item){
        equipments.remove(item);
    }

    public void listInventory(){
        System.out.println("\n---------------------------------------------------------INVENTORY--------------------------------------------------------\n");
        for (T item : equipments) {
                System.out.println(item);
        }
    }

    public void addToFiles(T item) {
        archive.add(item);
    }

    public void listFiles(){
        System.out.println("\n-----------------------------------------------------------FILES----------------------------------------------------------\n");
        for (T item : archive) {
            System.out.println(item);
        }
    }

    private void addToItemBox(T item){
        itemBox.add(item);
    }

    private void removeFromItemBox(T item){
        itemBox.remove(item);
    }

    public void listItemBox(){
        System.out.println("\n----------------------------------------------------------ITEM BOX--------------------------------------------------------\n");
        for (T item : itemBox){
                System.out.println(item);
        }
    }

    public T getItemByName (String name, boolean searchItemBox) {
        List<T> targetList = searchItemBox ? itemBox : equipments;
        for (T item : targetList) {
            if (item instanceof Item && ((Item) item).getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public T getFileByName (String name) {
        for (T file : archive) {
            if (file instanceof Item && ((Item) file).getName().equalsIgnoreCase(name)){
                return file;
            }
        }
        return null;
    }

    @Override
    public void combineItems(String name1, String name2) {
        T item1 = getItemByName(name1, false);
        T item2 = getItemByName(name2, false);

        if (item1 instanceof RecoveryItem && item2 instanceof RecoveryItem){
            RecoveryItem herb1 = (RecoveryItem) item1;
            RecoveryItem herb2 = (RecoveryItem) item2;
            RecoveryItem combinedHerb = herb1.mixHerb(herb2);
            if (combinedHerb != null){
                this.removeFromInventory(item1);
                this.removeFromInventory(item2);
                addToInventory((T) combinedHerb);
                System.out.println("\nHerbs combined successfully!");
            } else {
                System.out.println("\nThere is no need to combine these.");
            }
        } else if (item1 instanceof Weapon && item2 instanceof Ammunition || item1 instanceof Ammunition && item2 instanceof Weapon){
            Weapon weapon = (Weapon) item1;
            Ammunition ammo = (Ammunition) item2;
            ReloadResult reloadResult = weapon.reloadWeapon(ammo);
            Ammunition returnedAmmo = reloadResult.getReturnedAmmo();
            if (returnedAmmo != null) {
                boolean ammoAdded = false;
                for (T item : equipments) {
                    if (item instanceof Ammunition) {
                        Ammunition inventoryAmmo = (Ammunition) item;
                        if (inventoryAmmo.getAmmoType() == returnedAmmo.getAmmoType()) {
                            inventoryAmmo.setQuantity(inventoryAmmo.getQuantity() + returnedAmmo.getQuantity());
                            ammoAdded = true;
                            break;
                        }
                    }
                }
                if(!ammoAdded){
                    addToInventory((T) returnedAmmo);
                }
            }
            if(weapon.getTypeWeapon() != WeaponType.INFINITE_WEAPON) {
                if (ammo.getQuantity() <= 0) {
                    this.removeFromInventory((T) ammo);
                    System.out.println("\nWeapon reloaded sucessfully!");
                } else {
                    System.out.println("\nWeapon reloaded sucessfully!");
                }
            }
        } else if (item1 instanceof Weapon && item2 instanceof Parts || item1 instanceof Parts && item2 instanceof Weapon) {
            Weapon weapon = (Weapon) item1;
            Parts part = (Parts) item2;

            Weapon customWeapon = weapon.upgradeWeapon(part);
            if (customWeapon != null){
                this.removeFromInventory((T) part);
                this.removeFromInventory((T) weapon);
                addToInventory((T) customWeapon);
                System.out.println("\nWeapon customized sucessfully!");
            } else {
                System.out.println("\nThere is no need to combine these.");
            }
        } else {
            System.out.println("\nCannot combine these items.");
        }
    }

    @Override
    public void itemBoxIn(String name){
        T item = getItemByName(name, false);
        if (item != null) {
            addToItemBox(item);
            removeFromInventory(item);
        } else {
            System.out.println("\nError: item not found.");
        }
    }

    @Override
    public void itemBoxOut(String name){
        T item = getItemByName(name, true);
        if(item != null) {
            addToInventory(item);
            removeFromItemBox(item);
        } else {
            System.out.println("\nError: item not found.");
        }
    }

    @Override
    public void readFile(String name){
        T file = getFileByName(name);
        if (file != null){
            if (file instanceof File fileToRead){
                System.out.println(fileToRead.getDescription());
            }
        } else {
            System.out.println("\nError: file not found.");
        }
    }

    @Override
    public void useWeapon(String nameWeapon, int count){
        if (count == 0){
            count = 1;
        }
        T item = getItemByName(nameWeapon, false);
        if (item instanceof Weapon) {
            Weapon weaponInUse = (Weapon) item;
            if (item != null){
                weaponInUse.fireCount(nameWeapon, count);
            }
        } else if (item instanceof Knife){
            if (item != null){
                Knife knifeInUse = (Knife) item;
                knifeInUse.swingCount(nameWeapon, count);
            }
        }

    }
}