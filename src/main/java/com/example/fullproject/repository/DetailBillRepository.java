package com.example.fullproject.repository;

import com.example.fullproject.entities.Category;
import com.example.fullproject.entities.DetailBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DetailBillRepository extends JpaRepository<DetailBill, Long> {
     Page<DetailBill> findByBillCode(String code, Pageable page);
}
