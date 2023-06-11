package com.example.backend.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.Entities.Admin;
import java.util.List;


public interface AdminRepo extends JpaRepository<Admin,Long>{
    
    public Optional<Admin> findByUsername(String username);
}
