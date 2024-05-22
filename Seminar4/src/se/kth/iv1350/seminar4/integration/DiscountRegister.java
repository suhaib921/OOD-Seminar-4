package se.kth.iv1350.seminar4.integration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.kth.iv1350.seminar4.dto.ItemDTO;
import se.kth.iv1350.seminar4.dto.SaleDTO;


/**
 * Manages discount information and calculations for sales.
 * Handles different types of discounts including item-specific, customer-specific, and volume-based discounts.
 */

public class DiscountRegister {
    private Map<Integer, Double> customerDiscounts; // Maps customerID to percentage discount
    private Map<Integer, Double> itemsDiscounts; // Maps itemID to percentage discount
    private List<DiscountLevel> discountLevels; // Holds discount levels based on total price

    public DiscountRegister() {
        itemsDiscounts = new HashMap<>();
        customerDiscounts = new HashMap<>();
        discountLevels = new ArrayList<>();
        initializeItemsWithDiscount();
        initializeCustomerDiscounts();
        initializeDiscountLevels();
    }

    // Initializes predefined item-specific discounts.
    private void initializeItemsWithDiscount() {
        itemsDiscounts.put(1, 10.0);
        itemsDiscounts.put(2, 5.0);
        itemsDiscounts.put(3, 7.0);
        itemsDiscounts.put(4, 3.0);
        itemsDiscounts.put(5, 2.0);
    }

    // Initializes predefined customer-specific discounts.
    private void initializeCustomerDiscounts() {
        customerDiscounts.put(1111, 10.0);
        customerDiscounts.put(2222, 15.0);
        customerDiscounts.put(3333, 20.0);
        customerDiscounts.put(4444, 25.0);
        customerDiscounts.put(5555, 30.0);
    }

    // Initializes discount levels based on total purchase thresholds.
    private void initializeDiscountLevels() {
        discountLevels.add(new DiscountLevel(50.0, 5.0));
        discountLevels.add(new DiscountLevel(100.0, 10.0));
        discountLevels.add(new DiscountLevel(200.0, 15.0));
    }

    /**
     * Fetches the discount details based on customer ID, items purchased, and total price.
     * 
     * @param customerId The customer ID.
     * @param saleDTO The sale data transfer object.
     * @param currentTotalPrice The current total price of the sale.
     * @return The total discount amount.
     */
    public double fetchDiscountFromRegister(int customerId, SaleDTO saleDTO, double currentTotalPrice) {
        double totalItemDiscount = applyItemSpecificDiscounts(saleDTO);
        double customerDiscountRate = customerDiscounts.getOrDefault(customerId, 0.0);
        double fixedDiscount = applyFixedDiscountBasedOnTotalPrice(currentTotalPrice);

        double totalDiscountAmountTosubstract =  totalItemDiscount + customerDiscountRate + fixedDiscount;
    
        return totalDiscountAmountTosubstract;
    }

    /**
     * Applies item-specific percentage discounts to the current total price.
     * 
     * @param saleDTO The sale data transfer object.
     * @return The total discount amount for items.
     */
    private double applyItemSpecificDiscounts(SaleDTO saleDTO) {
        double totalItemDiscount = 0.0;
        List<ItemDTO> purchasedItems = saleDTO.getTheListOfPurchasedItems();
        if (purchasedItems != null) {
            for (ItemDTO item : purchasedItems) {
                double itemDiscountRate = itemsDiscounts.getOrDefault(item.getItemID(), 0.0);
                if (itemDiscountRate > 0) {
                    double itemDiscount = item.getItemPrice() * item.getQuantity() * (itemDiscountRate / 100.0);
                    totalItemDiscount += itemDiscount;
                }
            }
        }
        return totalItemDiscount;
    }

    /**
     * Applies a fixed discount based on predefined levels of total price.
     * 
     * @param currentTotalPrice The current total price after other discounts.
     * @return The fixed discount amount.
     */
    private double applyFixedDiscountBasedOnTotalPrice(double currentTotalPrice) {
        double discountAmount = 0.0;
        for (DiscountLevel level : discountLevels) {
            if (currentTotalPrice >= level.threshold) {
                discountAmount = level.discountAmount;
            }
        }
        return discountAmount;
    }


    /**
     * Inner class to represent discount levels.
     */
    private static class DiscountLevel {
        double threshold;
        double discountAmount;

        DiscountLevel(double threshold, double discountAmount) {
            this.threshold = threshold;
            this.discountAmount = discountAmount;
        }
    }
}
