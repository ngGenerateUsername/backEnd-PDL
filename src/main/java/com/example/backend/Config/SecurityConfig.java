package com.example.backend.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationProvider;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
// @EnableMethodSecurity
public class SecurityConfig{
    
    private final jwtFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {  
        http.cors()
        .and()
        .csrf()
        .disable()
        .authorizeHttpRequests()
        .requestMatchers("api/client/**")
        .permitAll()
        // .requestMatchers("api/reclamation/**").hasAuthority("client")
        .anyRequest()
        .authenticated()
        .and()
        .sessionManagement() //cant login twice(more than one session)
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


         return http.build();
    }
}
