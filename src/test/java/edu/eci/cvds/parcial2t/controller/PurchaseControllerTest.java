package edu.eci.cvds.parcial2t.controller;

import edu.eci.cvds.parcial2t.model.DTO.ProductDTO;
import edu.eci.cvds.parcial2t.model.DTO.PurchaseDTO;
import edu.eci.cvds.parcial2t.model.Status;
import edu.eci.cvds.parcial2t.model.Transaction;
import edu.eci.cvds.parcial2t.service.ServicesPurchase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PurchaseControllerTest {

    @InjectMocks
    private PurchaseController purchaseController;

    @Mock
    private ServicesPurchase purchaseService;

    private PurchaseDTO purchaseDTO;
    private Transaction transaction;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        List<ProductDTO> products = Arrays.asList(
                new ProductDTO("Laptop", 1000.0, 1),
                new ProductDTO("Mouse", 50.0, 1)
        );

        purchaseDTO = new PurchaseDTO(
                "user1",
                products,
                1050.0,
                Status.APPROVED,
                "txn123",
                "El pago fue aprobado correctamente!",
                LocalDate.now()
        );

        transaction = new Transaction("txn123", 1050.0, Status.APPROVED, "El pago fue aprobado correctamente!");
    }

    @Test
    public void testSavePurchase_Success() {
        when(purchaseService.savePurchase(any(PurchaseDTO.class))).thenReturn(transaction);

        ResponseEntity<?> response = purchaseController.savePurchase(purchaseDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(transaction, response.getBody());
    }

    @Test
    public void testSavePurchase_Error() {
        when(purchaseService.savePurchase(any(PurchaseDTO.class))).thenThrow(new RuntimeException("Error en la compra"));

        ResponseEntity<?> response = purchaseController.savePurchase(purchaseDTO);

        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("Error en la compra"));
    }

    @Test
    public void testGetPurchasesByUserId() {
        List<PurchaseDTO> expectedPurchases = Arrays.asList(purchaseDTO);
        when(purchaseService.getPurchasesByUserId("user1")).thenReturn(expectedPurchases);

        ResponseEntity<List<PurchaseDTO>> response = purchaseController.getPurchasesByUserId("user1");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedPurchases, response.getBody());
    }
}
