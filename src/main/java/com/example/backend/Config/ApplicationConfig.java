package com.example.backend.Config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.backend.dao.AdminRepo;
import com.example.backend.dao.ClientRepo;

import lombok.RequiredArgsConstructor;
@Configuration
@RequiredArgsConstructor 
public class ApplicationConfig {
    

    private final ClientRepo repository;

    @Bean
    public UserDetailsService userDetailsService()
    {  
        return username -> repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("user Not Found"));
        
    }


    //data access object responsible to fetch userDetails,encode password...
    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authProvier = new DaoAuthenticationProvider();
        authProvier.setUserDetailsService(userDetailsService());

        authProvier.setPasswordEncoder(passwordEncoder());
        return authProvier;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
    {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

}
