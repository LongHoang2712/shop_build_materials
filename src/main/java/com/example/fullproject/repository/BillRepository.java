package com.example.fullproject.repository;

import com.example.fullproject.entities.Bill;
import com.example.fullproject.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
    Page<Bill> findByCode(String code , Pageable pageable);
}
