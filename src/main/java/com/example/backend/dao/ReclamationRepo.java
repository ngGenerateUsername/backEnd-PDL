package com.example.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.Entities.Reclamation;

public interface ReclamationRepo extends JpaRepository<Reclamation, Long> {
    
}
