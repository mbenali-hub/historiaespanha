package com.ben3li.historiaespanha.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ben3li.historiaespanha.entidades.Personaje;

@Repository
public interface PersonajeRepositorio extends JpaRepository<Personaje,Integer>{

    @Query("SELECT p FROM Personaje p JOIN p.eventos e WHERE LOWER(e.nombre) LIKE LOWER(CONCAT('%',:nombre,'%'))")
    List<Personaje> findAllByEventoNombre(String nombre);

    List<Personaje> findAllByNombre(String nombre);
}
