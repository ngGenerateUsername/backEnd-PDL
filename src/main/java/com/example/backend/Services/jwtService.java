package com.example.backend.Services;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.backend.Entities.Client;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class jwtService {
    

    private static final String SECRET_KE="2D4B6150645367566B59703373367638792F423F4528482B4D6251655468576D";

//parseBuilder : Perform Base64Url decoding with the specified Decoder
//setSigningKey: use it to decode the token
    private Claims extractAllClaims(String token) //parseBuilder : Perform Base64Url decoding with the specified Decoder
    {
        return Jwts
        .parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJws(token)
        .getBody()
        ;  
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KE);
        System.out.println(new String(keyBytes));
        System.out.println("hmacSha: ");
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public <T> T extractClaim(String token,Function<Claims, T> claimsResolver)
    {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //extract payload
    public String extractUsername(String token)
    {   
        System.out.println(token);
        return extractClaim(token, Claims::getSubject);
    }



    //generate token without extraClaim
     public String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }




    public String generateToken(
        Map<String,Object> extraClaims,
        UserDetails userDetails
    )
    {

        return Jwts
        .builder()
        .setClaims(extraClaims)
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis()+1000 * 60 * 33333))
        .signWith(getSignInKey(), SignatureAlgorithm.HS256)
        .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails)
    {
        final String emailUser = extractUsername(token);
        return (emailUser.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token)
    {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token)
    {
        return extractClaim(token, Claims::getExpiration);
    }
}
