import java.util.Scanner;

public class InventoryManagement {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inventory inventory = new Inventory();

        int choice;

        do {
            displayMenu();
            choice = readInteger(sc, "Enter your choice: ");

            switch (choice) {
                case 1:
                    System.out.print("Enter item name: ");
                    String addName = sc.nextLine();

                    int addQuantity = readInteger(sc, "Enter quantity: ");
                    inventory.addItem(addName, addQuantity);
                    break;

                case 2:
                    System.out.print("Enter item name: ");
                    String removeName = sc.nextLine();

                    int removeQuantity = readInteger(sc, "Enter quantity to remove: ");
                    inventory.removeItem(removeName, removeQuantity);
                    break;

                case 3:
                    System.out.print("Enter item name: ");
                    String checkName = sc.nextLine();

                    inventory.checkQuantity(checkName);
                    break;

                case 4:
                    System.out.println("Exiting Inventory Management System...");
                    break;

                default:
                    System.out.println("Invalid choice. Please select 1, 2, 3, or 4.");
            }

        } while (choice != 4);

        sc.close();
    }
    private static void displayMenu() {
        System.out.println("\n----- INVENTORY MANAGEMENT SYSTEM -----");
        System.out.println("1. Add Item");
        System.out.println("2. Remove Item");
        System.out.println("3. Check Quantity");
        System.out.println("4. Exit");
    }
    private static int readInteger(Scanner sc, String message) {
        while (true) {
            System.out.print(message);

            if (sc.hasNextInt()) {
                int value = sc.nextInt();
                sc.nextLine();
                return value;
            }
            System.out.println("Invalid input. Please enter a whole number.");
            sc.nextLine();
        }
    }
}
