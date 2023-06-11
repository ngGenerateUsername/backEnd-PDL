package com.example.backend.Config;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.backend.Services.jwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class jwtFilter extends OncePerRequestFilter {
    
    private final jwtService jwtService;

    private final UserDetailsService userDetailsService;
    
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain)
            throws ServletException, IOException {

 
                final String authHeader = request.getHeader("Authorization");
                final String jwt;
                final String userEmail;
                if(authHeader == null || !authHeader.startsWith("Bearer"))
                {
                    filterChain.doFilter(request, response);
                    return;
                }
                jwt = authHeader.substring(7);
                // userEmail = extract user email
                userEmail = jwtService.extractUsername(jwt);

                if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null)
                {
                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                    if(jwtService.isTokenValid(jwt, userDetails))
                    {
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                        );
                        //enforce with the details of our request
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    } 
                }
                System.out.println(SecurityContextHolder.getContext().getAuthentication());
                filterChain.doFilter(request, response);

    }
    

}
