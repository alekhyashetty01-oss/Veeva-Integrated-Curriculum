package com.ecommerce;
import java.math.BigDecimal;
import java.util.Map;
public class GiftCardPayment implements PaymentMethod {
    private final Map<String, BigDecimal> balances;
    public GiftCardPayment(Map<String, BigDecimal> balances) { this.balances = balances; }
    public String getName() { return "Gift Card"; }
    public boolean pay(BigDecimal amount, String reference) {
        if (amount == null || amount.signum() < 0 || reference == null) return false;
        String code = reference.trim().toUpperCase();
        BigDecimal balance = balances.get(code);
        if (balance == null || balance.compareTo(amount) < 0) return false;
        balances.put(code, balance.subtract(amount));
        return true;
    }
}
