package com.ben3li.historiaespanha.servicios;

import java.util.List;
import com.ben3li.historiaespanha.entidades.Evento;
import com.ben3li.historiaespanha.entidades.Personaje;


public interface MapaEspanhaService {
    List<Evento> buscarEventosPorFechaInicio(Long fecha_inicio);
    List<Evento> buscarEventoPorNombre(String nombre);
    List<Personaje> buscarPersonajesDeEvento(String nombreEvento);
    List<Personaje> buscarPersonajePorNombre(String nombrePersonaje);
}
