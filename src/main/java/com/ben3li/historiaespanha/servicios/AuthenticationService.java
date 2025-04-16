package com.ben3li.historiaespanha.servicios;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {
    UserDetails autenticar(String email, String password);
    UserDetails validateToken(String token);
    String generarToken(UserDetails userDetails);
}
