package com.ben3li.historiaespanha.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ben3li.historiaespanha.entidades.Pregunta;

@Repository
public interface PreguntaRepositorio extends JpaRepository<Pregunta,Integer> {
    List<Pregunta> findByEpoca(String epoca);
    
}
