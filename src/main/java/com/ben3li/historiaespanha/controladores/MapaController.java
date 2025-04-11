package com.ben3li.historiaespanha.controladores;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ben3li.historiaespanha.entidades.Evento;
import com.ben3li.historiaespanha.servicios.impl.EventoServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/historia-espanha")
@RequiredArgsConstructor
public class MapaController {

    private final EventoServiceImpl eventoServiceImpl;
    @GetMapping("/eventos/{fecha_inicio}")
    public List<Evento> eventosPorFecha(@PathVariable Long fecha_inicio){
        return eventoServiceImpl.buscarEventosPorFechaInicio(fecha_inicio);
    }
}
