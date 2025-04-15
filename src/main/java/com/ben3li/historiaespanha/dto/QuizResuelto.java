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
    private Map<Integer,Integer> respuestasAPreguntas= new HashMap<>();
    private Map<Integer,Boolean> respuestasCorregidas= new HashMap<>();
}
