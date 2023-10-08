package com.example.fullproject.entities;

import com.example.fullproject.dto.CategoryDTO;
import com.example.fullproject.dto.DeliveryDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private String name;
    @OneToMany( fetch = FetchType.EAGER, mappedBy = "delivery",cascade = CascadeType.ALL)
    private List<Bill> bills ;
    public Delivery toEntityDelivery(DeliveryDto deliveryDto) {
        return Delivery.builder().
                id(deliveryDto.getId()).
                name(deliveryDto.getName()).
                bills(deliveryDto.getBill())
                .build();
    }
}
