package se.kth.iv1350.seminar4.integration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.seminar4.dto.ItemDTO;
import se.kth.iv1350.seminar4.dto.SaleDTO;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import se.kth.iv1350.seminar4.dto.ItemDTO;

public class InventorySystemTest {

    private InventorySystem inventorySystem;

    @BeforeEach
    public void setUp() {
        inventorySystem = new InventorySystem();
    }

    /**
     * Tests fetching a valid item.
     */
    @Test
    public void testFetchItemInfoWithValidID() {
        int validItemID = 1; // Assuming 1 is a valid item ID
        try {
            ItemDTO result = inventorySystem.fetchIteminfo(validItemID);
            assertNotNull(result, "Item should be found for a valid item ID.");
            assertEquals(validItemID, result.getItemID(), "The item ID should match the requested ID.");
        } catch (SQLException e) {
            fail("SQLException should not be thrown for a valid item ID.");
        }
    }

    /**
     * Tests fetching an invalid item.
     * Ensures that the result is null for nonexistent items.
     */
    @Test
    public void testFetchItemInfoWithInvalidID() {
        int invalidItemID = 999; // This item ID does not exist in the inventory
        try {
            ItemDTO result = inventorySystem.fetchIteminfo(invalidItemID);
            assertNull(result, "Result should be null for an invalid item ID.");
        } catch (SQLException e) {
            fail("SQLException should not be thrown for an invalid item ID.");
        }
    }

    /**
     * Tests if SQLException is thrown correctly for a problematic item ID.
     * Assuming item ID 12 causes a database issue.
     */
    @Test
    public void testFetchItemInfoThrowsSQLException() {
        int problematicItemID = 12; // Assuming item ID 12 causes a database issue
        assertThrows(SQLException.class, () -> {
            inventorySystem.fetchIteminfo(problematicItemID);
        }, "SQLException should be thrown for problematic item ID.");
    }
}
