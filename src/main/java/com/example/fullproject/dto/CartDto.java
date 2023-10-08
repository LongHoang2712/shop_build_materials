package com.example.fullproject.dto;

import com.example.fullproject.entities.Bill;
import com.example.fullproject.entities.Product;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private long id;
    private int quantity;
    private ProductDto product;
    private Bill bill;

    public CartDto(long id, int quantity, ProductDto product) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
    }
}
