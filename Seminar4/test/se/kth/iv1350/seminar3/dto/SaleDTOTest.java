import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.seminar3.integration.Sale;
import se.kth.iv1350.seminar3.dto.ItemDTO;
import se.kth.iv1350.seminar3.dto.SaleDTO;

import java.util.ArrayList;
import java.util.List;

public class SaleDTOTest {

    @Test
    void testGetTheListOfPurchasedItems() {
        Sale sale = new Sale();
        sale.addItem(new ItemDTO(1, "Item 1", 10.0, 0.25));
        sale.addItem(new ItemDTO(2, "Item 2", 20.0, 0.12));
        SaleDTO saleDTO = new SaleDTO(sale);
        List<ItemDTO> purchasedItems = saleDTO.getTheListOfPurchasedItems();
        assertEquals(2, purchasedItems.size());
        assertEquals("Item 1", purchasedItems.get(0).getName());
        assertEquals("Item 2", purchasedItems.get(1).getName());
    }

    @Test
    void testGetTheCurrentTotalPrice() {
        Sale sale = new Sale();
        sale.addItem(new ItemDTO(1, "Item 1", 10.0, 0.25));
        sale.addItem(new ItemDTO(2, "Item 2", 20.0, 0.12));
        SaleDTO saleDTO = new SaleDTO(sale);
        double totalPrice = saleDTO.getTheCurrentTotalPrice();
        assertEquals(33.4, totalPrice, 0.01);
    }

    @Test
    void testGetSaleDTOID() {
        Sale sale = new Sale();
        sale.addItem(new ItemDTO(1, "Item 1", 10.0, 0.25));
        SaleDTO saleDTO = new SaleDTO(sale);
        int saleId = saleDTO.getSaleDTOID();
        assertEquals(sale.getSaleID(), saleId);
    }

    @Test
    void testUnmodifiableListOfPurchasedItems() {
        Sale sale = new Sale();
        sale.addItem(new ItemDTO(1, "Item 1", 10.0, 0.25));
        SaleDTO saleDTO = new SaleDTO(sale);
        List<ItemDTO> purchasedItems = saleDTO.getTheListOfPurchasedItems();
        try {
            purchasedItems.add(new ItemDTO(2, "Item 2", 20.0, 0.12));
            fail("Expected UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            // Expected exception
        }
    }

    @Test
    void testCopyItems() {
        ArrayList<ItemDTO> originalItems = new ArrayList<>();
        originalItems.add(new ItemDTO(1, "Item 1", 10.0, 0.25));
        originalItems.add(new ItemDTO(2, "Item 2", 20.0, 0.12));
        Sale sale = new Sale();
        SaleDTO saleDTO = new SaleDTO(sale);
        ArrayList<ItemDTO> copiedItems = saleDTO.copyItems(originalItems);
        assertNotSame(originalItems, copiedItems);
        assertEquals(originalItems.size(), copiedItems.size());
        for (int i = 0; i < originalItems.size(); i++) {
            assertEquals(originalItems.get(i).getName(), copiedItems.get(i).getName());
        }
    }
}
