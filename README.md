# Inventory Billing System

A clean, beginner-friendly **Command Line Interface (CLI)** application built using **Core Java** and **Object-Oriented Programming (OOP)** principles. Designed as an **internship certification project** demonstrating real-world inventory management, transaction handling, and billing calculation.

---

## 1. Project Objective

The **Inventory Billing System** enables retail shop and store personnel to:
* Manage product catalog (Add new items, view inventory list, search by ID).
* Update item stock dynamically when new inventory arrives.
* Process customer orders and generate itemized bills with multiple items.
* Automatically compute subtotal, 10% tax, and final grand total.
* Automatically decrement available stock once a bill is successfully generated.
* Handle user errors cleanly without crashing through robust input validation.

---

## 2. Technologies Used

* **Language**: Java (JDK 8 or higher, tested on OpenJDK 17)
* **Core Concepts**:
  * Object-Oriented Programming (OOP) - Encapsulation, Classes, Objects
  * Java Collections Framework (`java.util.ArrayList`)
  * User Input Handling (`java.util.Scanner`)
  * Exception Handling (`try-catch`, `NumberFormatException`)
* **Interface**: Command Line Interface (CLI / Terminal)
* **External Dependencies**: None (Standard Java SE runtime only)

---

## 3. Project Structure

```
InventoryBillingSystem/
│
├── Item.java                     # Model class for inventory product details
├── BillItem.java                 # Model class representing an item added to a bill
├── InventoryBillingSystem.java   # Main driver class with menu & business logic
└── README.md                     # Complete project documentation
```

---

## 4. Class Descriptions

### 1. `Item.java`
* Represents a physical product in the store inventory.
* **Fields**:
  * `private int id` - Unique ID of the product.
  * `private String name` - Name of the product.
  * `private double price` - Unit price of the product.
  * `private int quantity` - Stock available in the inventory.
* **OOP Concept**: Demonstrates **Encapsulation** using private variables with public getters and setters.
* **Methods**:
  * `addStock(int qty)`: Increases stock when new shipment arrives.
  * `reduceStock(int qty)`: Decreases stock after a successful sale.

### 2. `BillItem.java`
* Represents an individual item purchased in a billing transaction.
* **Fields**:
  * `private Item item`: Reference to the purchased inventory item.
  * `private int quantity`: Quantity purchased by the customer.
  * `private double unitPrice`: Price per unit at purchase time.
  * `private double totalPrice`: Calculated item total (`unitPrice * quantity`).
* **Methods**:
  * `getItemName()`, `getItemId()`, `getQuantity()`, `getUnitPrice()`, `getTotalPrice()`.

### 3. `InventoryBillingSystem.java`
* The main controller class containing the CLI menu loop and application logic.
* **Key Methods**:
  * `main(String[] args)`: Application entry point and menu control loop.
  * `loadSampleData()`: Pre-loads initial items (`Pen`, `Notebook`, `Pencil`, `Eraser`).
  * `displayMenu()`: Prints the formatted main menu.
  * `addItem()`: Validates and adds a new item to `inventory`. Prevents duplicates and invalid data.
  * `viewInventory()`: Displays all products in a formatted table.
  * `searchItem()`: Searches for a product by ID and prints details.
  * `updateStock()`: Increases the stock of an existing item.
  * `generateBill()`: Handles multi-item customer purchases, verifies stock, displays formatted receipt, and reduces inventory stock.
  * `findItemById(int id)`: Helper method to retrieve an `Item` from the `ArrayList`.
  * `getIntInput(String prompt)` / `getDoubleInput(String prompt)` / `getStringInput(String prompt)`: Helper methods with exception handling to prevent runtime crashes.

---

## 5. How to Compile and Run

### Prerequisites
Make sure Java is installed on your computer. Check with:
```bash
java -version
javac -version
```

### Steps:
1. Open terminal/command prompt in the project directory:
   ```bash
   cd c:\InventoryBillingSystem
   ```
2. Compile the Java source files:
   ```bash
   javac Item.java BillItem.java InventoryBillingSystem.java
   ```
3. Run the application:
   ```bash
   java InventoryBillingSystem
   ```

---

## 6. Sample Application Flow & Outputs

### Main Menu
```text
========================================
        INVENTORY BILLING SYSTEM        
========================================
1. Add Item
2. View Inventory
3. Search Item
4. Update Stock
5. Generate Bill
6. Exit
========================================
Enter your choice: 
```

### View Inventory (Option 2)
```text
------------------------------------------------------------
ID         Item Name            Price           Stock     
------------------------------------------------------------
101        Pen                  Rs. 20.00       50        
102        Notebook             Rs. 60.00       30        
103        Pencil               Rs. 10.00       100       
104        Eraser               Rs. 5.00        80        
------------------------------------------------------------
```

### Generate Bill (Option 5)
```text
--- Generate Customer Bill ---

Enter Item ID: 101
Enter Quantity: 3
Pen (x3) added to bill.

Add another item? (yes/no): yes

Enter Item ID: 102
Enter Quantity: 2
Notebook (x2) added to bill.

Add another item? (yes/no): no

==================================================
                  CUSTOMER BILL                   
==================================================
Item           Quantity   Price        Total     
--------------------------------------------------
Pen            3          Rs. 20.00    Rs. 60.00   
Notebook       2          Rs. 60.00    Rs. 120.00  
--------------------------------------------------
Subtotal                               Rs.   180.00
Tax (10%)                              Rs.    18.00
--------------------------------------------------
GRAND TOTAL                            Rs.   198.00
==================================================
Thank you for your purchase!
==================================================

[Inventory stock updated automatically]
```

---

## 7. Test Cases Handled

| S.No | Test Scenario | Input Given | Expected Output | Status |
|---|---|---|---|---|
| 1 | Add valid item | ID: 105, Name: Marker, Price: 25, Stock: 40 | Item added successfully | Passed |
| 2 | Add duplicate Item ID | ID: 101 | Duplicate IDs are not allowed | Passed |
| 3 | Add non-numeric ID/Price | ID: `abc` | Please enter a valid number | Passed |
| 4 | Add negative price or qty | Price: `-10` | Price must be greater than 0 | Passed |
| 5 | Search existing item | ID: 101 | Shows Item details | Passed |
| 6 | Search non-existing item | ID: 999 | Item not found | Passed |
| 7 | Update stock with valid qty | ID: 101, Add: 10 | Stock: 50 -> 60 | Passed |
| 8 | Buy item with insufficient stock | Stock: 50, Requested: 100 | Insufficient stock! | Passed |
| 9 | Multi-item billing calculation | 3x Pen + 2x Notebook | Subtotal: 180, Tax: 18, Grand Total: 198 | Passed |
| 10 | Stock auto-reduction | After bill | Pen stock decreases from 50 to 47 | Passed |
| 11 | Invalid menu choice | `9` or `abc` | Re-prompts without crashing | Passed |

---

## 8. Future Enhancements

1. **Persistent File Storage**: Save inventory and sales receipts to CSV/JSON or text files (`products.csv`, `invoices.txt`).
2. **Database Integration**: Connect with MySQL / SQLite using JDBC.
3. **Discount Management**: Add promotional coupons or percentage discounts per bill.
4. **Graphical User Interface (GUI)**: Build a desktop UI using JavaFX or Swing.
