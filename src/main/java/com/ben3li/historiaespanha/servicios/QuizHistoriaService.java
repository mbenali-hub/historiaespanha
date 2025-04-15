package com.ben3li.historiaespanha.servicios;

import java.util.List;

import com.ben3li.historiaespanha.dto.QuizDTO;
import com.ben3li.historiaespanha.dto.QuizResuelto;
import com.ben3li.historiaespanha.entidades.Pregunta;

public interface QuizHistoriaService {
    QuizDTO crearQiuz(String epoca);
    List<Pregunta> buscarPreguntaPorEpoca(String epoca);
    QuizResuelto corregirQuiz(QuizResuelto quizResuelto);
}
