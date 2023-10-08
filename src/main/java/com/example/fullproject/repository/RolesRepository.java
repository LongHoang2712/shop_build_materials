package com.example.fullproject.repository;

import com.example.fullproject.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
