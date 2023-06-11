package com.example.backend.Entities;

import org.hibernate.Hibernate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Reclamation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"reclamations"})
    @JoinColumn(name = "client_id",referencedColumnName = "id",nullable = true)
    private Client client;
}
