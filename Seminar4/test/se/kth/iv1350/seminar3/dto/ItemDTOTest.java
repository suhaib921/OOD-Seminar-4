import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ItemDTOTest {
    @Test
    public void testDecreaseQuantityPositive() {
        ItemDTO item = new ItemDTO(1, 10, "Test Item", 100.0);
        item.decreaseQuantity(5);
        assertEquals(5, item.getQuantity());
    }

    @Test
    public void testDecreaseQuantityZero() {
        ItemDTO item = new ItemDTO(1, 10, "Test Item", 100.0);
        item.decreaseQuantity(10);
        assertEquals(0, item.getQuantity());
    }

    @Test
    public void testDecreaseQuantityNegative() {
        ItemDTO item = new ItemDTO(1, 10, "Test Item", 100.0);
        item.decreaseQuantity(15);
        assertEquals(-5, item.getQuantity());
    }
}
