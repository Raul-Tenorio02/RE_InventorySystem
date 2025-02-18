package InventoryRE;

import InventoryRE.Files.File;
import InventoryRE.Key.KeyItem;
import InventoryRE.Key.KeyType;
import InventoryRE.Weaponry.Ammunition.AmmoType;
import InventoryRE.Weaponry.Ammunition.Ammunition;
import InventoryRE.Weaponry.Weapons.Weapon;
import InventoryRE.Weaponry.Weapons.WeaponType;
import InventoryRE.Weaponry.Knife.Knife;
import InventoryRE.Weaponry.WeaponParts.PartType;
import InventoryRE.Weaponry.WeaponParts.Parts;

public class Main {

    /*                          OG Resident Evil 2's Inventory

        This project's purpose is to represent Classic Resident Evil 2's inventory main features
        by using Object-Oriented Programing, Data Structure, some Java build-in features etc.
        More information on README.

                                            Enjoy :)

        author: Raul-Tenorio02 on GitHub
    */

    public static void main(String[] args) {

        Inventory<Object> Leon = new Inventory<>();
        Inventory<Object> Claire = new Inventory<>();



        Leon.addToFiles(new File("Police Memorandum", """
                
                                                                        POLICE MEMORANDUM
                
                8/23/1998
                This letter is just to inform everyone about the recent movement of equipment that has happened during the precinct's rearrangement.
                
                The safe with four digit lock has been moved from S.T.A.R.S. office on the second floor, to the eastern office on the first floor.
                
                                                                              "2236"
                
                                                                                                                        Raccoon Police Liaison Dept.
                
                
                """, ItemType.FILE));

        Leon.listFiles();
        Leon.readFile("Police Memorandum");

        Leon.addToInventory(new Weapon("H&K VP70", "\"Manufactured by H&K, Germany. It uses 9mm parabellum rounds.\"", ItemType.WEAPON, WeaponType.HANDGUN, 18, 18));
        Leon.addToInventory(new Ammunition("Hand Gun Bullets", "\"9x19 parabellum rounds that can be used for either the H&K VP70 or Browning HP.\"", ItemType.AMMUNITION, AmmoType.HANDGUN_BULLETS, 15));
        Leon.addToInventory(new Parts("Hand Gun Parts", "\"They look like parts for a gun.\"", ItemType.PART, PartType.HANDGUN_PARTS));
        Leon.addToInventory(new KeyItem("Ink Ribbon", "\"I can type in my progress with this.\"", ItemType.KEY, KeyType.INK_RIBBON, 3));
        Leon.addToInventory(new Knife("Knife", "\"A combat knife. It could come in handy...\"", ItemType.WEAPON, WeaponType.KNIFE));
        Leon.addToInventory(new Weapon("Rocket Launcher", "\"A rocket launcher. One shot from this should kill any enemy.\"", ItemType.SPECIAL, WeaponType.INFINITE_WEAPON, 1, 1));
        Leon.listInventory();

        Leon.itemBoxIn("Ink Ribbon");
        Leon.listItemBox();

        Leon.useWeapon("H&K VP70", 5);
        Leon.listInventory();
        Leon.combineItems("H&K VP70", "Hand Gun Bullets");
        Leon.listInventory();
        Leon.combineItems("H&K VP70", "Hand Gun Parts");
        Leon.listInventory();
        Leon.useWeapon("H&K VP70 Burst", 1);
        System.out.println("\nGetting item from the item box. Please wait...");
        Leon.itemBoxOut("Ink Ribbon");
        Leon.listInventory();
        Leon.useWeapon("Knife", 100);
        Leon.useWeapon("Rocket Launcher", 100);
    }
}