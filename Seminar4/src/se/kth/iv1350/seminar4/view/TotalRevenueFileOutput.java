package se.kth.iv1350.seminar4.view;

import se.kth.iv1350.seminar4.modell.SaleObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

/**
 * Outputs the total revenue to a file.
 */
public class TotalRevenueFileOutput implements SaleObserver {
    private PrintWriter file;
    private static final String REVENUE_FILE = "Total_Revenue.txt";
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private int saleNumber = 1;

    /**
     * Creates a new instance that writes total revenue to a file.
     */
    public TotalRevenueFileOutput() {
        try {
            file = new PrintWriter(new FileWriter(REVENUE_FILE), true);
        } catch (IOException excp) {
            System.err.println("Error: Cannot create log file.");
            excp.printStackTrace();
        }
    }

    /**
     * Outprints the log order of sales, current total price and  the total revenue to the file.
     * 
     * @param recentTotalPrice The total price of the recent sale.
     * @param totalRevenue The cumulative total revenue pf all sales since program started.
     */
    @Override
    public void totalRevenue(double recentTotalPrice, double totalRevenue) {
         String logEntry = String.format("Sale number %d: Total price of the sale number = %s, Current Total revenue = %s",
                saleNumber, df.format(recentTotalPrice), df.format(totalRevenue));
        
        System.out.println(logEntry);
        file.println(logEntry);
        saleNumber++;
    }

      /**
     * Closes the PrintWriter to release resources.
     */
    public void close() {
        if (file != null) {
            file.close();
        }
    }
}
