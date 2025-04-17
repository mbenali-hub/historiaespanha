package com.ben3li.historiaespanha.servicios;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.UUID;

import com.ben3li.historiaespanha.dto.QuizDTO;
import com.ben3li.historiaespanha.dto.QuizResuelto;
import com.ben3li.historiaespanha.entidades.Pregunta;

import jakarta.servlet.http.HttpServletRequest;

public interface QuizHistoriaService {
    QuizDTO crearQiuz(String epoca,UUID userId);
    List<Pregunta> buscarPreguntasPorEpoca(String epoca);
    List<Pregunta> buscarPreguntasAleatorias();
    QuizResuelto corregirQuiz(QuizResuelto quizResuelto);
}
