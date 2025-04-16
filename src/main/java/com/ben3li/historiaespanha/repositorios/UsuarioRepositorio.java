package com.ben3li.historiaespanha.repositorios;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ben3li.historiaespanha.entidades.Usuario;
import java.util.Optional;


@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,UUID>{
    Optional<Usuario> findByEmail(String email);
}
