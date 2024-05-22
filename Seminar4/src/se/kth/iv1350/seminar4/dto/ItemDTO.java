package se.kth.iv1350.seminar4.dto;

public class ItemDTO {
    private String itemName;
    private int itemID;
    private double itemPrice;
    private double itemVAT;
    private int quantity;

    public ItemDTO(String itemName, int itemID, double itemPrice, double itemVAT, int quantity) {
        this.itemName = itemName;
        this.itemID = itemID;
        this.itemPrice = itemPrice;
        this.itemVAT = itemVAT;
        this.quantity = quantity;
    }

    /** @return The unique identifier of the item. */
    public int getItemID() {
        return itemID;
    }

    /** @return The name of the item. */
    public String getItemName() {
        return itemName;
    }

    /** @return The price of the item. */
    public double getItemPrice() {
        return itemPrice;
    }

    /** @return The VAT rate of the item.*/
    public double getItemVAT() {
        return itemVAT;
    }

    /** @return The quantity of the item. */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the item.
     *
     * @param quantity The new quantity of the item.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Increases the quantity of the item.
     *
     * @param amount The amount to increase the quantity by.
     */
    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }

    /**
     * Decreases the quantity of the item.
     *
     * @param amount The amount to decrease the quantity by.
     */
    public void decreaseQuantity(int amount) {
        this.quantity -= amount;
    }
}
