package com.ben3li.historiaespanha.servicios.impl;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import com.ben3li.historiaespanha.servicios.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final long jwtexpiracion=430000l;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Override
    public UserDetails autenticar(String email, String password) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, password)
       );
       return userDetailsService.loadUserByUsername(email);
    }

    @Override
    public String generarToken(UserDetails userDetails) {
       Map<String, Object> claims= new HashMap<>();
       return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+jwtexpiracion))
                .setClaims(claims)
                .signWith(getKeyCodificada())
                .setSubject(userDetails.getUsername())
                .compact();             
    }

    @Override
    public UserDetails validateToken(String token) {
      String nombre=extraerNombre(token);  
      return userDetailsService.loadUserByUsername(nombre);
    }

    private Key getKeyCodificada(){
        byte[] keyEnBytes="d4f8a9c4b1e2d83e1a6f7c9a02d1b7e3".getBytes();
         return Keys.hmacShaKeyFor(keyEnBytes);
    }


    private String extraerNombre(String token){
        Claims claims = Jwts.parserBuilder()
                        .setSigningKey(getKeyCodificada())
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
        return claims.getSubject();
    }
}
