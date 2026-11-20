INVENTORY & SALES MANAGEMENT SYSTEM

Open this folder in IntelliJ IDEA and run src/Main.java.
Requires JDK 8 or later.

Sample data is loaded automatically:
C101 Tech Suppliers
C102 Keyboard World
P101 Wireless Mouse - $25.00 - stock 10
P102 Mechanical Keyboard - $60.00 - stock 0

Collections:
HashMap<String, Company> - company directory
HashMap<String, Product> - product catalog
HashMap<String, Integer> - inventory tracker
ArrayList<Transaction> - transaction log
LinkedHashMap<String, Integer> - order items

A complete order is validated before any stock is deducted. If one item fails,
the whole order is rejected and stock remains unchanged.

Data is stored in memory and resets when the program closes.
