package com.ben3li.historiaespanha.controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ben3li.historiaespanha.dto.QuizDTO;
import com.ben3li.historiaespanha.dto.QuizResuelto;
import com.ben3li.historiaespanha.servicios.impl.QuizHistoriaServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/historia-espanha/quiz")
public class QuizController {

    private final QuizHistoriaServiceImpl quizHistoriaServiceImpl;

    @GetMapping("/nuevoQuiz/{epoca}")
    public QuizDTO nuevoQuiz(@PathVariable String epoca){
        return quizHistoriaServiceImpl.crearQiuz(epoca);
    }

    @PostMapping("/quizResuelto/{quiz_id}")
    public QuizResuelto corregirTest(@RequestBody QuizResuelto quizResuelto){
        return quizHistoriaServiceImpl.corregirQuiz(quizResuelto);
    }
}
