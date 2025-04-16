package com.ben3li.historiaespanha.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreguntaDTO {

    private Integer id;
    private String pregunta;   
    private String epoca;
    private List<String> opciones;
}
