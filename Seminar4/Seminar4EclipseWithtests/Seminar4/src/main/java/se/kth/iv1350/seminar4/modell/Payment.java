package se.kth.iv1350.seminar4.modell;

/**
 * Represents a payment transaction within the sales system.
 * This class manages the calculation of change and stores details about the payment made.
 */
public class Payment {
    private double totalSaleAmount;
    private double amountPaidByCustomer;
    private String methodOfPayment;

    /**
     * Constructs a new Payment instance.
     *
     * @param amountPaidByCustomer The amount of money paid by the customer.
     * @param totalSaleAmount The total amount of the sale that needs to be paid.
     * @param methodOfPayment The method of payment used (e.g., cash, credit card).
     */
    public Payment(double amountPaidByCustomer, double totalSaleAmount, String methodOfPayment) {
        this.amountPaidByCustomer = amountPaidByCustomer;
        this.totalSaleAmount = totalSaleAmount;
        this.methodOfPayment = methodOfPayment;
    }

    /**
     * Calculates the change to be returned to the customer.
     *
     * @param amountPaid The amount of money paid by the customer.
     * @param totalSaleAmount The total sale amount that was due.
     * @return The calculated change that should be returned to the customer.
     */
    private double calculateChange(double amountPaid, double totalSaleAmount) {
        return amountPaid - totalSaleAmount;
    }

    /**
     * Retrieves the calculated change for the customer based on the amount paid and the total sale amount.
     *
     * @return The amount of change to be given back to the customer.
     */
    public double getCustomerChange() {
        return calculateChange(this.amountPaidByCustomer, this.totalSaleAmount);
    }

    /**
     * Retrieves the payment method used by the customer.
     *
     * @return A string describing the payment method.
     */
    public String getMethodOfPayment() {
        return this.methodOfPayment;
    }

    /**
     * Gets the amount paid by the customer.
     *
     * @return The amount paid.
     */
    public double getAmountPaid() {
        return this.amountPaidByCustomer;
    }

    /**
     * Gets the total sale amount that the payment covers.
     *
     * @return The total sale amount.
     */
    public double getTotalSaleAmount() {
        return this.totalSaleAmount;
    }
}
