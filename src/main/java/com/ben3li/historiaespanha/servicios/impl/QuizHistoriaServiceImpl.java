package com.ben3li.historiaespanha.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import com.ben3li.historiaespanha.dto.PreguntaDTO;
import com.ben3li.historiaespanha.dto.QuizDTO;
import com.ben3li.historiaespanha.repositorios.PreguntaRepositorio;
import com.ben3li.historiaespanha.servicios.QuizHistoriaService;

public class QuizHistoriaServiceImpl implements QuizHistoriaService{

    private final int PREGUNTAS_POR_TEST=15;
    private PreguntaRepositorio preguntaRepositorio;
    private List<PreguntaDTO> preguntasObtenidas= new ArrayList<>();
    @Override
    public QuizDTO crearQiuz(String epoca) {

        if(epoca!=null){
            for(int i=0;i<PREGUNTAS_POR_TEST;i++){
                //Obtener 15 preguntas de la bbdd
                //Pregunta pregunta=preguntaRepositorio.obtenerPreguntaAleatoria(epoca);
                //PreguntaDTO preguntaDTO= map.from(pregunta);
                //preguntasObtenidas.add(preguntaDTO);
            }
        }
        return null;
    }

}
