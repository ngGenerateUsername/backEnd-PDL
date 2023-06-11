package com.example.backend.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.example.backend.Entities.Admin;
import com.example.backend.Entities.Client;
import com.example.backend.dao.AdminRepo;
import com.example.backend.dao.ClientRepo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//hedha kollou code zayed
@Component
public class MultiDetailService {

    private static ThreadLocal<String> currentRole= new ThreadLocal<String>();

    public String getCurrentRole() {
        return currentRole.get();
    }

    public void setCurrentRole(String role) {
        currentRole.set(role);
    }


    // @Autowired
    // private AdminRepo adminRepo;

    // @Autowired
    // private ClientRepo clientRepo;

    // @Override
    // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
    //     switch (currentRole) {
    //         case "admin" ->{
    //             Admin admin= adminRepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("Admin username Not Found!"));
    //             return admin;
    //         }
            
    //         case "client" ->{
    //             Client client=clientRepo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("Client Email Not Found!"));
    //             return client;
    //         }

    //         default->
    //              throw new UsernameNotFoundException("username not found");
                
    //     }
    // }
    
}
