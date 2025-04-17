package com.ben3li.historiaespanha.config;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.LocalDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.HttpRequestHandler;

import com.ben3li.historiaespanha.entidades.Usuario;
import com.ben3li.historiaespanha.repositorios.UsuarioRepositorio;
import com.ben3li.historiaespanha.seguridad.HEUserDetailsService;
import com.ben3li.historiaespanha.seguridad.JwtAuthenticationFilter;
import com.ben3li.historiaespanha.servicios.AuthenticationService;

import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception{
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/historia-espanha/login/**").permitAll()
                .requestMatchers(HttpMethod.GET,"/historia-espanha/eventos/**").permitAll()
                .requestMatchers(HttpMethod.GET,"/historia-espanha/personajes/**").permitAll()
                .anyRequest().authenticated()
        )
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session-> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean 
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean 
    public UserDetailsService userDetailsService(UsuarioRepositorio usuarioRepositorio){
        HEUserDetailsService heUserDetailsService= new HEUserDetailsService(usuarioRepositorio);

        String email="test@gmail.com";
        usuarioRepositorio.findByEmail(email)
                            .orElseGet(()->{
                                Usuario usuario= Usuario.builder()
                                .nombre("mohamed")                                
                                .creadoA(LocalDateTime.now())
                                .email("test@gmail.com")
                                .password(codificador().encode("123qwe"))
                                .build();                            
                                return usuarioRepositorio.save(usuario);
                            });

        return heUserDetailsService;
    }

    @Bean
    public PasswordEncoder codificador(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationService authenticationService){
        return new JwtAuthenticationFilter(authenticationService);
    }

}
