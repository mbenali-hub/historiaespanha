package com.ben3li.historiaespanha.servicios;

import java.util.List;
import com.ben3li.historiaespanha.entidades.Evento;


public interface EventoService {
    List<Evento> buscarEventosPorFechaInicio(Long fecha_inicio);
    Evento buscarEventoPorNombre(String nombre);
}
