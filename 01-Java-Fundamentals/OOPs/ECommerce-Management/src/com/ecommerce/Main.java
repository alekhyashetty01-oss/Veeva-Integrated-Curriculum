package com.ecommerce;
import java.math.BigDecimal;
import java.util.*;
public class Main {
    private static final Scanner SC = new Scanner(System.in);
    private static final Store STORE = new Store();
    private static final Admin ADMIN = new Admin("A1", "Store Admin", "admin@example.com", "9000000000", "Head Office, Hyderabad");
    private static Customer customer;
    private static Cart cart = new Cart();

    public static void main(String[] args) {
        System.out.println("=== E-Commerce Product Module | Core Java Case Study ===");
        System.out.println("Demo customer: demo@example.com / demo123");
        boolean running = true;
        while (running) {
            try {
                menu();
                switch (number("Choose: ")) {
                    case 1 -> register(); case 2 -> login(); case 3 -> browse(); case 4 -> addToCart();
                    case 5 -> viewCart(); case 6 -> checkout(); case 7 -> viewOrders(); case 8 -> subscribe();
                    case 9 -> updateOrderStatus(); case 10 -> manageProducts(); case 11 -> manageCustomers();
                    case 12 -> updateProfile(); case 13 -> { running = false; System.out.println("Goodbye!"); }
                    default -> System.out.println("Choose an option from 1 to 13.");
                }
            } catch (RuntimeException ex) { System.out.println("Action not completed: " + ex.getMessage()); }
        }
    }
    private static void menu() {
        System.out.println("\n1 Register customer     2 Login");
        System.out.println("3 Browse/search products 4 Add product to cart");
        System.out.println("5 View cart             6 Checkout/place order");
        System.out.println("7 My orders/tracking    8 Subscription/free shipping");
        System.out.println("9 Admin: update status  10 Admin: manage products");
        System.out.println("11 Admin: manage/search customers 12 Update my profile 13 Exit");
        System.out.println("Signed in: " + (customer == null ? "Guest" : customer.getName()));
    }
    private static String read(String prompt) { System.out.print(prompt); return SC.nextLine().trim(); }
    private static int number(String prompt) { return Integer.parseInt(read(prompt)); }
    private static BigDecimal money(String prompt) { return new BigDecimal(read(prompt)); }
    private static void requireLogin() { if (customer == null) throw new IllegalStateException("Please log in first."); }
    private static boolean adminGate() {
        if (!ADMIN.getEmail().equalsIgnoreCase(read("Admin email (demo: admin@example.com): "))) {
            System.out.println("Admin access denied."); return false;
        }
        System.out.println("Demo admin access accepted (classroom simulation only)."); return true;
    }
    private static void register() {
        Customer c = STORE.register(read("Name: "), read("Email: "), read("Phone: "), read("Address (include city after comma): "), read("Password (4+ chars for demo): "));
        System.out.println("Registered successfully. Customer ID: " + c.getId());
    }
    private static void login() {
        customer = STORE.login(read("Email: "), read("Password: ")); cart = new Cart();
        System.out.println("Welcome, " + customer.getName() + "!");
    }
    private static void browse() {
        System.out.println("1 All  2 ID  3 Name  4 Name+exact price  5 Name+price range  6 Name+company  7 Name+company+price");
        List<Product> results;
        switch (number("Search option: ")) {
            case 1 -> results = STORE.getAllProducts();
            case 2 -> { Product p = STORE.findProductById(read("Product ID: "));
                System.out.println(p == null ? "Not found." : p); return; }
            case 3 -> results = STORE.searchByName(read("Name keyword: "));
            case 4 -> results = STORE.searchByNameAndPrice(read("Name keyword: "), money("Exact price: "));
            case 5 -> results = STORE.searchByNameAndRange(read("Name keyword: "), money("Minimum price: "), money("Maximum price: "));
            case 6 -> results = STORE.searchByNameAndCompany(read("Name keyword: "), read("Company: "));
            case 7 -> results = STORE.searchByNameCompanyAndPrice(read("Name keyword: "), read("Company: "), money("Exact price: "));
            default -> { System.out.println("Invalid search option."); return; }
        }
        if (results.isEmpty()) System.out.println("No matching products."); else results.forEach(System.out::println);
    }
    private static void addToCart() {
        requireLogin(); Product p = STORE.findProductById(read("Product ID: "));
        if (p == null) throw new IllegalArgumentException("Product not found.");
        cart.add(p, number("Quantity: "));
        System.out.println("Added to cart.");
    }
    private static Map<String, Product> productIndex() {
        Map<String, Product> result = new HashMap<>(); STORE.getAllProducts().forEach(p -> result.put(p.getId(), p)); return result;
    }
    private static void viewCart() {
        if (cart.isEmpty()) { System.out.println("Cart is empty."); return;
        }
        for (Map.Entry<String, Integer> e : cart.getQuantities().entrySet())
        {
            Product p = STORE.findProductById(e.getKey());
            if (p != null) System.out.println(p.getName() + " x " + e.getValue() + " = ₹" + p.getPrice().multiply(BigDecimal.valueOf(e.getValue())));
        }
        System.out.println("Subtotal: ₹" + cart.subtotal(productIndex()));
    }
    private static void checkout() {
        requireLogin(); viewCart();
        System.out.println("1 UPI  2 Debit/Credit Card  3 Cash on Delivery  4 Gift Card");
        PaymentMethod method; String reference;
        switch (number("Payment method: ")) {
            case 1 -> { method = new UpiPayment(); reference = read("Demo UPI reference: "); }
            case 2 -> { method = new CardPayment(); reference = read("Demo authorization reference (never enter card details): "); }
            case 3 -> { method = new CashOnDelivery(); reference = "COD"; }
            case 4 -> { method = new GiftCardPayment(STORE.getGiftCardBalances()); System.out.println("Demo codes: GIFT1000, GIFT500"); reference = read("Gift-card code: ").toUpperCase(); }
            default -> { System.out.println("Invalid payment method."); return; }
        }
        System.out.println("Order created: " + STORE.checkout(customer, cart, method, reference));
    }
    private static void viewOrders() {
        requireLogin(); List<Order> list = STORE.getOrdersFor(customer);
        if (list.isEmpty()) { System.out.println("No orders yet."); return; }
        for (Order o : list) {
            System.out.println(o);
            if (o.getTrackingNumber() != null) System.out.println("Tracking: " + o.getTrackingNumber() + " | Courier: " + o.getCourierName());
            o.getHistory().forEach(System.out::println);
        }
    }
    private static void subscribe() {
        requireLogin(); System.out.println("1 Monthly 2 Quarterly 3 Half-yearly 4 Annual");
        SubscriptionPlan plan = switch (number("Plan: ")) { case 1 -> SubscriptionPlan.MONTHLY; case 2 -> SubscriptionPlan.QUARTERLY; case 3 -> SubscriptionPlan.HALF_YEARLY; case 4 -> SubscriptionPlan.ANNUAL; default -> throw new IllegalArgumentException("Invalid plan."); };
        STORE.subscribe(customer, plan); System.out.println("Active until " + customer.getSubscriptionExpiry() + ". Shipping is free while active.");
    }
    private static void updateOrderStatus() {
        if (!adminGate()) return;
        if (STORE.getAllOrders().isEmpty()) { System.out.println("No orders available."); return; }
        STORE.getAllOrders().forEach(System.out::println); Order o = STORE.findOrder(read("Order ID: "));
        if (o == null) throw new IllegalArgumentException("Order not found.");
        OrderStatus[] statuses = OrderStatus.values(); for (int i=0;i<statuses.length;i++) System.out.println((i+1)+" "+statuses[i]);
        int n = number("New status number: "); if (n<1 || n>statuses.length) throw new IllegalArgumentException("Invalid status number.");
        o.updateStatus(statuses[n-1]); System.out.println("Status updated: " + o.getStatus());
        if (o.getTrackingNumber()!=null) System.out.println("Tracking: " + o.getTrackingNumber() + " | Courier: " + o.getCourierName());
    }
    private static void manageProducts() {
        if (!adminGate()) return;
        System.out.println("1 List 2 Add 3 Update 4 Delete");
        switch (number("Action: ")) {
            case 1 -> STORE.getAllProducts().forEach(System.out::println);
            case 2 -> { Product p = new Product(read("ID: "), read("Name: "), read("Company: "), money("Price: "), number("Stock: ")); STORE.addProduct(p); System.out.println("Product added."); }
            case 3 -> { String id=read("ID to update: "); STORE.updateProduct(id, read("New name: "), read("New company: "), money("New price: "), number("New stock: ")); System.out.println("Product updated."); }
            case 4 -> System.out.println(STORE.deleteProduct(read("ID to delete: ")) ? "Product deleted from catalog." : "Product not found.");
            default -> System.out.println("Invalid action.");
        }
    }
    private static void manageCustomers() {
        if (!adminGate()) return;
        System.out.println("1 List 2 Search name 3 Search city 4 Search email 5 Search phone 6 Delete by email");
        switch (number("Action: ")) {
            case 1 -> STORE.getAllCustomers().forEach(System.out::println);
            case 2 -> STORE.findCustomersByName(read("Name: ")).forEach(System.out::println);
            case 3 -> STORE.findCustomersByCity(read("City: ")).forEach(System.out::println);
            case 4 -> { Customer c=STORE.findCustomerByEmail(read("Email: ")); System.out.println(c==null?"Not found.":c); }
            case 5 -> { Customer c=STORE.findCustomerByPhone(read("Phone: ")); System.out.println(c==null?"Not found.":c); }
            case 6 -> { String email=read("Email to delete: "); if(customer!=null&&customer.getEmail().equalsIgnoreCase(email)) throw new IllegalArgumentException("Log out before deleting your active account."); System.out.println(STORE.deleteCustomer(email)?"Customer deleted.":"Customer not found."); }
            default -> System.out.println("Invalid action.");
        }
    }
    private static void updateProfile() {
        requireLogin(); STORE.updateCustomer(customer, read("New name: "), read("New email: "), read("New phone: "), read("New address: "));
        System.out.println("Profile updated.");
    }
}
