package com.ben3li.historiaespanha.seguridad;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ben3li.historiaespanha.servicios.AuthenticationService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private final AuthenticationService authenticationService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
           
        String token=extraerToken(request);
        if(token!=null){
            UserDetails userdetails = authenticationService.validateToken(token);
            UsernamePasswordAuthenticationToken authentication= new UsernamePasswordAuthenticationToken(userdetails, null, userdetails.getAuthorities());
            
            SecurityContextHolder.getContext().setAuthentication(authentication);

            if(userdetails instanceof HEUserDetails){
                request.setAttribute("usuarioId", ((HEUserDetails) userdetails).getId());
            }
        }
        filterChain.doFilter(request, response);
      
    }

    private String extraerToken(HttpServletRequest request){
        String bearer= request.getHeader("Autorization");
        String token=null;
        if(bearer!=null && bearer.startsWith("Bearer: ")){
            token=bearer.substring(7);
        }
        return token;
    }

}
