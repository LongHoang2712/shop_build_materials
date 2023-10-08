package com.example.fullproject.dto;

import com.example.fullproject.entities.Product;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ManufacturerDto {
    private int id;
    private String name;
    private Set<Product> productSet1 = new HashSet<>();

    public ManufacturerDto(String name, Set<Product> productSet1) {
        this.name = name;
        this.productSet1 = productSet1;
    }
}
