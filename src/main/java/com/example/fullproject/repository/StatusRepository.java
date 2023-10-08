package com.example.fullproject.repository;

import com.example.fullproject.entities.Product;
import com.example.fullproject.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
}
