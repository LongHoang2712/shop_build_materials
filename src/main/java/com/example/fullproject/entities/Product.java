package com.example.fullproject.entities;

import com.example.fullproject.dto.ProductDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private long amount;
    private long price;
    private String image;
    private String donvi;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category categorySet ;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_manufacturer",
            joinColumns = @JoinColumn(name = "product_id" ),
            inverseJoinColumns = @JoinColumn(name = "manufacturer_id" )
    )
    private Set<Manufacturer> manufacturerSet = new HashSet<>();
    @OneToMany(mappedBy = "product", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private Set<DetailBill> detailBills;
    @ManyToOne
    @JoinColumn(name = "id_status")
    private Status status;

    public Product toEntity(ProductDto productDto) {
        return Product.builder().
                id(productDto.getId()).
                name(productDto.getName()).
                amount(productDto.getAmount()).
                price(productDto.getPrice()).
                image(productDto.getImage()).
                donvi(productDto.getDonvi()).
                categorySet(productDto.getCategorySet()).
                manufacturerSet(productDto.getManufacturerSet()).
                status(productDto.getStatus())
                .build();
    }
}
