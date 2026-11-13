import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final Map<String, Integer> items = new HashMap<>();
    public void addItem(String itemName, int quantity) {
        String name = normalizeName(itemName);
        if (name == null) {
            System.out.println("Item name cannot be empty.");
            return;
        }
        if (quantity <= 0) {
            System.out.println("Quantity must be greater than zero.");
            return;
        }
        int currentQuantity = items.getOrDefault(name, 0);
        if (quantity > Integer.MAX_VALUE - currentQuantity) {
            System.out.println("Quantity is too large to add.");
            return;
        }
        items.put(name, currentQuantity + quantity);
        System.out.println(quantity + " " + name + "(s) added successfully.");
    }
    public void removeItem(String itemName, int quantity) {
        String name = normalizeName(itemName);

        if (name == null) {
            System.out.println("Item name cannot be empty.");
            return;
        }
        if (quantity <= 0) {
            System.out.println("Quantity must be greater than zero.");
            return;
        }
        Integer currentQuantity = items.get(name);
        if (currentQuantity == null) {
            System.out.println("Item not found in inventory.");
            return;
        }

        if (quantity > currentQuantity) {
            System.out.println("Insufficient quantity available.");
            return;
        }

        int remainingQuantity = currentQuantity - quantity;

        if (remainingQuantity == 0) {
            items.remove(name);
        } else {
            items.put(name, remainingQuantity);
        }

        System.out.println(quantity + " " + name + "(s) removed successfully.");
    }
    public void checkQuantity(String itemName) {
        String name = normalizeName(itemName);

        if (name == null) {
            System.out.println("Item name cannot be empty.");
            return;
        }
        Integer quantity = items.get(name);
        if (quantity == null) {
            System.out.println("Item not found in inventory.");
        } else {
            System.out.println("Available quantity of " + name + ": " + quantity);
        }
    }
    private String normalizeName(String itemName) {
        if (itemName == null || itemName.trim().isEmpty()) {
            return null;
        }
        return itemName.trim();
    }
}
