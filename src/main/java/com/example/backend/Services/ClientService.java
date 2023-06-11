package com.example.backend.Services;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.backend.Entities.Client;
import com.example.backend.dao.ClientRepo;
import com.example.backend.Config.ApplicationConfig;
import com.example.backend.DTO.AuthResponse;
import com.example.backend.DTO.ClientAuthRequest;



// autowired or @requireArgs
@Service
public class ClientService {
    
    @Autowired
    private ClientRepo _jpaClient;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private jwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;


    public AuthResponse regiestClient(Client cl)
    {
        var clientRegister = Client.builder().name(cl.getName())
        .lastName(cl.getLastName())
        .email(cl.getEmail())
        .phoneNumber(cl.getPhoneNumber())
        .password(passwordEncoder.encode(cl.getPassword()))
        .build();

        _jpaClient.save(clientRegister);
        var jwtToken = jwtService.generateToken(clientRegister);
        System.out.println(jwtToken);

        return AuthResponse.builder().token(jwtToken).build();
    }

    public AuthResponse loginClient(ClientAuthRequest request)
    {
        // Collection <String> authorities = new ArrayList<String>();
        // authorities.add("client");

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        var user = _jpaClient.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder().token(jwtToken).build();
    }

    //after auth
    public List<Client> getAllClients()
    {
        return _jpaClient.findAll();
    }


}
