package com.example.fullproject.services.impl;

import com.example.fullproject.dto.CategoryDTO;
import com.example.fullproject.dto.ManufacturerDto;
import com.example.fullproject.entities.Manufacturer;
import com.example.fullproject.mapper.ManufacturerMapper;
import com.example.fullproject.repository.ManufacturerRepository;
import com.example.fullproject.services.ManufacturerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ManufacturerDTOServices implements ManufacturerServices {
    @Autowired
    private ManufacturerMapper mapper;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Override
    public Page getAllManufacturer(Pageable pageable) {
         return manufacturerRepository.findAll(pageable).map(x -> mapper.todtoManufacturer(x));
    }

    @Override
    public Set getAllManufacturer() {
        return manufacturerRepository.findAll().stream().map(x -> mapper.todtoManufacturer(x)).collect(Collectors.toSet());
    }

    @Override
    public Boolean saveManufacturer(ManufacturerDto manufacturerDto) {
        try {
            manufacturerRepository.save(new Manufacturer().toEntityManufacturer(manufacturerDto));
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean updateManufacturer(ManufacturerDto manufacturerDto) {
        try {
            manufacturerRepository.save(new Manufacturer().toEntityManufacturer(manufacturerDto));
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean deleteManufacturer(int id) {
        Manufacturer manufacturerDto = manufacturerRepository.findById(id).get();
        manufacturerRepository.delete(manufacturerDto);
        return true;
    }
    public List<ManufacturerDto> findbyId(int id){
        return manufacturerRepository.findById(id).stream().map(x->mapper.todtoManufacturer(x)).toList();
    }
    public Page<ManufacturerDto> findByName(String name, Pageable pageable) {
        return manufacturerRepository.findByName(name, pageable).map(x -> mapper.todtoManufacturer(x));
    }
}
