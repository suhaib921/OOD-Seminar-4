package se.kth.iv1350.seminar4.integration;

import se.kth.iv1350.seminar4.dto.SaleDTO;
/**
 * This class represents the external accounting system. 
 * It is responsible for updating the accounting records with new sales data.
 */
public class AccountingSystem {

    /**
     * Creates an instance of the AccountingSystem. 
     * Currently, it does not perform any initialization tasks.
     */
    public AccountingSystem(){


    }


/**
     * Sends sale information to the accounting system. This method is used to update the accounting records
     * with new sale data. It prints out a confirmation to simulate the interaction with an external accounting system.
     * In a real application, this method would likely send data to an actual accounting service or database.
     *
     * @param saleDTO The data transfer object containing details of the sale which includes items sold,
     *                total cost, and other relevant sale information.
     */
    public void sendSaleInfo(SaleDTO saleDTO) {
        // Simulation of sending the sale information to an external accounting system
        System.out.println("\n-------------------Updating Accounting System------------------------");
        System.out.println("Sent sale information to Accounting System for accounting");
    }
    
}
