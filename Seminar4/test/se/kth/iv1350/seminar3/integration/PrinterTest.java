import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PrinterTest {

    @Test
    void testPrintOrder() {
        Printer printer = new Printer();
        String expectedOutput = "Printing order: 123";
        assertEquals(expectedOutput, printer.printOrder(123));
    }

    @Test
    void testPrintOrderWithNegativeValue() {
        Printer printer = new Printer();
        String expectedOutput = "Invalid order number: -123";
        assertEquals(expectedOutput, printer.printOrder(-123));
    }

    @Test
    void testPrintOrderWithZero() {
        Printer printer = new Printer();
        String expectedOutput = "Invalid order number: 0";
        assertEquals(expectedOutput, printer.printOrder(0));
    }

    @Test
    void testPrintPayment() {
        Printer printer = new Printer();
        String expectedOutput = "Printing payment: 123.45";
        assertEquals(expectedOutput, printer.printPayment(123.45));
    }

    @Test
    void testPrintPaymentWithNegativeValue() {
        Printer printer = new Printer();
        String expectedOutput = "Invalid payment amount: -123.45";
        assertEquals(expectedOutput, printer.printPayment(-123.45));
    }

    @Test
    void testPrintPaymentWithZero() {
        Printer printer = new Printer();
        String expectedOutput = "Invalid payment amount: 0.0";
        assertEquals(expectedOutput, printer.printPayment(0.0));
    }
}
