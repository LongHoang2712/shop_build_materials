package com.example.fullproject.entities;

import com.example.fullproject.dto.CartDto;
import com.example.fullproject.dto.ManufacturerDto;
import com.example.fullproject.mapper.ProductMapper;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DetailBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    private int quantity;

    @ManyToOne()
    @JoinColumn(name = "id_product")
    private Product product;

    @ManyToOne()
    @JoinColumn(name = "id_bill")
    private Bill bill;

    public DetailBill(int quantity, Product product, Bill bill) {
        this.quantity = quantity;
        this.product = product;
        this.bill = bill;
    }

    public DetailBill(Product product) {
        this.product = product;
    }

    public DetailBill(long id, Bill bill) {
        this.id = id;
        this.bill = bill;
    }

    @Override
    public String toString() {
        return "DetailBill{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", product=" + product +
                ", bill=" + bill +
                '}';
    }

    public DetailBill toEntityDetailBill(CartDto cartDto) {
        Product product1 = new Product().toEntity(cartDto.getProduct());
        return DetailBill.builder().
                id(cartDto.getId()).
                product(product1).
                quantity(cartDto.getQuantity())
                .bill(cartDto.getBill()).
                build();
    }


}
