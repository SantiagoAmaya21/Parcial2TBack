package edu.eci.cvds.parcial2t.controller;

import edu.eci.cvds.parcial2t.model.DTO.PurchaseDTO;
import edu.eci.cvds.parcial2t.model.Transaction;
import edu.eci.cvds.parcial2t.service.ServicesPurchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    @Autowired
    private ServicesPurchase purchaseService;

    @PostMapping("/transaction")
    public ResponseEntity<?> savePurchase(@RequestBody PurchaseDTO purchaseDTO){
        try{
            Transaction transaction = purchaseService.savePurchase(purchaseDTO);
            return ResponseEntity.ok(transaction);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PurchaseDTO>> getPurchasesByUserId(@PathVariable String userId){
        List<PurchaseDTO> purchasesByUserId = purchaseService.getPurchasesByUserId(userId);
        return ResponseEntity.ok(purchasesByUserId);
    }
}
