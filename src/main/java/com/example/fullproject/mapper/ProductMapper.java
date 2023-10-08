package com.example.fullproject.mapper;

import com.example.fullproject.dto.ProductDto;
import com.example.fullproject.entities.Category;
import com.example.fullproject.entities.Product;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    public ProductDto todto(Product product){
        return ProductDto.builder().
                id((product.getId())).
                name(product.getName()).
                amount(product.getAmount()).
                price(product.getPrice()).
                image(product.getImage()).
                donvi(product.getDonvi()).
                categorySet(product.getCategorySet()).
                manufacturerSet(product.getManufacturerSet()).
                status(product.getStatus()).
                build();
    }

}
