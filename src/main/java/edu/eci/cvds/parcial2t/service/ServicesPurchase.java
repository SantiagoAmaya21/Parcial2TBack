package edu.eci.cvds.parcial2t.service;

import edu.eci.cvds.parcial2t.model.DTO.PurchaseDTO;
import edu.eci.cvds.parcial2t.model.Purchase;
import edu.eci.cvds.parcial2t.model.Transaction;

import java.util.List;

public interface ServicesPurchase {
    Transaction savePurchase(PurchaseDTO dto);
    List<PurchaseDTO> getPurchasesByUserId(String userId);
}