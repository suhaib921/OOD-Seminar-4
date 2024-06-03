package se.kth.iv1350.seminar4.view;

import java.text.DecimalFormat;
import se.kth.iv1350.seminar4.controller.Controller;
import se.kth.iv1350.seminar4.dto.ItemDTO;
import se.kth.iv1350.seminar4.integration.DatabaseServerNotRunningException;
import se.kth.iv1350.seminar4.integration.NoSuchItemFoundException;

/**
 * This class is a placeholder for the user interface.
 */
public class View {
    private Controller contr;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public View(Controller contr){
        this.contr = contr;
        contr.addSaleObserver(new TotalRevenueFileOutput());
        contr.addSaleObserver(new TotalRevenueView());
    }

    /**
     * Simulates a presentation of an item that 
     * @param foundItem The item that has been added to the sale.
     */
    private void presentItem(ItemDTO foundItem) {
        
            System.out.println("Added item: " + foundItem.getItemName() + ", quantity: " 
                               + foundItem.getQuantity() + ", Price per unit: " + df.format(foundItem.getItemPrice()));
    
    }


      /**
     * Simulates a cashier scanning an item
     * Item id 9 simulates a scenario where the item id is not in inventorysystem
     * Item id 10 simulates a scenario where the server crash

     * @param itemId 
     */
    private void chooseItem(int itemId, int quantity) {
        try {
             // Simulate adding items
            System.out.println("Adding items to the sale...");
            ItemDTO foundItem = contr.scanItem(itemId, quantity);
            System.out.println("Item has been found: "+  foundItem);
            presentItem(foundItem);
            System.out.println();
        }
        
        catch(NoSuchItemFoundException exc){
           
            System.out.println("Invalid item identifier: " + itemId);
            System.out.println();
        }
        
        catch(DatabaseServerNotRunningException exc) {
            System.out.println("There has been a problem, lost connection to the server");
            System.out.println("Could not search for item id: " + itemId + "\n");
           
        }
        
    }

    /**
     * Simulates user adding items, requesting discounts, and making a payment.
     */
    public void runFakeExecution() {
        
            System.out.println("Starting a new sale...");
            contr.startSale();


            chooseItem(5, 2);
            chooseItem(8,5);
            chooseItem(3,3);
            chooseItem(3,3);
            chooseItem(6,2);
            chooseItem(2,1);
            chooseItem(1,3);
            chooseItem(4,3);
            chooseItem(1,3);
            chooseItem(12,4);


            // End the sale and show total
            double totalPrice = contr.endSale();
            System.out.println("Sale ended.\n Total price: " + df.format(totalPrice));

            // Simulate discount request
            boolean customerRequestsDiscount = true; // Simulated flag for requesting a discount
            int customerId = 1111; // Simulated customer ID
            if (customerRequestsDiscount) {
                double discount = contr.requestDiscount(customerId);
                System.out.println("Applied discount: " + df.format(discount));
                totalPrice -= discount; // Apply discount
            }

            // Process payment
            contr.pay(200, totalPrice, "Cash");
            System.out.println("Payment of " + df.format(200) + " received. Change given: " 
                               + df.format(200 - totalPrice));
       
    }
}
