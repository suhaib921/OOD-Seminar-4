package se.kth.iv1350.seminar4.integration;

import se.kth.iv1350.seminar4.modell.Receipt;

public class Printer {
    
    public Printer(){

    }
     
    public void print(Receipt receipt) {
        System.out.println(receipt.receiptPaperFormat());
    }        
}
