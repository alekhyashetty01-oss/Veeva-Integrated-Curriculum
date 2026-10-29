package com.ecommerce;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final String id;
    private final String customerId;
    private final List<String> itemSummaries;
    private final BigDecimal subtotal;
    private final BigDecimal discount;
    private final BigDecimal shipping;
    private final BigDecimal total;
    private final String paymentMethod;
    private OrderStatus status = OrderStatus.PLACED;
    private String trackingNumber;
    private String courierName;
    private final List<String> history = new ArrayList<>();

    public Order(String id, String customerId, List<String> itemSummaries,
                 BigDecimal subtotal, BigDecimal discount, BigDecimal shipping,
                 BigDecimal total, String paymentMethod) {
        this.id = id;
        this.customerId = customerId;
        this.itemSummaries = List.copyOf(itemSummaries);
        this.subtotal = subtotal;
        this.discount = discount;
        this.shipping = shipping;
        this.total = total;
        this.paymentMethod = paymentMethod;
        history.add(LocalDateTime.now() + " - PLACED");
    }

    public String getId() { return id; }
    public String getCustomerId() { return customerId; }
    public OrderStatus getStatus() { return status; }
    public BigDecimal getTotal() { return total; }
    public BigDecimal getSubtotal() { return subtotal; }
    public String getCourierName() { return courierName; }
    public List<String> getHistory() { return List.copyOf(history); }
    public String getTrackingNumber() { return trackingNumber; }

    public void updateStatus(OrderStatus next) {
        boolean allowed = switch (status) {
            case PLACED -> next == OrderStatus.RECEIVED;
            case RECEIVED -> next == OrderStatus.DISPATCHED;
            case DISPATCHED -> next == OrderStatus.WITH_COURIER || next == OrderStatus.IN_TRANSIT;
            case WITH_COURIER -> next == OrderStatus.IN_TRANSIT || next == OrderStatus.DELIVERED;
            case IN_TRANSIT -> next == OrderStatus.WITH_COURIER || next == OrderStatus.DELIVERED;
            case DELIVERED -> next == OrderStatus.RETURNED;
            case RETURNED -> false;
        };
        if (!allowed) throw new IllegalArgumentException("Invalid status change: " + status + " -> " + next);
        status = next;
        if (next == OrderStatus.DISPATCHED) { trackingNumber = "TRK-" + id; courierName = "Demo Courier"; }
        history.add(LocalDateTime.now() + " - " + status);
    }

    @Override
    public String toString() {
        return "Order " + id + " | customer=" + customerId + " | items=" + itemSummaries
                + " | subtotal=₹" + subtotal + " | discount=₹" + discount + " | shipping=₹"
                + shipping + " | total=₹" + total + " | payment=" + paymentMethod + " | status=" + status;
    }
}
