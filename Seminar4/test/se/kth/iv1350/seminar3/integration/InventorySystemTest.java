import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InventorySystemTest {
    private InventorySystem inventorySystem;

    @BeforeEach
    void setUp() {
        inventorySystem = new InventorySystem();
    }

    @AfterEach
    void tearDown() {
        inventorySystem = null;
    }

    @Test
    void testUpdateInventory() {
        int itemId = 1;
        int quantity = 10;
        inventorySystem.updateInventory(itemId, quantity);
        int updatedQuantity = inventorySystem.getInventoryQuantity(itemId);
        assertEquals(quantity, updatedQuantity, "Inventory quantity not updated correctly");
    }

    @Test
    void testGetInventoryQuantityForNonExistentItem() {
        int nonExistentItemId = 999;
        int quantity = inventorySystem.getInventoryQuantity(nonExistentItemId);
        assertEquals(0, quantity, "Non-existent item should have quantity 0");
    }

    @Test
    void testUpdateInventoryWithNegativeQuantity() {
        int itemId = 2;
        int negativeQuantity = -5;
        inventorySystem.updateInventory(itemId, negativeQuantity);
        int updatedQuantity = inventorySystem.getInventoryQuantity(itemId);
        assertEquals(0, updatedQuantity, "Negative quantity should set inventory to 0");
    }
}
