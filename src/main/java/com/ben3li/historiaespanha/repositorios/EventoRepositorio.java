package com.ben3li.historiaespanha.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ben3li.historiaespanha.entidades.Evento;

import java.util.List;

@Repository
public interface EventoRepositorio extends JpaRepository<Evento,Integer>{
    List<Evento> findByFechaInicio(Long fechaInicio);
    List<Evento> findAllByNombre(String nombre);
    
}
