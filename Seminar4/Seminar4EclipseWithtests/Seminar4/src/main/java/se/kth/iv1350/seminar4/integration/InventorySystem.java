package se.kth.iv1350.seminar4.integration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.seminar4.dto.ItemDTO;
import se.kth.iv1350.seminar4.dto.SaleDTO;

/**
 * This class represents the external inventory system.
 * It is responsible for managing inventory items and updating their quantities based on sales.
 */
public class InventorySystem {
    
    private List<ItemDTO> items = new ArrayList<>();

    /**
     * Creates an instance of the InventorySystem.
     * Initializes the inventory with a predefined set of items.
     */
    public InventorySystem() {
        this.items = new ArrayList<>();
        addItemsToStore();
    }

    /**
     * Initializes the store with a predefined set of items.
     */
    private void addItemsToStore() {
        items.add(new ItemDTO("Orange Juice", 1, 23.5, 0.25, 50));
        items.add(new ItemDTO("Orange", 2, 12.5, 0.25, 100));
        items.add(new ItemDTO("Apple", 3, 3.7, 0.12, 80));
        items.add(new ItemDTO("Banana", 4, 4.3, 0.12, 120));
        items.add(new ItemDTO("Grapes", 5, 4.99, 0.18, 30));
        items.add(new ItemDTO("Milk", 6, 15.99, 0.07, 200));
        items.add(new ItemDTO("Bread", 7, 24.5, 0.06, 150));
        items.add(new ItemDTO("Butter", 8, 67.5, 0.12, 60));
        items.add(new ItemDTO("Cheese", 9, 53.49, 0.12, 40));
        items.add(new ItemDTO("Yogurt", 10, 35.99, 0.07, 90));
        items.add(new ItemDTO("Onion", 11, 9.99, 0.07, 90));
        items.add(new ItemDTO("Potato", 12, 11.99, 0.07, 90));
    }

    /**
     * Fetches item information based on the item ID.
     * Throws SQLException for item ID 10 to simulate a database issue.
     *
     * @param itemID The ID of the item to fetch.
     * @return The ItemDTO if found, otherwise null.
     * @throws SQLException if itemID is X, simulating a database failure.
     */
    public ItemDTO fetchIteminfo(int itemID) throws SQLException {
        if (itemID == 12) {
            throw new SQLException();
        }

        for (ItemDTO item : items) {
            if (item.getItemID() == itemID) {
                return new ItemDTO(item); // Return a copy to prevent external modification
            }
        }

         // If no item is found and the loop completes, throw the NoSuchItemFoundException
        //throw new NoSuchItemFoundException("ERROR: Invalid item ID: " + itemID);
        return null;

    }

    /**
     * Processes the details of a sale to update the inventory quantities.
     * 
     * @param saleDTO The sale data transfer object containing details of the sale.
     */
    public void sendSaleInfo(SaleDTO saleDTO) {
               
    }
}
