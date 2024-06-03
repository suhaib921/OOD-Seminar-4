package se.kth.iv1350.seminar4.modell;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.seminar4.dto.SaleDTO;
import se.kth.iv1350.seminar4.integration.AccountingSystem;
import se.kth.iv1350.seminar4.integration.InventorySystem;

/**
 * The Sale Log of the program
 */
public class SaleLog {
    private List<SaleDTO> saleLog = new ArrayList<>();
    private InventorySystem invSys;
    private AccountingSystem accSys;
    private SaleDTO recentSale;
    private double totalRevenue;
    private List<SaleObserver> saleObservers = new ArrayList<>();

    /**
     * Creates a new instance
     * @param invSys reference to the inventory system
     * @param accSys reference to the accounting system
     */
    public SaleLog( InventorySystem invSys, AccountingSystem accSys) {

        this.invSys = invSys;
        this.accSys = accSys;
        
    }

    /**
     * Logs the completed sale in a list which contains all information about each sale.
     * The SaleDTO object gives access to all available information about the sale.
     * 
     * @param saleDTO the SaleDTO object giving access to all available information about the sale.
     */
     public void logCompletedSale(SaleDTO saleDTO) {
        saleLog.add(saleDTO);
        totalRevenue += saleDTO.getTheCurrentTotalPrice();
        recentSale = saleDTO;
        notifyObservers();
        updateExternalSystems();
    }

    /**
     * Adds a SaleObserver to the list of observers that monitor sales.
     * 
     * @param obs the observer to be added
     */
    public void addObserver(SaleObserver obs) {
     
        saleObservers.add(obs);
    }

    /**
     * Notifies observers about the total revenue.
     */
   private void notifyObservers() {
       
        for (SaleObserver observer : saleObservers) {
           
            observer.totalRevenue(recentSale.getTheCurrentTotalPrice(), totalRevenue);
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
