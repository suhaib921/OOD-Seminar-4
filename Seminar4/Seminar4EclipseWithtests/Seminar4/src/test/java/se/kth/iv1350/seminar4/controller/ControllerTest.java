package se.kth.iv1350.seminar4.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import se.kth.iv1350.seminar4.dto.ItemDTO;
import se.kth.iv1350.seminar4.integration.DatabaseServerNotRunningException;
import se.kth.iv1350.seminar4.integration.NoSuchItemFoundException;
import se.kth.iv1350.seminar4.util.Logger;
import se.kth.iv1350.seminar4.util.ErrorLogger;
/**
 * Test cases for the Controller class.
 */
public class ControllerTest {

    private Controller controller;

    @BeforeAll
    public static void setUpClass() {
        // Initialize any resources needed for all tests
    }

    @AfterAll
    public static void tearDownClass() {
        // Clean up resources used in all tests
    }

    @BeforeEach
    public void setUp() {
   
        controller = new Controller(new ErrorLogger());
        controller.startSale();
    }

    @AfterEach
    public void tearDown() {
        // Clean up resources used in individual tests
    }

    /**
     * Tests if scanning a valid item ID returns an item.
     * Ensures the method does not throw an exception for valid inputs.
     */
    @Test
    public void testScanItemWithValidID() {
        int validItemID = 1; // Assuming 1 is a valid item ID
        try {
            ItemDTO result = controller.scanItem(validItemID, 1);
            assertNotNull(result, "Item was not found when expected.");
        } catch (NoSuchItemFoundException | DatabaseServerNotRunningException e) {
            fail("No exception should be thrown for a valid item ID.");
        }
    }

    /**
     * Tests that an exception is thrown when an invalid item ID is scanned.
     * This test ensures that the correct exception is thrown for nonexistent items.
     */
    @Test
    public void testScanItemWithInvalidIDThrowsNoSuchItemFoundException() {
        int invalidItemID = 999; // This item ID does not exist in the inventory
        assertThrows(NoSuchItemFoundException.class, () -> {
            controller.scanItem(invalidItemID, 1);
        }, "NoSuchItemFoundException should be thrown for an invalid item ID.");
    }

    /**
     * Tests if the item quantity is increased when an already scanned item is scanned again.
     */
    @Test
    public void testScanItemItem3IncreaseQuantity() {
        int itemID = 3; // Assuming item ID 3 is valid and has been scanned before
        try {
            controller.scanItem(itemID, 1); // Scan once to add to sale
            ItemDTO result = controller.scanItem(itemID, 1); // Scan again to increase quantity
            assertNotNull(result, "Item was not found when expected.");
            // Additional assertions can be made here to check if the quantity increased
            // For example, you might need a method in Sale or Controller to verify the quantity
        } catch (NoSuchItemFoundException | DatabaseServerNotRunningException e) {
            fail("No exception should be thrown for a valid item ID.");
        }
    }

    /**
     * Tests if DatabaseServerNotRunningException is thrown correctly during database failure scenarios.
     * Assuming item ID 12 initiates the fake scenario causing server error exception.
     */
    @Test
    public void testScanItemThrowsDatabaseServerNotRunningException() {
        int problematicItemID = 12; // Assuming item ID 12 causes a database issue
        assertThrows(DatabaseServerNotRunningException.class, () -> {
            controller.scanItem(problematicItemID, 1);
        }, "DatabaseServerNotRunningException should be thrown for database issues.");
    }
}
