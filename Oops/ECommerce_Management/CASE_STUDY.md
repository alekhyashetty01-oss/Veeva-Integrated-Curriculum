# Case Study: E-Commerce Product Module Using Core Java

## 1. Problem statement
Build a small console-based e-commerce simulation that demonstrates how Java classes and OOP concepts can model customers, products, carts, orders, payments, and delivery status.

## 2. Scope
Included:
- View/search products by ID, name, price range, company
- Add items to a cart and place an order
- Calculate subtotal, discount, shipping, and grand total
- Demonstrate UPI, card, cash on delivery, and gift-card payment choices
- Simulate order status updates and tracking
- Demonstrate monthly, quarterly, half-yearly, and annual subscriptions
- Demonstrate a loyalty discount after a configurable spend threshold

Not included:
- Database persistence, web UI, Spring Boot, real authentication/security, real payments, live courier integration, tax engine, refunds, or production-grade concurrency handling.

## 3. OOP concept mapping
| Concept | Example in this project | Why it is useful |
|---|---|---|
| Encapsulation | Private fields in `Product`, `Customer`, `Order`; public methods validate changes | Prevents invalid direct updates |
| Abstraction | `PaymentMethod` interface and abstract `User` class | Exposes essential behavior while hiding details |
| Inheritance | `Customer extends User`, `Admin extends User` | Reuses common user data |
| Polymorphism | `PaymentMethod` variable can hold UPI, card, COD, or gift-card object | Checkout calls the same `pay()` method for different payment types |
| Collections | `ArrayList` for orders, `HashMap` for products and cart quantities | Stores and retrieves related data |
| Enum | `OrderStatus`, `SubscriptionPlan` | Limits values to defined choices |
| Exception handling | Invalid quantity, product not found, invalid status | Stops invalid operations safely |
| BigDecimal | Product price and order totals | Avoids floating-point money errors |

## 4. Simple class relationships
- `User` is the common abstract parent.
- `Customer` and `Admin` inherit from `User`.
- `Product` represents an item in the catalog.
- `Cart` contains product IDs and quantities.
- `Order` stores a snapshot of purchased items and total.
- `PaymentMethod` is implemented by four payment classes.
- `Store` coordinates product search, cart checkout, subscription, and order status.

## 5. Business rules used in this example
- Standard shipping: ₹80.
- Active subscription: shipping is waived.
- Loyalty: 5% discount if previously delivered order subtotal totals at least ₹5,000.
- Grand total: subtotal − discount + shipping.
- Tracking number appears after dispatch.
- Stock must be sufficient at checkout.
- Payment behavior is simulated; do not enter real payment credentials.

These values are teaching examples and can be changed in `Store.java`.

## 6. Example calculation
Suppose a customer buys:
- Wireless Mouse: ₹799 × 1 = ₹799
- Laptop Stand: ₹1,299 × 1 = ₹1,299

Subtotal = ₹2,098.00  
Discount = ₹0.00 (assuming the loyalty threshold has not been reached)  
Shipping = ₹80.00 (no active subscription)  
Grand total = ₹2,178.00

If the subscription is active, shipping is ₹0 and the total is ₹2,098.00.

## 7. Suggested classroom explanation
1. Start with `Product` and `Customer` to explain classes, objects, and encapsulation.
2. Explain `User` → `Customer` / `Admin` to show inheritance.
3. Explain `PaymentMethod` and its implementations to show abstraction and polymorphism.
4. Explain `Cart` and `Store` to show collections and business logic.
5. Run checkout to explain methods, loops, validation, exceptions, and `BigDecimal`.
6. Update order status to explain enums and state changes.
7. Activate a subscription and compare shipping totals.
8. Explain that real applications would replace in-memory collections with a database.


## Corrections included in this version
- Profile update is available from the menu.
- Admin menu supports product add, update, list, and delete.
- City search compares the last comma-separated address part as the city.
- Gift-card balances are shared across payment attempts during the same run.
- Loyalty qualification uses previous delivered order merchandise subtotals, not shipping-inclusive totals. Returned orders do not qualify.
