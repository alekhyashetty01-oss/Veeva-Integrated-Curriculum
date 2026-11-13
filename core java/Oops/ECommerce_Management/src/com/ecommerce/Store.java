package com.ecommerce;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class Store {
    private final Map<String, Product> products = new LinkedHashMap<>();
    private final Map<String, Customer> customersByEmail = new HashMap<>();
    private final List<Order> orders = new ArrayList<>();
    private final Map<String, BigDecimal> giftCardBalances = new HashMap<>();
    private int nextCustomerNumber = 102;
    private final BigDecimal shippingCharge = new BigDecimal("80.00");
    private final BigDecimal loyaltyThreshold = new BigDecimal("5000.00");
    private final BigDecimal loyaltyRate = new BigDecimal("0.05");
    private int nextOrderNumber = 1001;

    public Store() {
        addProduct(new Product("P101", "Wireless Mouse", "LogiTech", new BigDecimal("799.00"), 20));
        addProduct(new Product("P102", "Mechanical Keyboard", "KeyCraft", new BigDecimal("2499.00"), 10));
        addProduct(new Product("P103", "USB-C Hub", "ConnectPro", new BigDecimal("1899.00"), 15));
        addProduct(new Product("P104", "Wireless Headphones", "SoundMax", new BigDecimal("3499.00"), 8));
        addProduct(new Product("P105", "Gaming Mouse", "LogiTech", new BigDecimal("1499.00"), 12));
        addProduct(new Product("P106", "Laptop Stand", "ErgoWorks", new BigDecimal("1299.00"), 18));

        Customer demo = new Customer("C101", "Demo Customer", "demo@example.com",
                "9876543210", "12 Main Road, Tadepalligudem", "demo123");
        customersByEmail.put(demo.getEmail(), demo);
        giftCardBalances.put("GIFT1000", new BigDecimal("1000.00"));
        giftCardBalances.put("GIFT500", new BigDecimal("500.00"));
    }

    public void addProduct(Product product) {
        if (products.containsKey(product.getId())) throw new IllegalArgumentException("Product ID already exists.");
        products.put(product.getId(), product);
    }
    public boolean deleteProduct(String id) { return products.remove(id) != null; }
    public void updateProduct(String id, String name, String company, BigDecimal price, int stock) {
        Product p = findProductById(id);
        if (p == null) throw new IllegalArgumentException("Product not found.");
        p.setName(name); p.setCompany(company); p.setPrice(price); p.setStock(stock);
    }
    public List<Product> getAllProducts() { return products.values().stream().filter(Product::isActive).toList(); }
    public Product findProductById(String id) { Product p = products.get(id); return p != null && p.isActive() ? p : null; }

    public List<Product> searchByName(String name) {
        return getAllProducts().stream().filter(p -> p.getName().toLowerCase().contains(name.toLowerCase())).toList();
    }
    public List<Product> searchByNameAndPrice(String name, BigDecimal price) {
        return searchByName(name).stream().filter(p -> p.getPrice().compareTo(price) == 0).toList();
    }
    public List<Product> searchByNameAndRange(String name, BigDecimal min, BigDecimal max) {
        if (min.compareTo(max) > 0) throw new IllegalArgumentException("Minimum price cannot exceed maximum price.");
        return searchByName(name).stream()
                .filter(p -> p.getPrice().compareTo(min) >= 0 && p.getPrice().compareTo(max) <= 0).toList();
    }
    public List<Product> searchByNameAndCompany(String name, String company) {
        return searchByName(name).stream().filter(p -> p.getCompany().equalsIgnoreCase(company)).toList();
    }
    public List<Product> searchByNameCompanyAndPrice(String name, String company, BigDecimal price) {
        return searchByNameAndCompany(name, company).stream()
                .filter(p -> p.getPrice().compareTo(price) == 0).toList();
    }

    public Customer register(String name, String email, String phone, String address, String password) {
        String key = email.trim().toLowerCase();
        if (customersByEmail.containsKey(key)) throw new IllegalArgumentException("Email is already registered.");
        Customer customer = new Customer("C" + nextCustomerNumber++, name, key, phone, address, password);
        customersByEmail.put(customer.getEmail(), customer);
        return customer;
    }
    public Customer login(String email, String password) {
        Customer customer = customersByEmail.get(email.toLowerCase());
        if (customer == null || !customer.login(password)) throw new IllegalArgumentException("Invalid email or password.");
        return customer;
    }
    public List<Customer> findCustomersByName(String name) {
        return customersByEmail.values().stream().filter(c -> c.getName().toLowerCase().contains(name.toLowerCase())).toList();
    }
    public List<Customer> findCustomersByCity(String city) {
        return customersByEmail.values().stream().filter(c -> { String[] parts = c.getAddress().split(","); return parts[parts.length - 1].trim().equalsIgnoreCase(city.trim()); }).toList();
    }
    public Customer findCustomerByEmail(String email) { return customersByEmail.get(email.trim().toLowerCase()); }
    public Customer findCustomerByPhone(String phone) {
        return customersByEmail.values().stream().filter(c -> c.getPhone().equals(phone)).findFirst().orElse(null);
    }
    public boolean deleteCustomer(String email) { return customersByEmail.remove(email.trim().toLowerCase()) != null; }
    public List<Customer> getAllCustomers() { return List.copyOf(customersByEmail.values()); }
    public void updateCustomer(Customer c, String name, String email, String phone, String address) {
        String newEmail = email.trim().toLowerCase();
        Customer existing = customersByEmail.get(newEmail);
        if (existing != null && existing != c) throw new IllegalArgumentException("Email already belongs to another customer.");
        String oldEmail = c.getEmail();
        c.setName(name); c.setEmail(newEmail); c.setPhone(phone); c.setAddress(address);
        customersByEmail.remove(oldEmail); customersByEmail.put(c.getEmail(), c);
    }
    public Map<String, BigDecimal> getGiftCardBalances() { return giftCardBalances; }

    public Order checkout(Customer customer, Cart cart, PaymentMethod payment, String reference) {
        if (cart.isEmpty()) throw new IllegalArgumentException("Cart is empty.");
        for (Map.Entry<String, Integer> entry : cart.getQuantities().entrySet()) {
            Product p = products.get(entry.getKey());
            if (p == null) throw new IllegalArgumentException("Product not found: " + entry.getKey());
            if (entry.getValue() > p.getStock()) throw new IllegalArgumentException("Insufficient stock for " + p.getName());
        }

        BigDecimal subtotal = cart.subtotal(products);
        BigDecimal historicalDeliveredSpend = orders.stream()
                .filter(o -> o.getCustomerId().equals(customer.getId()) && o.getStatus() == OrderStatus.DELIVERED)
                .map(Order::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal discount = historicalDeliveredSpend.compareTo(loyaltyThreshold) >= 0
                ? subtotal.multiply(loyaltyRate).setScale(2, java.math.RoundingMode.HALF_UP)
                : BigDecimal.ZERO.setScale(2);
        if (discount.compareTo(subtotal) > 0) discount = subtotal;
        BigDecimal shipping = customer.hasFreeShipping() ? BigDecimal.ZERO.setScale(2) : shippingCharge;
        BigDecimal total = subtotal.subtract(discount).add(shipping).setScale(2, java.math.RoundingMode.HALF_UP);

        if (!payment.pay(total, reference)) throw new IllegalArgumentException("Payment simulation failed.");
        List<String> itemSummaries = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : cart.getQuantities().entrySet()) {
            Product p = products.get(entry.getKey());
            int quantity = entry.getValue();
            p.reduceStock(quantity);
            itemSummaries.add(p.getName() + " x " + quantity + " @ ₹" + p.getPrice());
        }
        Order order = new Order("O" + nextOrderNumber++, customer.getId(), itemSummaries,
                subtotal, discount, shipping, total, payment.getName());
        orders.add(order);
        cart.clear();
        return order;
    }

    public List<Order> getOrdersFor(Customer customer) {
        return orders.stream().filter(o -> o.getCustomerId().equals(customer.getId())).toList();
    }
    public List<Order> getAllOrders() { return List.copyOf(orders); }
    public Order findOrder(String id) {
        return orders.stream().filter(o -> o.getId().equalsIgnoreCase(id)).findFirst().orElse(null);
    }
    public void subscribe(Customer customer, SubscriptionPlan plan) { customer.subscribe(plan); }
    public BigDecimal getShippingCharge() { return shippingCharge; }
}
