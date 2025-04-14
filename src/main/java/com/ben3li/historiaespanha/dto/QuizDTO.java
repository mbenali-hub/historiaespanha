package com.ben3li.historiaespanha.dto;

import java.util.List;
import com.ben3li.historiaespanha.entidades.Pregunta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizDTO {
    private int puntuacion;
    private List<Pregunta> preguntas;
}
