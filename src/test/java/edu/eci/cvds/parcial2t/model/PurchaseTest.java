package edu.eci.cvds.parcial2t.model;

import edu.eci.cvds.parcial2t.model.DTO.PurchaseDTO;
import org.junit.Test;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PurchaseTest {

    @Test
    public void testPurchaseConstructorAndGetters() {
        Product product1 = new Product("Laptop", 1200.50, 1);
        Product product2 = new Product("Mouse", 25.75, 2);
        List<Product> products = Arrays.asList(product1, product2);

        Purchase purchase = new Purchase("123", "user1", products, 1252.00, Status.APPROVED, "txn123", "Success", LocalDate.now());

        assertEquals("123", purchase.getId());
        assertEquals("user1", purchase.getUserId());
        assertEquals(products, purchase.getProducts());
        assertEquals(1252.00, purchase.getTotalAmount(), 0.01);
        assertEquals(Status.APPROVED, purchase.getStatus());
        assertEquals("txn123", purchase.getTransactionId());
        assertEquals("Success", purchase.getResponseMessage());
        assertEquals(LocalDate.now(), purchase.getPurchaseDate());
    }

    @Test
    public void testPurchaseSetters() {
        Purchase purchase = new Purchase();
        purchase.setId("456");
        purchase.setUserId("user2");
        Product product = new Product("Keyboard", 50.00, 1);
        purchase.setProducts(Arrays.asList(product));
        purchase.setTotalAmount(50.00);
        purchase.setStatus(Status.APPROVED);
        purchase.setTransactionId("txn456");
        purchase.setResponseMessage("Pending Approval");
        purchase.setPurchaseDate(LocalDate.of(2025, 3, 26));

        assertEquals("456", purchase.getId());
        assertEquals("user2", purchase.getUserId());
        assertEquals(1, purchase.getProducts().size());
        assertEquals(50.00, purchase.getTotalAmount(), 0.01);
        assertEquals(Status.APPROVED, purchase.getStatus());
        assertEquals("txn456", purchase.getTransactionId());
        assertEquals("Pending Approval", purchase.getResponseMessage());
        assertEquals(LocalDate.of(2025, 3, 26), purchase.getPurchaseDate());
    }

    @Test
    public void testToDTO() {
        Product product1 = new Product("Tablet", 300.75, 1);
        Product product2 = new Product("Charger", 20.00, 2);
        List<Product> products = Arrays.asList(product1, product2);

        Purchase purchase = new Purchase("789", "user3", products, 340.75, Status.DECLINED, "txn789", "Payment Declined", LocalDate.now());
        PurchaseDTO dto = purchase.toDTO();

        assertNotNull(dto);
        assertEquals("user3", dto.getUserId());
        assertEquals(2, dto.getProducts().size());
        assertEquals(340.75, dto.getTotalAmount(), 0.01);
        assertEquals(Status.DECLINED, dto.getStatus());
        assertEquals("Payment Declined", dto.getResponseMessage());
        assertEquals(LocalDate.now(), dto.getPurchaseDate());
    }
}