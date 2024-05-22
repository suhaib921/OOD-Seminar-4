import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.seminar3.integration.ItemDTO;
import se.kth.iv1350.seminar3.integration.InventoryRegistry;
import se.kth.iv1350.seminar3.integration.InventoryRegistryCreator;
import se.kth.iv1350.seminar3.integration.Printer;
import se.kth.iv1350.seminar3.integration.Sale;

public class SaleTest {
    private InventoryRegistry inventoryRegistry;
    private Sale sale;
    private Printer printer;

    @BeforeEach
    public void setUp() {
        inventoryRegistry = InventoryRegistryCreator.getInventoryRegistry();
        printer = new Printer();
        sale = new Sale(inventoryRegistry, printer);
    }

    @AfterEach
    public void tearDown() {
        inventoryRegistry = null;
        sale = null;
        printer = null;
    }

    @Test
    public void testAddItemToSale() {
        ItemDTO itemDTO = new ItemDTO(1, "Test Item", 10.0, 0.25);
        sale.addItem(itemDTO);
        assertEquals(1, sale.getItems().size());
        assertEquals(itemDTO, sale.getItems().get(0));
    }

    @Test
    public void testAddMultipleItemsToSale() {
        ItemDTO itemDTO1 = new ItemDTO(1, "Test Item 1", 10.0, 0.25);
        ItemDTO itemDTO2 = new ItemDTO(2, "Test Item 2", 20.0, 0.12);
        sale.addItem(itemDTO1);
        sale.addItem(itemDTO2);
        assertEquals(2, sale.getItems().size());
        assertEquals(itemDTO1, sale.getItems().get(0));
        assertEquals(itemDTO2, sale.getItems().get(1));
    }

    @Test
    public void testAddNonExistentItemToSale() {
        ItemDTO itemDTO = new ItemDTO(999, "Non-existent Item", 100.0, 0.25);
        assertThrows(IllegalArgumentException.class, () -> sale.addItem(itemDTO));
    }

    @Test
    public void testCalculateTotalPrice() {
        ItemDTO itemDTO1 = new ItemDTO(1, "Test Item 1", 10.0, 0.25);
        ItemDTO itemDTO2 = new ItemDTO(2, "Test Item 2", 20.0, 0.12);
        sale.addItem(itemDTO1);
        sale.addItem(itemDTO2);
        double expectedTotalPrice = 10.0 * 1.25 + 20.0 * 1.12;
        assertEquals(expectedTotalPrice, sale.calculateTotalPrice(), 0.001);
    }

    @Test
    public void testCalculateTotalPriceWithNoItems() {
        assertEquals(0.0, sale.calculateTotalPrice(), 0.001);
    }
