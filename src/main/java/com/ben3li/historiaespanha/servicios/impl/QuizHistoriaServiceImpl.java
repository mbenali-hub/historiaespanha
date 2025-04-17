package com.ben3li.historiaespanha.servicios.impl;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ben3li.historiaespanha.dto.PreguntaDTO;
import com.ben3li.historiaespanha.dto.QuizDTO;
import com.ben3li.historiaespanha.dto.QuizResuelto;
import com.ben3li.historiaespanha.entidades.Pregunta;
import com.ben3li.historiaespanha.entidades.Quiz;
import com.ben3li.historiaespanha.entidades.Respuesta;
import com.ben3li.historiaespanha.entidades.Usuario;
import com.ben3li.historiaespanha.mappers.PreguntaMapper;
import com.ben3li.historiaespanha.mappers.QuizMapper;
import com.ben3li.historiaespanha.repositorios.PreguntaRepositorio;
import com.ben3li.historiaespanha.repositorios.QuizRepositorio;
import com.ben3li.historiaespanha.repositorios.RespuestaRepositorio;
import com.ben3li.historiaespanha.repositorios.UsuarioRepositorio;
import com.ben3li.historiaespanha.servicios.QuizHistoriaService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizHistoriaServiceImpl implements QuizHistoriaService{

    private final int PREGUNTAS_POR_TEST=15;
    private final PreguntaRepositorio preguntaRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final QuizRepositorio quizRepositorio;
    private final PreguntaMapper preguntaMapper;


    @Override
    public QuizDTO crearQiuz(String epoca,UUID userId) {
        QuizDTO nuevoQuiz=null;
        if(epoca!=null){
            //Obtener 15 preguntas de la bbdd
            List<Pregunta> preguntas=buscarPreguntasPorEpoca(epoca);
            nuevoQuiz = guardarNuevoQuiz(preguntas,userId);
        }
        else{
            List<Pregunta> preguntas=buscarPreguntasAleatorias();
            nuevoQuiz = guardarNuevoQuiz(preguntas,userId);
        }
        return nuevoQuiz;
    }


    private QuizDTO guardarNuevoQuiz(List<Pregunta> preguntas,UUID userId) {
        Quiz quizGuardado;
        List<PreguntaDTO> preguntasDTO;
        QuizDTO nuevoQuiz;
        preguntasDTO = preguntas.stream()
                        .map(preguntaMapper::toDto)
                        .toList();

       
        Usuario usuario=usuarioRepositorio.findById(userId).orElseThrow(()-> new RuntimeException("usuario no encontrado"));
        Quiz quizEntity = Quiz.builder()
                        .puntuacion(0)
                        .puntuacionMaxima(preguntas.size())
                        .usuario(usuario)
                        .preguntas(preguntas)
                        .build();

        quizGuardado=quizRepositorio.save(quizEntity);
        nuevoQuiz=QuizDTO.builder()
                    .id(quizGuardado.getId())
                    .puntuacion(0)
                    .puntuacionMaxima(quizGuardado.getPuntuacionMaxima())
                    .preguntas(preguntasDTO)
                    .build();
        return nuevoQuiz;
    }

    
    @Override
    public List<Pregunta> buscarPreguntasPorEpoca(String epoca) {
        return preguntaRepositorio.obtenerPreguntasPorEpoca(epoca,PREGUNTAS_POR_TEST);
    }

    @Override
    public List<Pregunta> buscarPreguntasAleatorias() {
        return preguntaRepositorio.obtenerPreguntasAleatorias(PREGUNTAS_POR_TEST);
    }


    @Override
    public QuizResuelto corregirQuiz(QuizResuelto quizResuelto) {
        int respuestasCorrectas=0;
        Map<Integer,String> respuestasUsuario = quizResuelto.getRespuestasAPreguntas();
        Map<Integer, Boolean> respuestasCorregidas= new HashMap<>();
        
        Quiz quizGuardado= quizRepositorio.getReferenceById(quizResuelto.getId());

         Map<Integer, Pregunta> preguntaMap = quizGuardado.getPreguntas().stream()
        .collect(Collectors.toMap(Pregunta::getId, Function.identity()));

        for(Map.Entry<Integer,String> entry: respuestasUsuario.entrySet()){
            Integer preguntaId = entry.getKey();
            String respuesta = entry.getValue();

            Pregunta pregunta=preguntaMap.get(preguntaId);
            if(pregunta!=null){
                boolean esCorrecta=respuesta.equals(pregunta.getRespuesta().getRespuesta());
                if(esCorrecta) respuestasCorrectas++;
                respuestasCorregidas.put(pregunta.getId(),esCorrecta);               
            }          
        }

        quizResuelto.setRespuestasCorregidas(respuestasCorregidas);
        quizResuelto.setPuntuacion(respuestasCorrectas);
        quizResuelto.setPuntuacionMaxima(quizGuardado.getPuntuacionMaxima());
        return quizResuelto;
    }

}
