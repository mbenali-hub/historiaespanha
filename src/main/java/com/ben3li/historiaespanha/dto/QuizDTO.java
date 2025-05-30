package com.ben3li.historiaespanha.dto;

import java.util.List;
import java.util.UUID;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizDTO {
    private UUID id;
    private int puntuacion;
    private int puntuacionMaxima;
    private List<PreguntaDTO> preguntas;
}
