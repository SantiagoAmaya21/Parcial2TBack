package edu.eci.cvds.parcial2t.service;

import edu.eci.cvds.parcial2t.model.DTO.ProductDTO;
import edu.eci.cvds.parcial2t.model.DTO.PurchaseDTO;
import edu.eci.cvds.parcial2t.model.Purchase;
import edu.eci.cvds.parcial2t.model.Status;
import edu.eci.cvds.parcial2t.model.Transaction;
import edu.eci.cvds.parcial2t.repository.PurchaseRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PurchaseServiceTest {

    @Mock
    private PurchaseRepository purchaseRepository;

    @InjectMocks
    private PurchaseService purchaseService;

    private PurchaseDTO validPurchaseDTO;
    private PurchaseDTO invalidPurchaseDTO;
    private Purchase validPurchase;
    private Purchase invalidPurchase;

    @Before
    public void setUp() {
        // Productos válidos
        ProductDTO product1 = new ProductDTO("Laptop", 1000.00, 1);
        ProductDTO product2 = new ProductDTO("Mouse", 50.00, 2);
        List<ProductDTO> validProducts = Arrays.asList(product1, product2);
        double validTotalAmount = 1000.00 + (50.00 * 2);

        // Productos con inconsistencia en totalAmount
        double invalidTotalAmount = 2000.00; // Incorrecto a propósito

        // Compra válida
        validPurchaseDTO = new PurchaseDTO("user1", validProducts, validTotalAmount,Status.APPROVED,"123456782", "Purchase Successfully", LocalDate.now());
        validPurchase = new Purchase(UUID.randomUUID().toString(), "user1",
                validPurchaseDTO.getProducts().stream().map(ProductDTO::toProduct).toList(),
                validTotalAmount, Status.APPROVED, UUID.randomUUID().toString(),
                "El pago fue aprobado correctamente!", LocalDate.now());

        // Compra inválida
        invalidPurchaseDTO = new PurchaseDTO("user1", validProducts, invalidTotalAmount,Status.DECLINED,"123456782","Error", LocalDate.now());
        invalidPurchase = new Purchase(UUID.randomUUID().toString(), "user1",
                invalidPurchaseDTO.getProducts().stream().map(ProductDTO::toProduct).toList(),
                invalidTotalAmount, Status.DECLINED, UUID.randomUUID().toString(),
                "El pago fue rechazado por inconsistencia en los valores!", LocalDate.now());
    }

    @Test
    public void testSavePurchase_ValidPurchase() {
        // Simulación del guardado en repositorio
        when(purchaseRepository.save(Mockito.any(Purchase.class))).thenReturn(validPurchase);

        Transaction transaction = purchaseService.savePurchase(validPurchaseDTO);

        assertNotNull(transaction);
        assertEquals(validPurchase.getTotalAmount(), transaction.getTotalAmount(), 0.01);
        assertEquals(Status.APPROVED, transaction.getStatus());
        assertEquals("El pago fue aprobado correctamente!", transaction.getResponseMessage());

        verify(purchaseRepository, times(1)).save(Mockito.any(Purchase.class));
    }

    @Test
    public void testSavePurchase_InvalidPurchase() {
        // Simulación del guardado en repositorio
        when(purchaseRepository.save(Mockito.any(Purchase.class))).thenReturn(invalidPurchase);

        Transaction transaction = purchaseService.savePurchase(invalidPurchaseDTO);

        assertNotNull(transaction);
        assertEquals(invalidPurchase.getTotalAmount(), transaction.getTotalAmount(), 0.01);
        assertEquals(Status.DECLINED, transaction.getStatus());
        assertEquals("El pago fue rechazado por inconsistencia en los valores!", transaction.getResponseMessage());

        verify(purchaseRepository, times(1)).save(Mockito.any(Purchase.class));
    }

    @Test
    public void testGetPurchasesByUserId() {
        when(purchaseRepository.findByUserId("user1")).thenReturn(Arrays.asList(validPurchase));

        List<PurchaseDTO> purchases = purchaseService.getPurchasesByUserId("user1");

        assertNotNull(purchases);
        assertEquals(1, purchases.size());
        assertEquals("user1", purchases.get(0).getUserId());
        assertEquals(validPurchase.getTotalAmount(), purchases.get(0).getTotalAmount(), 0.01);

        verify(purchaseRepository, times(1)).findByUserId("user1");
    }
}
