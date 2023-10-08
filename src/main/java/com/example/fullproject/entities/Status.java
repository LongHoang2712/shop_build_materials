package com.example.fullproject.entities;

import com.example.fullproject.dto.ProductDto;
import com.example.fullproject.dto.StatusDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
    private Set<Product> product;

    public Status(String name, Set<Product> product) {
        this.name = name;
        this.product = product;
    }

    public Status toEntity(StatusDTO statusDTO) {
        return Status.builder().
                id(statusDTO.getId()).
                name(statusDTO.getName()).
                product(statusDTO.getProduct())
                .build();
    }
}
