package se.kth.iv1350.seminar4.modell;

import java.util.Random;

import se.kth.iv1350.seminar4.dto.ItemDTO;

import java.util.ArrayList;


public class Sale {
    private ArrayList<ItemDTO> purchased;
    private double runningCurrentTotalPrice;
    private int saleID;

    /**
     * Constructs a Sale with a unique ID and initializes it.
     */
    public Sale() {
        this.saleID = generateRandomSaleID();
        this.purchased = new ArrayList<>();
        this.runningCurrentTotalPrice = 0; // Whenever a new sales is started, the total price is set to 0.
    }

    /**
     * Generates a random, non-negative Sale ID.
     * @return Randomly generated integer.
     */
    private int generateRandomSaleID() {
        Random random = new Random();
        return random.nextInt(Integer.MAX_VALUE);
    }

     /**
     * Finds an item in the sale based on the item ID.
     * Increases the quantity of the item if it is found.
     *
     * @param itemID The ID of the item to find.
     * @return The found ItemDTO, or null if not found.
     */
    public ItemDTO itemAlreadyInSale(int itemID) {
        for (ItemDTO item : purchased) {
            if (item.getItemID() == itemID) {
                return item;
            }
        }
        return null;
    }

     /**
     * Adds a specified quantity of a new item to the purchased list.
     * @param itemDTO The item to add or update.
     * @param quantity The quantity of the item.
     */
    public void addItem(ItemDTO itemDTO, int quantity) {
        purchased.add(itemDTO); // We don't need to add the itemDTO again in to the purchased list, just increase quantity.
        increaseItemQuantity(itemDTO, quantity);
    }

    /**
     * Adds a specified quantity of a new item to the purchased list.
     * @param itemDTO The item to add or update.
     * @param quantity The quantity of the item.
     */
    public void increaseItemQuantity(ItemDTO itemDTO, int quantity) {
        itemDTO.setQuantity(quantity);
        updateTotalPrice();
    }

    /**
     * Recalculates the total price of the sale based on the items and their quantities.
     */
    private void updateTotalPrice() {
        runningCurrentTotalPrice = 0;
        for (ItemDTO item : purchased) {
            runningCurrentTotalPrice += item.getItemPrice() * item.getQuantity();
        }
    }

     /**
     * Retrieves the list of items purchased.
     * @return List of items.
     */
    public ArrayList<ItemDTO> getPurchasedItems() {
        return purchased;
    }
    /**
     * @return unique Sale ID.
     */
    public int getSaleID() {
        return saleID;
    }
     /**
     * @return runningCurrentTotalPrice
     */

    public double getCurrentTotalPrice() {
        return runningCurrentTotalPrice;
    } 
}
