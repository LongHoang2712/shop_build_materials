package com.example.fullproject.entities;

import com.example.fullproject.dto.ManufacturerDto;
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
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany(mappedBy = "manufacturerSet" ,cascade = { CascadeType.MERGE, CascadeType.REMOVE,
            CascadeType.REFRESH, CascadeType.DETACH },
            fetch = FetchType.EAGER)
    private Set<Product> productSet1 = new HashSet<>();
    public Manufacturer toEntityManufacturer(ManufacturerDto manufacturerDto) {
        return Manufacturer.builder().
                id(manufacturerDto.getId()).
                name(manufacturerDto.getName()).
                productSet1(manufacturerDto.getProductSet1())
                .build();
    }
}
