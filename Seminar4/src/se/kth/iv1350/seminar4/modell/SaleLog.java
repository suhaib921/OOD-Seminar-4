package se.kth.iv1350.seminar4.modell;
import  se.kth.iv1350.seminar4.integration.InventorySystem;
import  se.kth.iv1350.seminar4.integration.AccountingSystem;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * The Sale Log of the program
 */
public class SaleLog {
    private List<Sale> saleLog = new ArrayList<>();
    private InventorySystem invSys;
    private AccountingSystem accSys;
    private Sale recentSale;
    private double totalRevenue;
    private List<SaleObserver> saleObservers = new ArrayList<>();
    
    /**
     * Creates a new instance
     * @param invSys reference to the inventory system
     * @param accSys reference to the accounting system
     */
    public SaleLog(InventorySystem invSys, AccountingSystem accSys){
        this.invSys = invSys;
        this.accSys = accSys;
        
    }
    
    /**
     * Program logs the completed sale in a list which contains
     * all information about each sale, the {@link Sale} object gives access to 
     * all available information about the sale. 
     * @param sale the Sale object gives access to all available 
     * information about the sale.
     */
    public void logCompletedSale(Sale sale){
        saleLog.add(sale);
        totalRevenue = totalRevenue + sale.getCurrentTotalPrice();
        recentSale = sale;
        notifyObservers();
        updateExternalSystems();
    }
    
    /**
     * Adds a <code>SaleObserver</code> to the list of SaleObservers
     * That is a list of objects that observes the sales
     * @param obs the observer that gets added
     */
    public void addObserver(SaleObserver obs){
        saleObservers.add(obs);
    }
    
    
    private void notifyObservers(){
        for(SaleObserver observer : saleObservers){
            observer.totalRevenue(recentSale.getCurrentTotalPrice(), totalRevenue);
        }    
    }
    
  
      /**
     * Updates external systems with the details of the current sale.
     * Sends sale information to the inventory and accounting systems for processing.
     */
    private void updateExternalSystems() {
        invSys.sendSaleInfo(recentSale);
        accSys.sendSaleInfo(recentSale);
    }
}
