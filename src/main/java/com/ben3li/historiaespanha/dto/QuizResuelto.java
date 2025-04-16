package com.ben3li.historiaespanha.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizResuelto {
    private UUID id;
    private int puntuacion;
    private int puntuacionMaxima;
    private Map<Integer,String> respuestasAPreguntas= new HashMap<>();
    private Map<Integer,Boolean> respuestasCorregidas= new HashMap<>();
}
