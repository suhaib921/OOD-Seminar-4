import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class ReceiptTest {
    private Receipt receipt;
    private List<ItemDTO> itemDTOs;

    @BeforeEach
    void setUp() {
        itemDTOs = new ArrayList<>();
        itemDTOs.add(new ItemDTO(1, 10.0, 0.25, "Item 1"));
        itemDTOs.add(new ItemDTO(2, 20.0, 0.12, "Item 2"));
        receipt = new Receipt(1, LocalDateTime.now(), itemDTOs);
    }

    @AfterEach
    void tearDown() {
        receipt = null;
        itemDTOs = null;
    }

    @Test
    void testGetTotalPrice() {
        double expectedTotalPrice = 33.0;
        double actualTotalPrice = receipt.getTotalPrice();
        assertEquals(expectedTotalPrice, actualTotalPrice, 0.001, "Total price calculation is incorrect.");
    }

    @Test
    void testGetTotalVAT() {
        double expectedTotalVAT = 4.95;
        double actualTotalVAT = receipt.getTotalVAT();
        assertEquals(expectedTotalVAT, actualTotalVAT, 0.001, "Total VAT calculation is incorrect.");
    }

    @Test
    void testGetItemDTOs() {
        List<ItemDTO> expectedItemDTOs = itemDTOs;
        List<ItemDTO> actualItemDTOs = receipt.getItemDTOs();
        assertEquals(expectedItemDTOs, actualItemDTOs, "Item DTOs are not correct.");
    }

    @Test
    void testGetReceiptId() {
        int expectedReceiptId = 1;
        int actualReceiptId = receipt.getReceiptId();
        assertEquals(expectedReceiptId, actualReceiptId, "Receipt ID is incorrect.");
    }

    @Test
    void testGetDateTime() {
        LocalDateTime expectedDateTime = receipt.getDateTime();
        assertNotNull(expectedDateTime, "DateTime is null.");
    }
