package com.ecommerce;
public abstract class User {
    private final String id;
    private String name;
    private String email;
    private String phone;
    private String address;

    protected User(String id, String name, String email, String phone, String address) {
        this.id = requireText(id, "ID");
        setName(name);
        setEmail(email);
        setPhone(phone);
        setAddress(address);
    }

    private static String requireText(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " cannot be empty.");
        }
        return value.trim();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }

    public void setName(String name) { this.name = requireText(name, "Name"); }

    public void setEmail(String email) {
        String value = requireText(email, "Email").toLowerCase();
        if (!value.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            throw new IllegalArgumentException("Enter a valid email.");
        }
        this.email = value;
    }

    public void setPhone(String phone) {
        String value = requireText(phone, "Phone").replaceAll("[\\s-]", "");
        if (!value.matches("\\+?[0-9]{10,15}")) {
            throw new IllegalArgumentException("Phone must contain 10-15 digits.");
        }
        this.phone = value;
    }

    public void setAddress(String address) { this.address = requireText(address, "Address"); }
}
