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
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)  throws ServletException, IOException {
        String token=extraerToken(request);
        try {
            if(token!=null){
                System.out.println("TOKEN RECIBIDO: " + token);

                UserDetails userDetails= authenticationService.validateToken(token);

                SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities())
                );

                if(userDetails instanceof HEUserDetails){
                    request.setAttribute("userId", ((HEUserDetails)userDetails).getId());
                }            
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        filterChain.doFilter(request, response);
    }
    
   

    private String extraerToken(HttpServletRequest request){
        String token="";
        String bearer= request.getHeader("Authorization");

        if(bearer!=null && bearer.startsWith("Bearer ")){
            token=bearer.substring(7);
        }
        return token;
    }


}
