import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaymentTest {
    private Payment payment;
    
    @BeforeEach
    public void setUp() {
        payment = new Payment();
    }
    
    @AfterEach
    public void tearDown() {
        payment = null;
    }
    
    @Test
    public void testPayAmount() {
        int amount = 100;
        boolean result = payment.payAmount(amount);
        assertTrue(result);
    }
    
    @Test
    public void testPayAmountNegative() {
        int amount = -50;
        boolean result = payment.payAmount(amount);
        assertFalse(result);
    }
    
    @Test
    public void testPayAmountZero() {
        int amount = 0;
        boolean result = payment.payAmount(amount);
        assertFalse(result);
    }
    
    @Test
    public void testPayAmountMax() {
        int amount = Integer.MAX_VALUE;
        boolean result = payment.payAmount(amount);
        assertTrue(result);
    }
    
    @Test
    public void testPayAmountMin() {
        int amount = Integer.MIN_VALUE;
        boolean result = payment.payAmount(amount);
        assertFalse(result);
    }
}
