/**
 * Item.java
 * Represents an individual product in the inventory.
 * Demonstrates Encapsulation with private fields and public getters/setters.
 */
public class Item {
    private int id;
    private String name;
    private double price;
    private int quantity;

    // Constructor to initialize an inventory item
    public Item(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters (Encapsulation)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Helper method to add stock
    public void addStock(int qty) {
        this.quantity += qty;
    }

    // Helper method to reduce stock
    public void reduceStock(int qty) {
        this.quantity -= qty;
    }

    @Override
    public String toString() {
        return String.format("Item ID: %d | Name: %s | Price: Rs. %.2f | Stock: %d", id, name, price, quantity);
    }
}
