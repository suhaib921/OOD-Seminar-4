package se.kth.iv1350.seminar3.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

import se.kth.iv1350.seminar3.dto.ItemDTO;
import se.kth.iv1350.seminar3.integration.InventorySystem;
import se.kth.iv1350.seminar3.integration.AccountingSystem;
import se.kth.iv1350.seminar3.integration.DiscountRegister;
import se.kth.iv1350.seminar3.integration.Printer;
import se.kth.iv1350.seminar3.modell.Sale;

class ControllerTest {
    private Controller controller;
    private InventorySystem inventorySystemMock;
    private Sale saleMock;

    @BeforeEach
    void setUp() {
        inventorySystemMock = Mockito.mock(InventorySystem.class);
        AccountingSystem accountingSystemMock = Mockito.mock(AccountingSystem.class);
        DiscountRegister discountRegisterMock = Mockito.mock(DiscountRegister.class);
        Printer printerMock = Mockito.mock(Printer.class);
        controller = new Controller(accountingSystemMock, discountRegisterMock, inventorySystemMock, printerMock);
        saleMock = Mockito.mock(Sale.class);
        controller.sale = saleMock;  // Assuming there's a way to set this, maybe via constructor or method
    }

    @Test
    void testStartSale() {
        controller.startSale();
        assertNotNull(controller.sale, "Sale should not be null after starting a sale.");
    }

    @Test
    void testScanItem() {
        int itemID = 1;
        int quantity = 5;
        ItemDTO expectedItem = new ItemDTO("Apple", itemID, 10.0, 1.25, quantity);
        Mockito.when(inventorySystemMock.fetchItemInfo(itemID)).thenReturn(expectedItem);

        ItemDTO actualItem = controller.scanItem(itemID, quantity);

        assertEquals(expectedItem, actualItem, "The item returned was not as expected.");
        Mockito.verify(saleMock).addItem(expectedItem, quantity);
    }

    @Test
    void testEndSale() {
        Mockito.when(saleMock.getCurrentTotalPrice()).thenReturn(100.0);
        double price = controller.endSale();
        assertEquals(100.0, price, "The end sale price should match the current total price.");
    }
}
