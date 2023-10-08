package com.example.fullproject.mapper;

import com.example.fullproject.dto.CategoryDTO;
import com.example.fullproject.dto.DeliveryDto;
import com.example.fullproject.entities.Category;
import com.example.fullproject.entities.Delivery;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DeliveryMapper {
    public DeliveryDto todtoDelivery(Delivery delivery) {
        return DeliveryDto.builder().
                id(delivery.getId()).
                name(delivery.getName()).
                bill(delivery.getBills()).
                build();
    }
}
