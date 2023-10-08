package com.example.fullproject.services.impl;

import com.example.fullproject.dto.CategoryDTO;
import com.example.fullproject.entities.Category;
import com.example.fullproject.mapper.CategoryMapper;
import com.example.fullproject.repository.CategoryRepository;
import com.example.fullproject.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryDTOServices implements CategoryServices {

    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public Page getAllCategory(Pageable pageable) {
        return categoryRepository.findAll(pageable).map(x -> categoryMapper.todtoCategory(x));
    }

    @Override
    public Set getAllCategory() {
        return categoryRepository.findAll().stream().map(x -> categoryMapper.todtoCategory(x)).collect(Collectors.toSet());
    }

    @Override
    public Boolean saveCategory(CategoryDTO categoryDTO) {
        try {
            categoryRepository.save(new Category().toEntityCategory(categoryDTO));
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean updateCategory(CategoryDTO categoryDTO) {
        try {

            categoryRepository.save(new Category().toEntityCategory(categoryDTO));
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean deleteCategory(int id) {
       Category categoryDTO = categoryRepository.findById(id).get();
        categoryRepository.delete(categoryDTO);
        return true;
    }
    public List<CategoryDTO> findbyId(int id){
        return categoryRepository.findById(id).stream().map(x->categoryMapper.todtoCategory(x)).toList();
    }
    public Page<CategoryDTO> findByName(String name, Pageable pageable) {
        return categoryRepository.findByName(name, pageable).map(x -> categoryMapper.todtoCategory(x));
    }

}
