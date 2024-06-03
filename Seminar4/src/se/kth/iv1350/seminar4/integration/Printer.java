package se.kth.iv1350.seminar4.integration;

import se.kth.iv1350.seminar4.modell.Receipt;

/**
 * 
 * The class representing the printer that is responsible for printing the receipts.
 */

public class Printer {
    
     /**
     * This class is a placeholder for the external printer system
     */
    public Printer(){

    }

     /**
     * Prints the formatted receipt.
     * @param receipt The receipt to be printed, which must not be null.
     * @return A string representing the formatted receipt.
     */
     
   /*public String print(Receipt receipt) {
        
        return receipt.receiptPaperFormat(); 
    }*/ 
    
    public void print(Receipt receipt) {
        System.out.println(receipt.receiptPaperFormat());
    } 
      
}
