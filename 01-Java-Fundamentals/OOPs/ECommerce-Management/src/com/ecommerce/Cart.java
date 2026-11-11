package com.ecommerce;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    private final Map<String, Integer> quantities = new LinkedHashMap<>();

    public void add(Product product, int quantity) {
        if (product == null) throw new IllegalArgumentException("Product not found.");
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive.");
        quantities.merge(product.getId(), quantity, Integer::sum);
    }

    public Map<String, Integer> getQuantities() {
        return Map.copyOf(quantities);
    }

    public boolean isEmpty()
    {
        return quantities.isEmpty();
    }
    public void clear() { quantities.clear();
    }

    public BigDecimal subtotal(Map<String, Product> products)
    {
        BigDecimal total = BigDecimal.ZERO;
        for (Map.Entry<String, Integer> entry : quantities.entrySet())
        {
            Product product = products.get(entry.getKey());
            if (product == null) throw new IllegalArgumentException("Product no longer exists: " + entry.getKey());
            total = total.add(product.getPrice().multiply(BigDecimal.valueOf(entry.getValue())));
        }
        return total.setScale(2, java.math.RoundingMode.HALF_UP);
    }
}
