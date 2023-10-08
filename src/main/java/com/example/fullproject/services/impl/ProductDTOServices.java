package com.example.fullproject.services.impl;

import com.example.fullproject.dto.CategoryDTO;
import com.example.fullproject.dto.ManufacturerDto;
import com.example.fullproject.dto.ProductDto;
import com.example.fullproject.entities.Category;
import com.example.fullproject.entities.Product;
import com.example.fullproject.mapper.ProductMapper;
import com.example.fullproject.repository.CategoryRepository;
import com.example.fullproject.repository.ProductRepository;
import com.example.fullproject.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductDTOServices implements ProductServices {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Page getAll(Pageable pageable) {
        return productRepository.findAll(pageable).map(x -> productMapper.todto(x));
    }

    public Set getAll() {
        return productRepository.findAll().stream().map(x -> productMapper.todto(x)).collect(Collectors.toSet());
    }


    @Override
    public Boolean save(ProductDto productDto) {
        try {
            Product product = new Product().toEntity(productDto);
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    @Override
    public Boolean update(ProductDto productDto) {
        try {
            productRepository.save(new Product().toEntity(productDto));
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean delete(int id) {
        Product productDTO = productRepository.findById(id).get();
        productRepository.delete(productDTO);
        return true;
    }

    public Page<ProductDto> findByName(String name, Pageable pageable) {
        return productRepository.findByName(name, pageable).map(x -> productMapper.todto(x));
    }

    public List<ProductDto> findbyId(int id) {
        return productRepository.findById(id).stream().map(x->productMapper.todto(x)).toList();
    }
}
