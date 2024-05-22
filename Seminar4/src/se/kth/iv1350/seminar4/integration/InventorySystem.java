package se.kth.iv1350.seminar4.integration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.kth.iv1350.seminar4.dto.ItemDTO;
import se.kth.iv1350.seminar4.dto.SaleDTO;

import java.sql.SQLException;


public class InventorySystem {
    
    private List<ItemDTO> items = new ArrayList<>();

    public InventorySystem(){
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
        items.add(new ItemDTO("Onion", 10, 9.99, 0.07, 90));
        items.add(new ItemDTO("Potatis", 10, 11.99, 0.07, 90));
    }
    

  /**
     * Fetches item information based on the item ID.
     * 
     * @param itemID The ID of the item to fetch.
     * @return The ItemDTO if found, otherwise null.
     */
    public ItemDTO fetchIteminfo(int itemID)throws SQLException{
        for(ItemDTO item : items){
            if(item.getItemID() == itemID){
                return item; // Return a copy to prevent external modification
            }
        }
        return null;
    }



    /**
     * Processes the details of a sale to update the inventory quantities.
     * 
     * @param saleDTO The sale data transfer object containing details of the sale.
     */
    public void sendSaleInfo(SaleDTO saleDTO) {
        updateInventoryBasedOnSale(saleDTO);
    }

    /**
     * Updates the inventory quantities based on the sale details.
     * 
     * @param saleDTO The sale data transfer object containing details of the sale.
     */
    private void updateInventoryBasedOnSale(SaleDTO saleDTO) {
        Map<Integer, Integer> itemCounts = new HashMap<>();
        for (ItemDTO item : saleDTO.getTheListOfPurchasedItems()) {
            int itemID = item.getItemID();
            itemCounts.put(itemID, itemCounts.getOrDefault(itemID, 0) + item.getQuantity());
        }
        decreaseInventoryQuantities(itemCounts);
    }
 /**
     * Decreases the inventory quantities for each item based on the sale.
     * 
     * @param itemCounts A map containing item IDs and their respective quantities to be decremented.
     */
    private void decreaseInventoryQuantities(Map<Integer, Integer> itemCounts) {
        System.out.println("-------------------Updating External Inventory System------------------------");
        for (Map.Entry<Integer, Integer> entry : itemCounts.entrySet()) {
            int itemID = entry.getKey();
            int quantityToDecrease = entry.getValue();
            ItemDTO item = fetchIteminfo(itemID);
            if (item != null) {
                item.decreaseQuantity(quantityToDecrease);
                System.out.println("Decreased inventory quantity of item with ID " + itemID + " by " + quantityToDecrease + " units.");
            }
        }
    }
    /**
     * Provides a safe read-only list of all inventory items.
     * 
     * @return A list of ItemDTO objects representing the inventory.
     */
    public List<ItemDTO> getItemList() {
        return new ArrayList<>(items);
    }
}  
