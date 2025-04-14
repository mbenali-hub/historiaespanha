package com.ben3li.historiaespanha.servicios.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ben3li.historiaespanha.entidades.Evento;
import com.ben3li.historiaespanha.entidades.Personaje;
import com.ben3li.historiaespanha.repositorios.EventoRepositorio;
import com.ben3li.historiaespanha.repositorios.PersonajeRepositorio;
import com.ben3li.historiaespanha.servicios.MapaEspanhaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MapaEspanhaServiceImpl implements MapaEspanhaService{

    private final EventoRepositorio eventoRepositorio;
    private final PersonajeRepositorio personajeRepositorio;
    @Override
    public List<Evento> buscarEventosPorFechaInicio(Long fecha_inicio) {
       return eventoRepositorio.findByFechaInicio(fecha_inicio);
    }

    @Override
    public List<Evento> buscarEventoPorNombre(String nombre) {
       return eventoRepositorio.findAllByNombre(nombre);
    }

    @Override
    public List<Personaje> buscarPersonajesDeEvento(String nombreEvento) {
         return personajeRepositorio.findAllByEventoNombre(nombreEvento);
    }

    @Override
    public List<Personaje> buscarPersonajePorNombre(String nombrePersonaje) {
         return personajeRepositorio.findAllByNombre(nombrePersonaje);
    }

}
