package com.ecommerce;

import java.math.BigDecimal;
public interface PaymentMethod {
    String getName();
    boolean pay(BigDecimal amount, String reference);
}
