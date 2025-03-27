package edu.eci.cvds.parcial2t.model;

import edu.eci.cvds.parcial2t.model.DTO.PurchaseDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.stream.Collectors;
import java.util.List;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Document(collection = "Purchases")
public class Purchase implements Serializable {
    @Id
    private String id;
    private String userId;
    private List<Product> products;
    private double totalAmount;
    private Status status;
    private String transactionId;
    private String responseMessage;
    private LocalDate purchaseDate;

    public PurchaseDTO toDTO(){
        PurchaseDTO dto = new PurchaseDTO();
        dto.setUserId(this.userId);
        dto.setProducts(this.products.stream().map(Product::toDTO).collect(Collectors.toList()));
        dto.setTotalAmount(this.totalAmount);
        dto.setStatus(this.status);
        dto.setResponseMessage(this.responseMessage);
        dto.setPurchaseDate(this.purchaseDate);
        return dto;
    }
}


