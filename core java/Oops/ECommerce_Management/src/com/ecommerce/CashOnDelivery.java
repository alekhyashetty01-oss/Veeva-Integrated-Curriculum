package com.ecommerce;
import java.math.BigDecimal;
public class CashOnDelivery implements PaymentMethod {
    public String getName() { return "Cash on Delivery"; }
    public boolean pay(BigDecimal amount, String reference) {
        return amount != null && amount.signum() >= 0;
    }
}
