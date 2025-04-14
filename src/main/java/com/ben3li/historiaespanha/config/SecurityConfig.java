package com.ben3li.historiaespanha.config;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.HttpRequestHandler;

import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(auth -> auth
            //.requestMatchers(HttpMethod.GET,"/historia-espanha/eventos/**").permitAll()
            //.requestMatchers(HttpMethod.GET,"/historia-espanha/personajes/**").permitAll()
            .anyRequest().permitAll()
            
        )
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        return http.build();        
    }
}
