package InventoryRE;

import InventoryRE.Files.File;
import InventoryRE.Recovery.RecoveryItem;
import InventoryRE.Weaponry.Ammunition;
import InventoryRE.Weaponry.Parts;
import InventoryRE.Weaponry.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Inventory<T> implements InventoryInterface{

    private List<T> inventoryItems;
    private List<T> itemBox;

    public Inventory(){
        this.inventoryItems = new ArrayList<>();
        this.itemBox = new ArrayList<>();
    }

    public void addToInventory(T item) {
        inventoryItems.add(item);
    }

    /*
    private because technically you should be able to remove an item
    from your inventory only by using it or by sending it to the item box
    */
    private void removeFromInventory(T item){
        inventoryItems.remove(item);
    }

    public void listInventory(){
        System.out.println("\n-------------------------------------INVENTORY---------------------------------------\n");
        for (T item : inventoryItems) {
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
        System.out.println("\n--------------------------------------ITEM BOX---------------------------------------\n");
        for (T item : itemBox){
                System.out.println(item);
        }
    }

    public T getItemByName (String name, boolean searchItemBox) {
        List<T> targetList = searchItemBox ? itemBox : inventoryItems;
        for (T item : targetList) {
            if (item instanceof Item && ((Item) item).getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    //TODO: inventory combine action system
    @Override
    public void combineItems(String name1, String name2) {
        T item1 = getItemByName(name1, false);
        T item2 = getItemByName(name2, false);

        //TODO: triggers mix herb method in RecoveryItem
        if (item1 instanceof RecoveryItem && item2 instanceof RecoveryItem){
            RecoveryItem herb1 = (RecoveryItem) item1;
            RecoveryItem herb2 = (RecoveryItem) item2;

            RecoveryItem combinedHerb = herb1.mixHerb(herb2);
            if (combinedHerb != null){
                this.removeFromInventory(item1);
                this.removeFromInventory(item2);
                addToInventory((T) combinedHerb);
                System.out.println("\nHerbs combined successfully!\n");
            } else {
                System.out.println("\nThere is no need to combine these.\n");
            }
            //TODO: triggers reload weapon method in Weapon
        } else if (item1 instanceof Weapon && item2 instanceof Ammunition || item1 instanceof Ammunition && item2 instanceof Weapon){
            Weapon weapon = (Weapon) item1;
            Ammunition ammo = (Ammunition) item2;

            Weapon reloadedWeapon = weapon.reloadWeapon(ammo);
            if (reloadedWeapon == null){
                if (ammo.getQuantity() <= 0){
                    this.removeFromInventory((T) ammo);
                    System.out.println("\nWeapon reloaded sucessfully!\n");
                } else {
                    System.out.println("\nWeapon reloaded sucessfully!\n");
                }
            } else {
                System.out.println("\nThere is no need to combine these.\n");
            }
        } else if (item1 instanceof Weapon && item2 instanceof Parts || item1 instanceof Parts && item2 instanceof Weapon) {
            Weapon weapon = (Weapon) item1;
            Parts part = (Parts) item2;

            Weapon customWeapon = weapon.upgradeWeapon(part);
            if (customWeapon != null){
                this.removeFromInventory((T) part);
                this.removeFromInventory((T) weapon);
                addToInventory((T) customWeapon);
                System.out.println("Weapon customized sucessfully!");
            } else {
                System.out.println("There is no need to combine these.");
            }
        } else {
            System.out.println("\nThere is no need to combine these.\n");
        }
    }

    @Override
    public void itemBoxIn(String name){
        T item = getItemByName(name, false);
        if (item != null) {
            addToItemBox(item);
            removeFromInventory(item);
        } else {
            System.out.println("Erro: Item não encontrado.");
        }
    }

    @Override
    public void itemBoxOut(String name){
        T item = getItemByName(name, true);
        if(item != null) {
            addToInventory(item);
            removeFromItemBox(item);
        } else {
            System.out.println("Erro: Item não encontrado.");
        }
    }

    @Override
    public void readFile(String name){
        T item = getItemByName(name, false);
        if (item != null){
            if (item instanceof File){
                File file = (File) item;
                System.out.println(file.getDescription());
            }
        } else {
            System.out.println("Error: file not found.");
        }
    }
}
