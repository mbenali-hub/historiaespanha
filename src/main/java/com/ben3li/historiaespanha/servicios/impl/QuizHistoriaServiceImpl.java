package com.ben3li.historiaespanha.servicios.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ben3li.historiaespanha.dto.PreguntaDTO;
import com.ben3li.historiaespanha.dto.QuizDTO;
import com.ben3li.historiaespanha.dto.QuizResuelto;
import com.ben3li.historiaespanha.entidades.Pregunta;
import com.ben3li.historiaespanha.entidades.Quiz;
import com.ben3li.historiaespanha.entidades.Respuesta;
import com.ben3li.historiaespanha.mappers.PreguntaMapper;
import com.ben3li.historiaespanha.mappers.QuizMapper;
import com.ben3li.historiaespanha.repositorios.PreguntaRepositorio;
import com.ben3li.historiaespanha.repositorios.QuizRepositorio;
import com.ben3li.historiaespanha.repositorios.RespuestaRepositorio;
import com.ben3li.historiaespanha.servicios.QuizHistoriaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizHistoriaServiceImpl implements QuizHistoriaService{

    private final int PREGUNTAS_POR_TEST=15;
    private final PreguntaRepositorio preguntaRepositorio;
    private final RespuestaRepositorio respuestaRepositorio;
    private final QuizRepositorio quizRepositorio;
    private List<PreguntaDTO> preguntasObtenidas= new ArrayList<>();
    private final PreguntaMapper preguntaMapper;
    private final QuizMapper quizMapper;

    @Override
    public QuizDTO crearQiuz(String epoca) {
        QuizDTO nuevoQuiz=null;
        List<Pregunta> preguntas=null;
        if(epoca!=null){
            //Obtener 15 preguntas de la bbdd
            preguntas=buscarPreguntaPorEpoca(epoca);
            preguntasObtenidas= preguntas.stream()
                                .map(preguntaMapper::toDto)
                                .toList();
            nuevoQuiz = QuizDTO.builder()
                                .puntuacion(0)
                                .preguntas(preguntasObtenidas)
                                .build();

        }

        Quiz quizEntity = quizMapper.toEntity(nuevoQuiz);
        quizEntity.setPreguntas(preguntas);
        
        Quiz quizGuardado=quizRepositorio.save(quizEntity);
        nuevoQuiz.setId(quizGuardado.getId());
        return nuevoQuiz;
    }

    
    @Override
    public List<Pregunta> buscarPreguntaPorEpoca(String epoca) {
        return preguntaRepositorio.findByEpoca(epoca);
    }


    @Override
    public QuizResuelto corregirQuiz(QuizResuelto quizResuelto) {
        Map<Integer,Integer> respuestasAPreguntas = quizResuelto.getRespuestasAPreguntas();
        System.out.println("RespuestasPreguntas:   "+respuestasAPreguntas);
        Map<Integer, Boolean> respuestasCoregidas= new HashMap<>();
        
        Quiz quizGuardado= quizRepositorio.getReferenceById(quizResuelto.getId());
        List<Pregunta> preguntasQuizGuardado= quizGuardado.getPreguntas();

         Map<Integer, Pregunta> preguntaMap = preguntasQuizGuardado.stream()
        .collect(Collectors.toMap(Pregunta::getId, Function.identity()));

        for(Map.Entry<Integer,Integer> entry: respuestasAPreguntas.entrySet()){
            Integer preguntaId = entry.getKey();
            Integer respuestaId = entry.getValue();

            Pregunta pregunta=preguntaMap.get(preguntaId);
            System.out.println("Preguynta: "+pregunta);
            if(pregunta!=null){
                Respuesta respuestaObtenida=respuestaRepositorio.findById(respuestaId).orElseThrow();
                Respuesta respuestaCorrecta = pregunta.getRespuesta();
    
                respuestasCoregidas.put(pregunta.getId(), respuestaObtenida.equals(respuestaCorrecta));
            }
        }

        quizResuelto.setRespuestasCorregidas(respuestasCoregidas);
        System.out.println("Respuestas:   "+respuestasCoregidas);
        return quizResuelto;
    }

}
