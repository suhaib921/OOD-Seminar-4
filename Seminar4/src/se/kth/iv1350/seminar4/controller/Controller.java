package se.kth.iv1350.seminar4.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import se.kth.iv1350.seminar4.dto.ItemDTO;
import se.kth.iv1350.seminar4.dto.SaleDTO;
import se.kth.iv1350.seminar4.integration.AccountingSystem;
import se.kth.iv1350.seminar4.integration.DatabaseServerNotRunningException;
import se.kth.iv1350.seminar4.integration.DiscountRegister;
import se.kth.iv1350.seminar4.integration.InventorySystem;
import se.kth.iv1350.seminar4.integration.NoSuchItemFoundException;
import se.kth.iv1350.seminar4.integration.Printer;
import se.kth.iv1350.seminar4.modell.Payment;
import se.kth.iv1350.seminar4.modell.Receipt;
import se.kth.iv1350.seminar4.modell.Sale;
import se.kth.iv1350.seminar4.modell.SaleLog;
import se.kth.iv1350.seminar4.modell.SaleObserver;
import se.kth.iv1350.seminar4.util.ErrorLogger;
import se.kth.iv1350.seminar4.util.Logger;






/**
 * The Controller class acts as the mediator between the view and the model.
 * It handles user actions, fetches data from the model, and determines the response for the view.
 */
public class Controller {

    private AccountingSystem accSys;
    private DiscountRegister discountReg;
    private InventorySystem invSys;
    private Printer printer;
    private Sale sale;
    private Logger logger;
    private SaleLog saleLog;
    private ArrayList<SaleObserver> saleObservers = new ArrayList<>();
    
 
    private double currentTotalPriceBeforeDiscount;

    

    // Data transfer objects
    private SaleDTO saleDTO;
    private ItemDTO itemDTO;



    /**
     * Creates a new instance, initializing all external system handlers and the logger.
     */
    public Controller() {
        accSys = new AccountingSystem();
        discountReg = new DiscountRegister();
        invSys = new InventorySystem();
        printer = new Printer();
        logger = new ErrorLogger();
        saleLog= new SaleLog(invSys, accSys);
    }

    /**
     * Starts a new sale transaction by initializing a Sale object.
     */
    public void startSale() {
        sale = new Sale();
        saleDTO = new SaleDTO(sale);  // Ensure this line is correct

    }
    
     /**
     * Searches for matching item in the sale
     * If the item is not found, it fetches the itemDTO from the inventory system and adds it to the sale with the given quantity.
     * Otherwise the item founded DTO, increases with givem quantity
     * @param itemID The identifier of the item to add.
     * @param quantity The quantity of the item.
     * @return The ItemDTO of the added item.
     * @throws NoSuchItemFoundException If the item is not found in the inventory.
     * @throws DatabaseException If there is a simulated database failure.
     */
    public ItemDTO scanItem(int itemID, int quantity) throws NoSuchItemFoundException, DatabaseServerNotRunningException{
    
        ItemDTO itemDTO = sale.itemAlreadyInSale(itemID);
        if (itemDTO != null) {
           sale.increaseItemQuantity(itemDTO, quantity);
           return itemDTO;
        }
        try {
            itemDTO = invSys.fetchIteminfo(itemID);
            sale.addItem(itemDTO, quantity);
            
            return itemDTO;

        //  System.out.println(sale.getPurchasedItems(););
            
       
        } 
        catch(SQLException exc){ 
            System.out.println(" print print ");
            logger.logError("Access to database server for inventory failed while searching for item: " + itemID, exc);
            throw new DatabaseServerNotRunningException("ERROR: Connection to the inventory server has failed");
        }
        
        
    }

  
    /**
     * Ends the current sale process.
     * @return The calculated total price including tax.
     */
    public double endSale() {
        currentTotalPriceBeforeDiscount = sale.getCurrentTotalPrice();
        return currentTotalPriceBeforeDiscount;
    }

     /**
     * Processes payment for the current sale, applies any available discounts,
     * prints the receipt, and updates external systems with the sale information.
     *
     * @param amountPaid the amount paid by the customer
     * @param paymentMethod the method used for payment
     */
    public void pay(double amountPaid, double discount, String paymentMethod) {
                
        Payment payment = new Payment(amountPaid, currentTotalPriceBeforeDiscount - discount, paymentMethod);
        Receipt receipt = new Receipt(payment, sale);
        printer.print(receipt);

       
    } 

    public double requestDiscount(int customerId) {
        double discount = discountReg.fetchDiscountFromRegister(customerId, saleDTO, currentTotalPriceBeforeDiscount);
        System.out.println("Discount2: " + discount);
        return discount;

    }

 
    
    /**
     * Passes a <code>SaleObserver</code> through addObserver method of saleLog 
     * @param observer the observer that gets added
     */
    public void addSaleObserver(SaleObserver observer) {
        saleLog.addObserver(observer);
    }

    
}
