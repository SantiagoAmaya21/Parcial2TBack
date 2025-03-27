package edu.eci.cvds.parcial2t.model.DTO;


import edu.eci.cvds.parcial2t.model.Product;
import edu.eci.cvds.parcial2t.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PurchaseDTO {
    private String userId;
    private List<ProductDTO> products;
    private double totalAmount;
    private Status status;
    private String transactionId;
    private String responseMessage;
    private LocalDate purchaseDate;
}
