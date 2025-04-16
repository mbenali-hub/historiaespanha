package com.ben3li.historiaespanha.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ben3li.historiaespanha.entidades.Pregunta;

@Repository
public interface PreguntaRepositorio extends JpaRepository<Pregunta,Integer> {
    @Query(
        value="SELECT * FROM preguntas where epoca =: epoca ORDER BY RANDOM() LIMIT :preguntasPorTest",
        nativeQuery = true
    )
    List<Pregunta> obtenerPreguntasPorEpoca(String epoca,int preguntasPorTest);

    @Query(
        value="SELECT * FROM preguntas ORDER BY RANDOM() LIMIT :preguntasPorTest",
        nativeQuery = true
    )
    List<Pregunta> obtenerPreguntasAleatorias(int preguntasPorTest);
    
}
