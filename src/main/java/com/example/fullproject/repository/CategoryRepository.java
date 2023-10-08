package com.example.fullproject.repository;

import com.example.fullproject.entities.Category;
import com.example.fullproject.entities.Manufacturer;
import com.example.fullproject.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Page<Category> findByName(String name , Pageable pageable);
}
