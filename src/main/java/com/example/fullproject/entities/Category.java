package com.example.fullproject.entities;

import com.example.fullproject.dto.CategoryDTO;
import com.example.fullproject.dto.ProductDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id ;
    String name;
@OneToMany(mappedBy = "categorySet", fetch = FetchType.EAGER)
    private Set<Product> productSet = new HashSet<>();
    public Category toEntityCategory(CategoryDTO categoryDTO) {
        return Category.builder().
                id(categoryDTO.getId()).
                name(categoryDTO.getName()).
                productSet(categoryDTO.getProductSet())
                .build();
    }
}
