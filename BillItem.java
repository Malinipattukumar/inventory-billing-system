/**
 * BillItem.java
 * Represents an item added to a customer's bill during a transaction.
 */
public class BillItem {
    private Item item;
    private int quantity;
    private double unitPrice;
    private double totalPrice;

    // Constructor to initialize a billed item
    public BillItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
        this.unitPrice = item.getPrice();
        this.totalPrice = this.unitPrice * quantity;
    }

    // Getters
    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalPrice = this.unitPrice * quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getItemName() {
        return item.getName();
    }

    public int getItemId() {
        return item.getId();
    }
}
