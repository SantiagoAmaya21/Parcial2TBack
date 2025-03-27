package edu.eci.cvds.parcial2t.model;

import edu.eci.cvds.parcial2t.model.DTO.ProductDTO;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProductTest {

    @Test
    public void testProductConstructorAndGetters() {
        Product product = new Product("Laptop", 1200.50, 5);

        assertEquals("Laptop", product.getName());
        assertEquals(1200.50, product.getPrice(), 0.01);
        assertEquals(5, product.getQuantity());
    }

    @Test
    public void testProductSetters() {
        Product product = new Product();
        product.setName("Smartphone");
        product.setPrice(800.99);
        product.setQuantity(10);

        assertEquals("Smartphone", product.getName());
        assertEquals(800.99, product.getPrice(), 0.01);
        assertEquals(10, product.getQuantity());
    }

    @Test
    public void testToDTO() {
        Product product = new Product("Tablet", 300.75, 7);
        ProductDTO dto = product.toDTO();

        assertNotNull(dto);
        assertEquals("Tablet", dto.getName());
        assertEquals(300.75, dto.getPrice(), 0.01);
        assertEquals(7, dto.getQuantity());
    }
}
