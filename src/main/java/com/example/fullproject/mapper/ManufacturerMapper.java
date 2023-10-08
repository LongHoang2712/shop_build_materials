package com.example.fullproject.mapper;

import com.example.fullproject.dto.ManufacturerDto;
import com.example.fullproject.dto.ProductDto;
import com.example.fullproject.entities.Manufacturer;
import com.example.fullproject.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ManufacturerMapper {
    public ManufacturerDto todtoManufacturer(Manufacturer manufacturer) {
        return ManufacturerDto.builder().
                id(manufacturer.getId()).
                name(manufacturer.getName()).
                productSet1(manufacturer.getProductSet1()).
                build();
    }
}
