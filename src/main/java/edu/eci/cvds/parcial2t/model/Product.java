package edu.eci.cvds.parcial2t.model;

import edu.eci.cvds.parcial2t.model.DTO.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Product {
    private String name;
    private double price;
    private int quantity;

    public ProductDTO toDTO(){
        ProductDTO dto = new ProductDTO();
        dto.setName(this.name);
        dto.setPrice(this.price);
        dto.setQuantity(this.quantity);
        return dto;
    }
}
