package com.example.fullproject.mapper;

import com.example.fullproject.dto.CategoryDTO;
import com.example.fullproject.dto.ManufacturerDto;
import com.example.fullproject.entities.Category;
import com.example.fullproject.entities.Manufacturer;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryDTO todtoCategory(Category category) {
        return CategoryDTO.builder().
                id(category.getId()).
                name(category.getName()).
                productSet(category.getProductSet()).
                build();
    }
}
