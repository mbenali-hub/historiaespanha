package com.ben3li.historiaespanha.controladores;

import java.io.ObjectInputFilter.Status;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ben3li.historiaespanha.dto.AuthResponse;
import com.ben3li.historiaespanha.dto.LoginRequest;
import com.ben3li.historiaespanha.servicios.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/historia-espanha/login")
public class LoginController {

    private final AuthenticationService authenticationService;
    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){

        UserDetails userDetails =authenticationService.autenticar(loginRequest.getEmail(), loginRequest.getPassword());
        String token = authenticationService.generarToken(userDetails);

        AuthResponse authResponse= AuthResponse.builder()
                                    .token(token)
                                    .expiraEn(43000)
                                    .build();

        return ResponseEntity.ok(authResponse);
    }

}
