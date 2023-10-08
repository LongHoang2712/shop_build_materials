package com.example.fullproject.services;

import com.example.fullproject.dto.ManufacturerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface ManufacturerServices<T> {
    Page<T> getAllManufacturer(Pageable pageable);
    Set getAllManufacturer();

    Boolean saveManufacturer(ManufacturerDto manufacturerDto);

    Boolean updateManufacturer(ManufacturerDto manufacturerDto);

    Boolean deleteManufacturer(int t);
}
