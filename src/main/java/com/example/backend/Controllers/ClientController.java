package com.example.backend.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.Config.MultiDetailService;
import com.example.backend.DTO.AuthResponse;
import com.example.backend.DTO.ClientAuthRequest;
import com.example.backend.Entities.Client;
import com.example.backend.Services.ClientService;
import com.example.backend.dao.ClientRepo;

@RestController
@RequestMapping("api/client")
public class ClientController {
    
    @Autowired
    private ClientService service;


    @PostMapping("register")
    private ResponseEntity<AuthResponse> addClient(@RequestBody Client bodyClient)
    {
        
        return ResponseEntity.ok(service.regiestClient(bodyClient));
    }
    //get All entities
    @GetMapping("all")
    private List<Client> getAllClient()
    {
        return service.getAllClients();
    }

    @PostMapping("login")

    private ResponseEntity<AuthResponse> loginClient(@RequestBody ClientAuthRequest request)
    {   
        return ResponseEntity.ok(service.loginClient(request));
    }

    // @GetMapping("testRole")
    // private ResponseEntity<Integer> testRole()
    // {
    //     System.out.println(mt.getCurrentRole());
    //     return ResponseEntity.ok(mt.getCurrentRole().length());
    // }

}
