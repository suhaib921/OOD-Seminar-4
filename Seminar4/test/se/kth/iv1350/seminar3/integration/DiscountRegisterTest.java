import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DiscountRegisterTest {
    private DiscountRegister discountRegister;
    
    @BeforeEach
    void setUp() {
        discountRegister = new DiscountRegister();
    }
    
    @AfterEach
    void tearDown() {
        discountRegister = null;
    }
    
    @Test
    void testAddDiscountWithValidDiscount() {
        int discountId = 1;
        String discountDescription = "Student Discount";
        double discountRate = 0.2;
        discountRegister.addDiscount(discountId, discountDescription, discountRate);
        assertTrue(discountRegister.getDiscounts().containsKey(discountId));
    }
    
    @Test
    void testAddDiscountWithDuplicateId() {
        int discountId = 1;
        String discountDescription1 = "Student Discount";
        double discountRate1 = 0.2;
        String discountDescription2 = "Employee Discount";
        double discountRate2 = 0.1;
        discountRegister.addDiscount(discountId, discountDescription1, discountRate1);
        assertThrows(IllegalArgumentException.class, () -> discountRegister.addDiscount(discountId, discountDescription2, discountRate2));
    }
    
    @Test
    void testRemoveDiscountWithValidId() {
        int discountId = 1;
        String discountDescription = "Student Discount";
        double discountRate = 0.2;
        discountRegister.addDiscount(discountId, discountDescription, discountRate);
        discountRegister.removeDiscount(discountId);
        assertFalse(discountRegister.getDiscounts().containsKey(discountId));
    }
    
    @Test
    void testRemoveDiscountWithInvalidId() {
        int discountId = 1;