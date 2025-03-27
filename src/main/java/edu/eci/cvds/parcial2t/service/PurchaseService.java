package edu.eci.cvds.parcial2t.service;

import edu.eci.cvds.parcial2t.model.DTO.ProductDTO;
import edu.eci.cvds.parcial2t.model.DTO.PurchaseDTO;
import edu.eci.cvds.parcial2t.model.Purchase;
import edu.eci.cvds.parcial2t.model.Status;
import edu.eci.cvds.parcial2t.model.Transaction;
import edu.eci.cvds.parcial2t.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.UUID;
import java.util.List;
@Service
public class PurchaseService implements ServicesPurchase {

    @Autowired

    private PurchaseRepository purchaseRepository;

    @Override
    public Transaction savePurchase(PurchaseDTO dto){
        Purchase purchase = new Purchase();
        purchase.setUserId(dto.getUserId());
        purchase.setProducts(dto.getProducts().stream().map(ProductDTO::toProduct).collect(Collectors.toList()));
        purchase.setTotalAmount(dto.getTotalAmount());
        purchase.setTransactionId(UUID.randomUUID().toString());
        purchase.setPurchaseDate(dto.getPurchaseDate());

        double productsPriceAmount = dto.getProducts().stream()
                .mapToDouble(p -> p.getPrice() * p.getQuantity())
                .sum();

        if (productsPriceAmount != dto.getTotalAmount()) {
            purchase.setStatus(Status.DECLINED);
            purchase.setResponseMessage("El pago fue rechazado por inconsistencia en los valores!");
            purchaseRepository.save(purchase);
            return new Transaction(purchase.getTransactionId(), purchase.getTotalAmount(), purchase.getStatus(), purchase.getResponseMessage());
        }

        purchase.setStatus(Status.APPROVED);
        purchase.setResponseMessage("El pago fue aprobado correctamente!");
        purchaseRepository.save(purchase);
        return new Transaction(purchase.getTransactionId(), purchase.getTotalAmount(), purchase.getStatus(), purchase.getResponseMessage());
    }

    @Override
    public List<PurchaseDTO> getPurchasesByUserId(String userId){
        return purchaseRepository.findByUserId(userId).stream().map(Purchase::toDTO).collect(Collectors.toList());
    }

}