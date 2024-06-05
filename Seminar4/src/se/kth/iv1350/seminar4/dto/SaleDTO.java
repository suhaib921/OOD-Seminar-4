package se.kth.iv1350.seminar4.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import se.kth.iv1350.seminar4.modell.Sale;

/**
 * Data Transfer Object for Sale information.
 * This class is responsible for carrying data about the sale, including items purchased and total price,
 * without allowing changes to the original Sale object's state.
 */
public class SaleDTO {
    private final double snapshotOfTheCurrentTotalPrice;
    private final ArrayList<ItemDTO> snapshotOfPurchasedItems;
    private final int saleDTOID;

    /**
     * Constructs a SaleDTO by copying values from the Sale object.
     * This ensures that the SaleDTO contains a snapshot of the sale's state at the time of creation,
     * preventing any further changes to affect this data.
     * 
     * @param sale The Sale object to copy data from.
     */
    public SaleDTO(Sale sale) {
        this.snapshotOfTheCurrentTotalPrice = sale.getCurrentTotalPrice();
        this.snapshotOfPurchasedItems = copyItems(sale.getPurchasedItems());
        this.saleDTOID = sale.getSaleID();
    }

    /**
     * Creates a deep copy of the list of purchased items to ensure that the SaleDTO's list
     * is completely independent of the original list in the Sale object.
     * 
     * @param purchased The original list of purchased items.
     * @return A deep copy of the list of purchased items.
     */
    private ArrayList<ItemDTO> copyItems(ArrayList<ItemDTO> purchased) {
        ArrayList<ItemDTO> itemsCopy = new ArrayList<>(purchased.size());
        for (ItemDTO item : purchased) {
            itemsCopy.add(item);
        }
        return itemsCopy;
    }

    /**
     * Provides a safe, read-only view of the list of items that were part of the sale.
     * This method now returns an unmodifiable list to prevent external modifications.
     * 
     * @return An unmodifiable view of the list of ItemDTOs, protecting the internal state.
     */
    public List<ItemDTO> getTheListOfPurchasedItems() {
        return Collections.unmodifiableList(snapshotOfPurchasedItems);
    }

    /**
     * Gets the snapshot of the current total price at the time of DTO creation.
     * 
     * @return The total price of the sale when the DTO was created.
     */
    public double getTheCurrentTotalPrice() {
        return snapshotOfTheCurrentTotalPrice;
    }

    /**
     * Gets the unique identifier for the sale that this DTO represents.
     * 
     * @return The sale ID.
     */
    public int getSaleDTOID() {
        return saleDTOID;
    }

   
}

