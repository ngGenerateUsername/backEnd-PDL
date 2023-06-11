package com.example.backend.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.Entities.Client;

public interface ClientRepo extends JpaRepository<Client, Long> {
    

    public Optional<Client> findByEmail(String email);
}
