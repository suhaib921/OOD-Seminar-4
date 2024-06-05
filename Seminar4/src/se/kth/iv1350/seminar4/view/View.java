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
    private void presentItem(ItemDTO foundItem, int quantity) {
        
            System.out.println("Added item: " + foundItem.getItemName() + ", quantity: " 
                               + quantity + ", Price per unit: " + df.format(foundItem.getItemPrice()) );
    
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
            ItemDTO foundItem = contr.scanItem(itemId, quantity);
            presentItem(foundItem, quantity);
        }
        
        catch(NoSuchItemFoundException exc){
           
            System.out.println("Invalid item identifier: " + itemId);
            System.out.println();
        }
        
        catch(DatabaseServerNotRunningException exc) {
            System.out.println("\n There has been a problem, lost connection to the server");
            System.out.println("Could not search for item id: " + itemId + "\n");
           
        }
        
    }

    /**
     * Simulates user adding items, requesting discounts, and making a payment.
     */
    public void runFakeExecution() {


        
        
            System.out.println("Starting a new sale...\n");
            contr.startSale();

            

            System.out.println("Adding items to the sale...\n");
            chooseItem(1, 2);
            chooseItem(2,5);
            chooseItem(3,5);
            chooseItem(5,5);

            chooseItem(12,5); 
            chooseItem(13,5);

    
           // chooseItem(12,4);


            // End the sale and show total
            double totalPrice = contr.endSale();
            System.out.println("Sale ended.\n Total price(Inc Vat): " + df.format(totalPrice));

            // Simulate discount request
            boolean customerRequestsDiscount = true; // Simulated flag for requesting a discount
            int customerId = 1111; // Simulated customer ID
            contr.requestDiscount(customerId);
            
           
            // Process payment
            contr.pay(500, totalPrice, "Cash");
            System.out.println("Payment of " + df.format(500) + " received. Change given: " 
                               + df.format(500-contr.requestDiscount(customerId)));


            System.out.println("\nInventory system has been updated\n");
            System.out.println("Accounting system has been updated\n");
         
       
    }
}
