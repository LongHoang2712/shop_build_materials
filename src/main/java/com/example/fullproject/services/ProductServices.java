package com.example.fullproject.services;

import com.example.fullproject.dto.ManufacturerDto;
import com.example.fullproject.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductServices<T> {
    Page<T> getAll(Pageable pageable);

    Boolean save(ProductDto productDto);

    Boolean update(ProductDto productDto);

    Boolean delete(int t);

}
