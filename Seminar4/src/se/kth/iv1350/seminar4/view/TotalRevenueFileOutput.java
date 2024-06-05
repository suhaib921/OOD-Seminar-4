package se.kth.iv1350.seminar4.view;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import se.kth.iv1350.seminar4.modell.SaleObserver;

public class TotalRevenueFileOutput implements SaleObserver {
    private PrintWriter file;
    private static final String REVENUE_FILE = "Total_Revenue.txt";
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private int saleNumber = 1;

    public TotalRevenueFileOutput() {
        try {
            file = new PrintWriter(new FileWriter(REVENUE_FILE, true));
            System.out.println("File opened successfully.");
        } catch (IOException e) {
            System.err.println("Failed to open file: " + REVENUE_FILE);
            e.printStackTrace();
        }
    }

    @Override
    public void totalRevenue(double recentTotalPrice, double totalRevenue) {
        if (file != null) {
            String logEntry = String.format("Sale number %d: %s, Current Total: %s",
                                            saleNumber++, df.format(recentTotalPrice), df.format(totalRevenue));
            System.out.println("Writing to file: " + logEntry);
            file.println(logEntry);
            file.flush();
        } else {
            System.err.println("PrintWriter not initialized.");
        }
    }

    public void close() {
        if (file != null) {
            file.close();
            System.out.println("File closed successfully.");
        }
    }
}
