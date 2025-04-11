package com.ben3li.historiaespanha.servicios.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ben3li.historiaespanha.entidades.Evento;
import com.ben3li.historiaespanha.repositorios.EventoRepositorio;
import com.ben3li.historiaespanha.servicios.EventoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventoServiceImpl implements EventoService{

    private final EventoRepositorio eventoRepositorio;
    @Override
    public List<Evento> buscarEventosPorFechaInicio(Long fecha_inicio) {
       return eventoRepositorio.findByFechaInicio(fecha_inicio);
    }

    @Override
    public Evento buscarEventoPorNombre(String nombre) {
       return eventoRepositorio.findByNombre(nombre);
    }

}
