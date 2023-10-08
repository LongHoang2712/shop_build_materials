package com.example.fullproject.services;

import com.example.fullproject.dto.CategoryDTO;
import com.example.fullproject.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface CategoryServices<T> {
    Page<T> getAllCategory(Pageable pageable);
    Set<T> getAllCategory();

    Boolean saveCategory(CategoryDTO categoryDTO);

    Boolean updateCategory(CategoryDTO categoryDTO);

    Boolean deleteCategory(int t);
}
