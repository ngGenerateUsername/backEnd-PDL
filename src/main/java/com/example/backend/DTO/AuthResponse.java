package com.example.backend.DTO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonSerialize
public class AuthResponse {

    public String token;
    
}
