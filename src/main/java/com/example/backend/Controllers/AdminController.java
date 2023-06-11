package com.example.backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.Config.MultiDetailService;
import com.example.backend.DTO.AdminAuthRequest;
import com.example.backend.DTO.AuthResponse;
import com.example.backend.Services.AdminService;


@RestController
@RequestMapping("api/admin")
public class AdminController {
    
    @Autowired
    private AdminService _adminService;


    @PostMapping("")
    private ResponseEntity<AuthResponse> authenticateAdmin(@RequestBody AdminAuthRequest request)
    {
      
        try {
            return new ResponseEntity<AuthResponse>(_adminService.loginAdmin(request),HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<AuthResponse>(AuthResponse.builder().token(e.getMessage()).build(),HttpStatus.BAD_REQUEST);
        }
        
    }

}
