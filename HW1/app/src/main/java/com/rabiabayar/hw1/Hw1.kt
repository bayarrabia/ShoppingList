package com.rabiabayar.hw1

//superclass for items
open class Item(val name: String, val price: Double)

//subclass representing food items
class Food(name: String, price: Double, val weight: String) : Item(name, price)

//subclass representing clothing items
class Cloth(name: String, price: Double, val type: String) : Item(name, price)

// Shopping list class
class ShoppingList {
    // List to store items
    private val itemList = mutableListOf<Item>()

    //Function to add an item to the shopping list
    fun addItem() {
        println("Enter the item type you want to add (1 for Food, 2 for Cloth):")
        var type = readlnOrNull()?.toIntOrNull()
        while (type !in listOf(1, 2)) {
            println("Invalid number. Enter the item type you want to add (1 for Food, 2 for Cloth):")
            type = readlnOrNull()?.toIntOrNull()
        }
        print("Enter the item name you want to add: ")
        val name = readlnOrNull() ?: ""
        print("Enter the item price you want to add: ")
        var price = readlnOrNull()?.toDoubleOrNull() ?: 0.0
        while (price <= 0) {
            println("Please enter a valid price:")
            price = readlnOrNull()?.toDoubleOrNull() ?: 0.0
        }
        if (type == 1) {
            print("Enter the food weight: ")
            val weight = readlnOrNull() ?: ""
            itemList.add(Food(name, price, weight))
        } else {
            print("Enter the cloth type: ")
            val clothType = readlnOrNull() ?: ""
            itemList.add(Cloth(name, price, clothType))
        }
        println("$name is added successfully!")
    }

    //Function to display items in the shopping list
    fun displayItems() {
        if (itemList.isEmpty()) {
            println("Your shopping list is empty.")
            return
        }
        println("Your shopping list:")
        itemList.forEachIndexed { index, item ->
            println("${index + 1}. ${item.name} ${item.price}\$" +
                    when (item) {
                        is Food -> " ${item.weight}kg"
                        is Cloth -> " ${item.type}"
                        else -> ""
                    })
        }
    }


    //Function to delete an item from the shopping list
    fun deleteItem() {
        if (itemList.isEmpty()) {
            println("Your shopping list is empty. Nothing to delete.")
            return
        }
        println("Enter the item number you want to delete:")
        val itemNumber = readlnOrNull()?.toIntOrNull()
        if (itemNumber !in 1..itemList.size) {
            println("Invalid item number.")
            return
        }
        val deletedItem = itemList.removeAt(itemNumber!! - 1)
        println("${deletedItem.name} is deleted successfully!")
    }
}
//Main function
fun main() {
    val shoppingList = ShoppingList()

    println("***** Welcome to Shopping List App *****")
    var choice: Int

    // Main loop for the program
    do {
        println("\nMake your choice (1-2-3-4):\n" +
                "1. Add Item\n" +
                "2. Display Item\n" +
                "3. Delete Item\n" +
                "4. Exit")
        print("Your choice is: ")
        choice = readlnOrNull()?.toIntOrNull() ?: 0

        // Perform actions based on user's choice
        when (choice) {
            1 -> shoppingList.addItem()
            2 -> shoppingList.displayItems()
            3 -> shoppingList.deleteItem()
            4 -> println("Exiting...")
            else -> println("Invalid choice. Please enter a number between 1 and 4.")
        }
    } while (choice != 4)
}
