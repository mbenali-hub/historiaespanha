package com.ben3li.historiaespanha.seguridad;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ben3li.historiaespanha.entidades.Usuario;
import com.ben3li.historiaespanha.repositorios.UsuarioRepositorio;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HEUserDetailsService implements UserDetailsService{

    private final UsuarioRepositorio usuarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       Usuario usuario = usuarioRepositorio.findByEmail(email)  
                            .orElseThrow(()-> new UsernameNotFoundException("Username not fuond with email: "+email));                     
       return new HEUserDetails(usuario);
    }

}
