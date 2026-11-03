# Inventory Management System

## Case Study 4 — Core Java and OOPS

A console-based Java application to add inventory items, remove quantities, and check available quantities.

## Project structure

```text
InventoryManagementSystem/
├── src/
│   ├── Inventory.java
│   └── InventoryManagement.java
└── README.md
```

## Features

- Add an item to inventory.
- Increase the quantity when an item already exists.
- Remove a specified quantity from inventory.
- Prevent removal when the requested quantity exceeds the available quantity.
- Remove an item from the map when its quantity becomes zero.
- Check the available quantity of an item.
- Continue showing the menu until the user selects Exit.
- Validate empty item names, invalid numbers, and non-positive quantities.
- Store data in memory only; data is not persistent.

## OOPS principles used

- **Encapsulation:** The inventory map is private and can only be accessed through public methods.
- **Abstraction:** The `Inventory` class exposes operations such as `addItem`, `removeItem`, and `checkQuantity`.
- **Modularity:** `Inventory` contains inventory logic, while `InventoryManagement` handles console input and output.

## How to run

### Using IntelliJ IDEA

1. Extract the ZIP file.
2. Open the `InventoryManagementSystem` folder in IntelliJ IDEA.
3. Open `src/InventoryManagement.java`.
4. Run the `main()` method.

### Using the command line

Run these commands from the project folder:

```bash
javac -d out src/Inventory.java src/InventoryManagement.java
java -cp out InventoryManagement
```

Requires JDK 8 or later.
