package se.kth.iv1350.seminar4.view;

import se.kth.iv1350.seminar4.modell.SaleObserver;
import java.text.DecimalFormat;

/**
 * Displays the total revenue in the user interface.
 */
public class TotalRevenueView implements SaleObserver {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

    /**
     * Prints the recent sale income and total revenue to the console.
     *
     * @param recentTotalPrice The total price of the recent sale.
     * @param totalRevenue The cumulative total revenue since the program started.
     */
    @Override
    public void totalRevenue(double recentTotalPrice, double totalRevenue) {
      StringBuilder revenueReport = new StringBuilder();
        revenueReport.append("************ ANOTHER VIEW ************\n")
                      .append("Recent sale income: ").append(DECIMAL_FORMAT.format(recentTotalPrice)).append("\n")
                      .append("The current total revenue is: ").append(DECIMAL_FORMAT.format(totalRevenue)).append("\n")
                      .append("************************************\n");

        System.out.print(revenueReport.toString());
    }
}
