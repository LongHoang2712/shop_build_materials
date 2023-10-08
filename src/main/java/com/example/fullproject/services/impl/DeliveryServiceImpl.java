package com.example.fullproject.services.impl;

import com.example.fullproject.mapper.DeliveryMapper;
import com.example.fullproject.repository.DeliveryRepository;
import com.example.fullproject.services.DeliveryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DeliveryServiceImpl implements DeliveryServices {
    @Autowired
    DeliveryRepository deliveryRepository;
    @Autowired
    DeliveryMapper deliveryMapper;
    @Override
    public Set getAllDelivery() {
        return deliveryRepository.findAll().stream().map(x -> deliveryMapper.todtoDelivery(x)).collect(Collectors.toSet());
    }

}
