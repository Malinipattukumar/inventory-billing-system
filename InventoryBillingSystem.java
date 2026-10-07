import java.util.ArrayList;
import java.util.Scanner;

/**
 * InventoryBillingSystem.java
 * Main class for the Inventory & Billing Management CLI Application.
 * Core Java Internship Project.
 */
public class InventoryBillingSystem {

    // Inventory storage using ArrayList
    private static ArrayList<Item> inventory = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static final double TAX_RATE = 0.10; // 10% Fixed Tax

    public static void main(String[] args) {
        // Pre-load sample inventory items for quick testing
        loadSampleData();

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    viewInventory();
                    break;
                case 3:
                    searchItem();
                    break;
                case 4:
                    updateStock();
                    break;
                case 5:
                    generateBill();
                    break;
                case 6:
                    System.out.println("\nThank you for using Inventory Billing System. Exiting application...");
                    running = false;
                    break;
                default:
                    System.out.println("\nInvalid choice! Please choose an option between 1 and 6.");
            }
        }
        scanner.close();
    }

    /**
     * Initializes inventory with default sample products.
     */
    private static void loadSampleData() {
        inventory.add(new Item(101, "Pen", 20.0, 50));
        inventory.add(new Item(102, "Notebook", 60.0, 30));
        inventory.add(new Item(103, "Pencil", 10.0, 100));
        inventory.add(new Item(104, "Eraser", 5.0, 80));
    }

    /**
     * Displays the main menu options.
     */
    private static void displayMenu() {
        System.out.println("\n========================================");
        System.out.println("        INVENTORY BILLING SYSTEM        ");
        System.out.println("========================================");
        System.out.println("1. Add Item");
        System.out.println("2. View Inventory");
        System.out.println("3. Search Item");
        System.out.println("4. Update Stock");
        System.out.println("5. Generate Bill");
        System.out.println("6. Exit");
        System.out.println("========================================");
    }

    /**
     * Adds a new item to the inventory after performing validations.
     */
    private static void addItem() {
        System.out.println("\n--- Add New Item ---");

        int id = getIntInput("Enter Item ID: ");
        if (id <= 0) {
            System.out.println("Invalid input! Item ID must be a positive integer.");
            return;
        }

        // Check for duplicate ID
        if (findItemById(id) != null) {
            System.out.println("Invalid input! Item with ID " + id + " already exists. Duplicate IDs are not allowed.");
            return;
        }

        String name = getStringInput("Enter Item Name: ");
        if (name.trim().isEmpty()) {
            System.out.println("Invalid input! Item name cannot be empty.");
            return;
        }

        double price = getDoubleInput("Enter Price: ");
        if (price <= 0) {
            System.out.println("Invalid input! Price must be greater than 0.");
            return;
        }

        int quantity = getIntInput("Enter Quantity: ");
        if (quantity < 0) {
            System.out.println("Invalid input! Quantity cannot be negative.");
            return;
        }

        Item newItem = new Item(id, name.trim(), price, quantity);
        inventory.add(newItem);
        System.out.println("\nItem added successfully!");
    }

    /**
     * Displays all items in inventory in a tabular format.
     */
    private static void viewInventory() {
        System.out.println("\n------------------------------------------------------------");
        System.out.printf("%-10s %-20s %-15s %-10s\n", "ID", "Item Name", "Price", "Stock");
        System.out.println("------------------------------------------------------------");

        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            for (Item item : inventory) {
                System.out.printf("%-10d %-20s Rs. %-11.2f %-10d\n",
                        item.getId(),
                        item.getName(),
                        item.getPrice(),
                        item.getQuantity());
            }
        }
        System.out.println("------------------------------------------------------------");
    }

    /**
     * Searches for an item by its ID and displays its details.
     */
    private static void searchItem() {
        System.out.println("\n--- Search Item ---");
        int id = getIntInput("Enter Item ID: ");

        Item item = findItemById(id);
        if (item != null) {
            System.out.println("\nItem Found!");
            System.out.println("Item ID    : " + item.getId());
            System.out.println("Item Name  : " + item.getName());
            System.out.printf("Price      : Rs. %.2f\n", item.getPrice());
            System.out.println("Stock      : " + item.getQuantity());
        } else {
            System.out.println("Item not found.");
        }
    }

    /**
     * Increases the stock quantity of an existing item.
     */
    private static void updateStock() {
        System.out.println("\n--- Update Stock ---");
        int id = getIntInput("Enter Item ID: ");

        Item item = findItemById(id);
        if (item == null) {
            System.out.println("Item not found. Please enter a valid Item ID.");
            return;
        }

        int qtyToAdd = getIntInput("Enter quantity to add: ");
        if (qtyToAdd <= 0) {
            System.out.println("Invalid input! Quantity to add must be greater than 0.");
            return;
        }

        int oldStock = item.getQuantity();
        item.addStock(qtyToAdd);
        int newStock = item.getQuantity();

        System.out.println("\nStock updated successfully!\n");
        System.out.println("Old Stock : " + oldStock);
        System.out.println("Added     : " + qtyToAdd);
        System.out.println("New Stock : " + newStock);
    }

    /**
     * Generates a customer bill for multiple items and reduces inventory stock.
     */
    private static void generateBill() {
        System.out.println("\n--- Generate Customer Bill ---");

        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty! Cannot generate bill.");
            return;
        }

        ArrayList<BillItem> billItems = new ArrayList<>();
        boolean addingMore = true;

        while (addingMore) {
            int id = getIntInput("\nEnter Item ID: ");
            Item item = findItemById(id);

            if (item == null) {
                System.out.println("Item not found. Please enter a valid Item ID.");
            } else {
                int quantity = getIntInput("Enter Quantity: ");

                if (quantity <= 0) {
                    System.out.println("Quantity must be greater than 0.");
                } else {
                    // Check already requested quantity in current cart
                    int alreadyInBill = 0;
                    for (BillItem bi : billItems) {
                        if (bi.getItemId() == id) {
                            alreadyInBill += bi.getQuantity();
                        }
                    }

                    int totalRequested = alreadyInBill + quantity;

                    if (totalRequested > item.getQuantity()) {
                        System.out.println("\nInsufficient stock!");
                        System.out.println("Available Stock: " + item.getQuantity());
                        System.out.println("Requested: " + quantity + (alreadyInBill > 0 ? " (Already in bill: " + alreadyInBill + ")" : ""));
                    } else {
                        // Check if item is already in bill, then update quantity or add new
                        boolean existsInBill = false;
                        for (BillItem bi : billItems) {
                            if (bi.getItemId() == id) {
                                bi.setQuantity(bi.getQuantity() + quantity);
                                existsInBill = true;
                                break;
                            }
                        }

                        if (!existsInBill) {
                            billItems.add(new BillItem(item, quantity));
                        }

                        System.out.println(item.getName() + " (x" + quantity + ") added to bill.");
                    }
                }
            }

            String choice = getStringInput("\nAdd another item? (yes/no): ").trim().toLowerCase();
            if (!choice.equals("yes") && !choice.equals("y")) {
                addingMore = false;
            }
        }

        // If no items were successfully added
        if (billItems.isEmpty()) {
            System.out.println("\nNo items in the bill. Transaction cancelled.");
            return;
        }

        // Calculations
        double subtotal = 0.0;
        for (BillItem bi : billItems) {
            subtotal += bi.getTotalPrice();
        }

        double tax = subtotal * TAX_RATE;
        double grandTotal = subtotal + tax;

        // Display Final Bill
        System.out.println("\n==================================================");
        System.out.println("                  CUSTOMER BILL                   ");
        System.out.println("==================================================");
        System.out.printf("%-14s %-10s %-12s %-10s\n", "Item", "Quantity", "Price", "Total");
        System.out.println("--------------------------------------------------");

        for (BillItem bi : billItems) {
            System.out.printf("%-14s %-10d Rs. %-8.2f Rs. %-8.2f\n",
                    bi.getItemName(),
                    bi.getQuantity(),
                    bi.getUnitPrice(),
                    bi.getTotalPrice());
        }

        System.out.println("--------------------------------------------------");
        System.out.printf("%-36s Rs. %8.2f\n", "Subtotal", subtotal);
        System.out.printf("%-36s Rs. %8.2f\n", "Tax (10%)", tax);
        System.out.println("--------------------------------------------------");
        System.out.printf("%-36s Rs. %8.2f\n", "GRAND TOTAL", grandTotal);
        System.out.println("==================================================");
        System.out.println("Thank you for your purchase!");
        System.out.println("==================================================");

        // Reduce stock in inventory after successful billing
        for (BillItem bi : billItems) {
            bi.getItem().reduceStock(bi.getQuantity());
        }
        System.out.println("\n[Inventory stock updated automatically]");
    }

    /**
     * Helper method to search for an item in the inventory by its ID.
     */
    private static Item findItemById(int id) {
        for (Item item : inventory) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    /**
     * Helper method to read a valid integer from console with error handling.
     */
    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextLine()) {
                System.exit(0);
            }
            String input = scanner.nextLine().replace("\uFEFF", "").trim();
            if (input.isEmpty()) {
                continue;
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    /**
     * Helper method to read a valid double from console with error handling.
     */
    private static double getDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextLine()) {
                System.exit(0);
            }
            String input = scanner.nextLine().replace("\uFEFF", "").trim();
            if (input.isEmpty()) {
                continue;
            }
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid numeric amount.");
            }
        }
    }

    /**
     * Helper method to read a non-empty string input.
     */
    private static String getStringInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextLine()) {
                System.exit(0);
            }
            String input = scanner.nextLine().replace("\uFEFF", "").trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }
}
