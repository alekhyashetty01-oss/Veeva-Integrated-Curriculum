package com.ecommerce;
import java.math.BigDecimal;
public class CardPayment implements PaymentMethod {
    public String getName() { return "Debit/Credit Card (demo)"; }
    public boolean pay(BigDecimal amount, String reference) {
        return amount != null && amount.signum() >= 0 && reference != null && !reference.isBlank();
    }
}
