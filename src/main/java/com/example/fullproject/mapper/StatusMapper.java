package com.example.fullproject.mapper;

import com.example.fullproject.dto.ProductDto;
import com.example.fullproject.dto.StatusDTO;
import com.example.fullproject.entities.Product;
import com.example.fullproject.entities.Status;
import org.springframework.stereotype.Component;

@Component
public class StatusMapper {
    public StatusDTO todtoStatus(Status status){
        return StatusDTO.builder().
                id(status.getId()).
                name(status.getName()).
                product(status.getProduct()).
                build();
    }
}
