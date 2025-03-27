package edu.eci.cvds.parcial2t.model.DTO;

import edu.eci.cvds.parcial2t.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductDTO {
    private String name;
    private double price;
    private int quantity;

    public Product toProduct(){
        return new Product(this.name,this.price,this.quantity);
    }
}
