package com.ecommerce;

import java.math.BigDecimal;

public class Product {
    private final String id;
    private String name;
    private String company;
    private BigDecimal price;
    private int stock;
    private boolean active = true;

    public Product(String id, String name, String company, BigDecimal price, int stock) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Product ID is required.");
        this.id = id.trim();
        setName(name);
        setCompany(company);
        setPrice(price);
        setStock(stock);
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getCompany() { return company; }
    public BigDecimal getPrice() { return price; }
    public int getStock() { return stock; }
    public boolean isActive() { return active; }
    public void deactivate() { active = false; }

    public void setName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name is required.");
        this.name = name.trim();
    }
    public void setCompany(String company) {
        if (company == null || company.isBlank()) throw new IllegalArgumentException("Company is required.");
        this.company = company.trim();
    }
    public void setPrice(BigDecimal price) {
        if (price == null || price.signum() < 0) throw new IllegalArgumentException("Price cannot be negative.");
        this.price = price.setScale(2, java.math.RoundingMode.HALF_UP);
    }
    public void setStock(int stock) {
        if (stock < 0) throw new IllegalArgumentException("Stock cannot be negative.");
        this.stock = stock;
    }
    public void reduceStock(int quantity) {
        if (quantity <= 0 || quantity > stock) throw new IllegalArgumentException("Insufficient stock.");
        stock -= quantity;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + company + " | ₹" + price + " | stock: " + stock;
    }
}
