package se.kth.iv1350.seminar4.controller;

import se.kth.iv1350.seminar4.dto.ItemDTO;
import se.kth.iv1350.seminar4.dto.SaleDTO;
import se.kth.iv1350.seminar4.integration.AccountingSystem;
import se.kth.iv1350.seminar4.integration.DiscountRegister;
import se.kth.iv1350.seminar4.integration.InventorySystem;
import se.kth.iv1350.seminar4.integration.Printer;
import se.kth.iv1350.seminar4.modell.Payment;
import se.kth.iv1350.seminar4.modell.Receipt;
import se.kth.iv1350.seminar4.modell.Sale;




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
    

    // Data transfer objects
    private SaleDTO saleDTO;
    private ItemDTO itemDTO;



    /**
     * Creates a new instance, initializing all external system handlers.
     */
    public Controller() {
        accSys = new AccountingSystem();
        discountReg = new DiscountRegister();
        invSys = new InventorySystem();
        printer = new Printer();
    }

    /**
     * Starts a new sale transaction by initializing a Sale object.
     */
    public void startSale() {
        sale = new Sale();
    }
    
     /**
     * Searches for matching item in the sale
     * If the item is not found, it fetches the itemDTO from the inventory system and adds it to the sale with the given quantity.
     * Otherwise the item founded DTO, increases with givem quantity
     * @param itemID The identifier of the item to add.
     * @param quantity The quantity of the item.
     * @return The ItemDTO of the added item.
     */
    public ItemDTO scanItem(int itemID, int quantity) { // the quantity becomes issue here 
        ItemDTO itemDTO = sale.itemAlreadyInSale(itemID);
        if (itemDTO == null) {
            itemDTO = invSys.fetchIteminfo(itemID);
        }
        sale.addItem(itemDTO, quantity);
        return itemDTO;
    }

     /**
     * Overloaded method to handle scanning an item with a default quantity of one.
     *
     * @param itemID The identifier of the item to add.
     * @return The ItemDTO of the added item.
     */
    public ItemDTO scanItem(int itemID) {
        return scanItem(itemID, 1);
    }
    /**
     * Ends the current sale process.
     * @return The calculated total price including tax.
     */
    public double endSale() {
        double currentTotalPrice = sale.getCurrentTotalPrice();
        return currentTotalPrice;
    }

     /**
     * Processes payment for the current sale, applies any available discounts,
     * prints the receipt, and updates external systems with the sale information.
     *
     * @param amountPaid the amount paid by the customer
     * @param paymentMethod the method used for payment
     */
    public void pay(double amountPaid, double discount, String paymentMethod) {
        
        double TotalPriceAfterDiscountApplied = sale.getCurrentTotalPrice() - discount;
        
        Payment payment = new Payment(amountPaid, TotalPriceAfterDiscountApplied, paymentMethod);
        Receipt receipt = new Receipt(payment, sale);
        printer.print(receipt);
        updateExternalSystems(saleDTO);
    } 

    public double requestDiscount(int customerId) {
        saleDTO = new SaleDTO(sale);
        double totalPrice = sale.getCurrentTotalPrice();
        return discountReg.fetchDiscountFromRegister(customerId, saleDTO, totalPrice);
    }

     /**
     * Updates external systems with the details of the current sale.
     * Sends sale information to the inventory and accounting systems for processing.
     */
    private void updateExternalSystems(SaleDTO saleDTO) {
        invSys.sendSaleInfo(saleDTO);
        accSys.sendSaleInfo(saleDTO);
    }

    
}
