package com.ecommerce;

import java.time.LocalDate;
public class Customer extends User {
    private final String password;
    private LocalDate subscriptionExpiry;

    public Customer(String id, String name, String email, String phone, String address, String password) {
        super(id, name, email, phone, address);
        if (password == null || password.length() < 4) {
            throw new IllegalArgumentException("Demo password must contain at least 4 characters.");
        }
        this.password = password;
    }

    public boolean login(String enteredPassword)
    {
        return password.equals(enteredPassword);
    }

    public boolean hasFreeShipping() {
        return subscriptionExpiry != null && !subscriptionExpiry.isBefore(LocalDate.now());
    }

    public void subscribe(SubscriptionPlan plan) {
        LocalDate start = hasFreeShipping() ? subscriptionExpiry : LocalDate.now();
        subscriptionExpiry = start.plus(plan.getDuration());
    }

    public LocalDate getSubscriptionExpiry() { return subscriptionExpiry; }

    @Override
    public String toString() {
        return "Customer{id='" + getId() + "', name='" + getName() + "', email='" + getEmail()
                + "', phone='" + getPhone() + "', address='" + getAddress() + "'}";
    }
}
