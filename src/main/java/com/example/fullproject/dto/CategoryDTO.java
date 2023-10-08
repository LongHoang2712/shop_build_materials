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
public class CategoryDTO {
    private  int id ;
    String name;
    private Set<Product> productSet = new HashSet<>();

    public CategoryDTO(String name, Set<Product> productSet) {
        this.name = name;
        this.productSet = productSet;
    }

    public CategoryDTO(String name) {
        this.name = name;
    }
}
