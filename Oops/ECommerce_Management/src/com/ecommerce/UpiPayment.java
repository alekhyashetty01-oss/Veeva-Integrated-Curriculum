package com.ecommerce;
import java.math.BigDecimal;
public class UpiPayment implements PaymentMethod {
    public String getName() { return "UPI"; }
    public boolean pay(BigDecimal amount, String reference) {
        return amount != null && amount.signum() >= 0 && reference != null && !reference.isBlank();
    }
}
