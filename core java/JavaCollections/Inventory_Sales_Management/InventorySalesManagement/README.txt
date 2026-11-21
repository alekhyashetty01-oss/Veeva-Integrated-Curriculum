Problem Statement: Inventory & Sales Management System



1. Background & Objective
Businesses need an automated system to organize supplier information, manage product catalogs, and process customer sales accurately. The objective of this project is to design and implement a software module that maintains structured collections of company and product data, handles sales transactions, tracks real-time stock levels, and raises appropriate alerts when inventory runs low or out of stock.

2. Core Data Collections (Entities & Mappings)
The system must maintain distinct data structures (such as dictionaries, hash maps, or database tables) to store and map the following information:
Company Directory: Maps a unique Company ID to company metadata, including Company Name, Contact Details, and Location.
Product Catalog: Maps a unique Product ID or Product Name to its core attributes, including Unit Price, Associated Company ID, and Category.
Inventory Tracker: Maps each Product ID to its current Available Stock Quantity.

3. Functional Requirements
Data Management (CRUD Operations):
oAdd/Update Companies: Register new suppliers or update existing company details.
oAdd/Update Products: Add new products to the catalog, assign them to a specific company, set their unit prices, and initialize stock quantities.
oPrice Lookup: Retrieve the exact price and supplier details of any product using its name or ID.
Transaction Processing (Selling Products):
oAccept customer orders containing one or more products along with the requested quantities.
oValidate product existence in the catalog before processing.
oCalculate the total transaction cost based on mapped product prices and requested quantities.
oDeduct the sold quantity from the Inventory Tracker immediately upon a successful sale.
oMaintain a Transaction Log recording the Transaction ID, Product(s) sold, Quantity, Total Amount, and Timestamp.
Stock Tracking & Exception Handling:
oContinuously monitor product availability before and after every transaction.
oOut-of-Stock Alert: If a requested product has 0 units available, block the sale for that item and raise a clear "Product Out of Stock" message.
oInsufficient Stock Alert: If the requested quantity exceeds the current available stock, halt the transaction and raise an "Insufficient Stock" message displaying the exact number of units currently remaining.

4. Expected Input & Output Scenarios
Scenario A (Successful Sale):
oInput: Sell 3 units of "Wireless Mouse" (Available stock: 10, Price: $25).
oOutput: Transaction successful. Total Bill: $75. Updated Stock for "Wireless Mouse": 7.
Scenario B (Insufficient Stock):
oInput: Sell 12 units of "Wireless Mouse" (Available stock: 7).
oOutput: Raise Warning/Exception — "Transaction Failed: Only 7 units of Wireless Mouse are available."
Scenario C (Out of Stock):
oInput: Sell 1 unit of "Mechanical Keyboard" (Available stock: 0).
oOutput: Raise Warning/Exception — "Alert: Mechanical Keyboard is currently out of stock."

5. Technical Constraints & Edge Cases
Product names and IDs must prevent duplicate entries or conflicting price mappings.
Negative or zero quantities in a sales request must be rejected as invalid inputs.
If a multi-item order fails due to one item being out of stock, the system should either roll back the entire transaction or process only the available items based on business rules.
