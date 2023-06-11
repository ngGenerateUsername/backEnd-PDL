package com.example.backend.Services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.backend.DTO.AdminAuthRequest;
import com.example.backend.DTO.AuthResponse;
import com.example.backend.Entities.Admin;
import com.example.backend.dao.AdminRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
public class AdminService {
    
    @Autowired
    private AdminRepo _ormAdmin;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private jwtService jwtService;

    public AuthResponse loginAdmin(AdminAuthRequest requestAdmin)
    {
        if(requestAdmin.getUsername().isBlank() || requestAdmin.getPassword().isBlank())
        {
            return null;
        }

        try {
           authManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestAdmin.getUsername(), requestAdmin.getPassword())
            );
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("somthing wrong with authManager! ");
            System.out.println(e.getMessage());
            //return null;
        }
      
        //generate Token
        HashMap<String,Object> claimToken = new HashMap<String,Object>();
        claimToken.put("role", "admin");

        var admin = _ormAdmin.findByUsername(requestAdmin.getUsername());
        if(admin.isPresent())
        {
            System.out.println("is Present True");
        }
        else System.out.println("is Present False");

        var tokenGenerated = jwtService.generateToken(claimToken, admin.get());

        return AuthResponse.builder().token(tokenGenerated).build();

    }

}
