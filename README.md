# OBJECT-ORIENTED PROGRAMMING INVENTORY SYSTEM

This project aims to recreate some of the main features present in the classic Resident Evil 2's inventory/items system. It allows you to create items, list them in your inventory, update their characteristics, or merge two items to generate a new one with special attributes. Additionally, you can move items between your inventory and the item box.

# Current Features

1. ArrayList-based data structure.
2. Usage of generics, enums, interfaces, abstract classes, final classes, and both overriding and overloading.
3. Methods for combining items, such as mixing herbs, reloading weapons, or upgrading them.

# Future implementations

1. Improve the use of the ternary operator.
2. Implement Records, HashSet, and Streams.
3. Add a method to reveal the contents of Film-type Files.
4. Add a method for shooting (or swinging) a weapon. (I know it's not strictly an inventory feature, but it will increase the project's complexity as it's directly related to reload and 5. upgrade methods.)
6. Create a more organized way to list files.
7. Develop a frontend.
8. Integrate with a database.
9. Create a web version using RESTful API concepts with Spring.

# Instructions

1. Download the project's ZIP file and open it in your IDE.
2. Predefined inventories for Leon and Claire are already set up:

        Inventory<Object> Leon = new Inventory<>();
        Inventory<Object> Claire = new Inventory<>();

3. You can start calling methods on either variable, Leon or Claire:

        Leon.addToInventory(new --class instance here--(--parameters here--));
        // Remember to check constructors in each class to create specific items correctly.

        Leon.listInventory(); 
        // Lists items created in the inventory.

        Leon.itemBoxIn("item to be stored"); 
        // Stores an item in the item box.

        Leon.itemBoxOut("item to be removed from storage"); 
        // Removes an item from the item box.

        Leon.listItemBox()
        // Work as same as listInventory() but for the item box

        Leon.combineItems("item to be combined", "item you're combining with"); 
        // Calls different methods depending on the items you're combining.
