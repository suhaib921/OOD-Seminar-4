package se.kth.iv1350.seminar4.controller;

import junit.framework.TestCase;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import java.sql.SQLException;

import se.kth.iv1350.seminar4.dto.ItemDTO;
import se.kth.iv1350.seminar4.integration.DatabaseServerNotRunningException;
import se.kth.iv1350.seminar4.integration.InventorySystem;
import se.kth.iv1350.seminar4.integration.NoSuchItemFoundException;
import se.kth.iv1350.seminar4.modell.Sale;
import se.kth.iv1350.seminar4.modell.SaleLog;
import se.kth.iv1350.seminar4.modell.SaleObserver;
import se.kth.iv1350.seminar4.util.Logger;




/**
 * Test cases for the Controller class.
 */
public class ControllerTest extends TestCase {


        @BeforeAll
        public static void setUpClass() {
        }

        @AfterAll
        public static void tearDownEvenIfError() {
        }

        @BeforeEach
        public void setUp() {

        }

        @AfterEach
        public void tearDown() {
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
        } catch (NoSuchItemFoundFor Exception | DatabaseServerNotRunningException e) {
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
     * item id already scanned and is in sale purchased list just increase its quantity.
     */
    @Test
    public void testScanItemItem3IncreaseQuantity() {

    }
    
        /**
         * Tests if DatabaseServerNotRunningException is thrown correctly during database failure scenarios.
         * item id 10 initiates the fake scenario causing server error exception.
         */
        @Test
        public void testScanItemThrowsDatabaseServerNotRunningException() {
            int problematicItemID = 12; // Assuming itemID 10 causes a database issue
            assertThrows(DatabaseServerNotRunningException.class, () -> {
                controller.scanMyItem(problematicItemID, 1);
            }, "DatabaseServerNotRunningException should be thrown for database issues");
        }

    }
