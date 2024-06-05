package se.kth.iv1350.seminar4.modell;

/**
 * An interface for observers that monitor the income of a retail store.
 */
public interface SaleObserver {
    /**
     * Called when a sale is completed to notify observers about the recent sale income 
     * and the updated total revenue.
     *
     * @param recentTotalPrice The income from the most recent sale.
     * @param totalRevenue The updated total revenue since the program started.
     */
    void totalRevenue(double recentTotalPrice, double totalRevenue);
}
