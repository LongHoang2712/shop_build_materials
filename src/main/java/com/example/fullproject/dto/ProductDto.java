package com.example.fullproject.dto;

import com.example.fullproject.entities.Category;
import com.example.fullproject.entities.Manufacturer;
import com.example.fullproject.entities.Status;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class ProductDto {
    private int id;
    private String name;
    private long amount;
    private long price;
    private String image;
    private String donvi;
    private Status status ;
    private Category categorySet ;
    private Set<Manufacturer> manufacturerSet = new HashSet<>();
    private MultipartFile file;



    public ProductDto(String name, int amount, long price, String image, String donvi, Status status, Category categorySet, Set<Manufacturer> manufacturerSet, MultipartFile file) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.image = image;
        this.donvi = donvi;
        this.status = status;
        this.categorySet = categorySet;
        this.manufacturerSet = manufacturerSet;
        this.file = file;
    }

    public ProductDto(String name, int amount, long price, String donvi, Status status, Category categorySet, Set<Manufacturer> manufacturerSet, MultipartFile file) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.donvi = donvi;
        this.status = status;
        this.categorySet = categorySet;
        this.manufacturerSet = manufacturerSet;
        this.file = file;
    }

    public ProductDto(int id, String name, int amount, long price, String donvi, Status status, Category categorySet, Set<Manufacturer> manufacturerSet, MultipartFile file) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.donvi = donvi;
        this.status = status;
        this.categorySet = categorySet;
        this.manufacturerSet = manufacturerSet;
        this.file = file;
    }

    public ProductDto(int id, String name, int amount, long price, String image, String donvi, Status status, Category categorySet, Set<Manufacturer> manufacturerSet) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.image = image;
        this.donvi = donvi;
        this.status = status;
        this.categorySet = categorySet;
        this.manufacturerSet = manufacturerSet;
    }

    public ProductDto(String name, int amount, long price, String image, String donvi, Status status, Category categorySet, Set<Manufacturer> manufacturerSet) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.image = image;
        this.donvi = donvi;
        this.status = status;
        this.categorySet = categorySet;
        this.manufacturerSet = manufacturerSet;
    }

    public ProductDto(int id, String name, int amount, long price, String donvi, Status status, Category categorySet, Set<Manufacturer> manufacturerSet) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.donvi = donvi;
        this.status = status;
        this.categorySet = categorySet;
        this.manufacturerSet = manufacturerSet;
    }

    public ProductDto(String name, int amount, long price, String donvi, Status status, Category categorySet, Set<Manufacturer> manufacturerSet) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.donvi = donvi;
        this.status = status;
        this.categorySet = categorySet;
        this.manufacturerSet = manufacturerSet;
    }
}
