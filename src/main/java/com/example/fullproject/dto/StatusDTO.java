package com.example.fullproject.dto;

import com.example.fullproject.entities.Product;
import lombok.*;

import java.util.Set;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatusDTO {
    private int id;
    private String name;
    private Set<Product> product ;
}
