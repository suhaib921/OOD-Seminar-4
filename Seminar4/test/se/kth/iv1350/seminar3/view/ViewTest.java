import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ViewTest {

    @Test
    public void testDisplayPaymentInfo() {
        View view = new View();
        String expectedOutput = "Payment info displayed";
        assertEquals(expectedOutput, view.displayPaymentInfo());
    }

    @Test
    public void testDisplayRunTimeException() {
        View view = new View();
        String expectedOutput = "Runtime exception occurred";
        assertEquals(expectedOutput, view.displayRunTimeException());
    }

    @Test
    public void testDisplayChange() {
        View view = new View();
        int amount = 10;
        String expectedOutput = "Change: 10 SEK";
        assertEquals(expectedOutput, view.displayChange(amount));
    }

    @Test
    public void testDisplayTotalCost() {
        View view = new View();
        int totalCost = 100;
        String expectedOutput = "Total cost: 100 SEK";
        assertEquals(expectedOutput, view.displayTotalCost(totalCost));
    }

}
