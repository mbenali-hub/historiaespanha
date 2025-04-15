package com.ben3li.historiaespanha.controladores;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ben3li.historiaespanha.entidades.Evento;
import com.ben3li.historiaespanha.entidades.Personaje;
import com.ben3li.historiaespanha.servicios.impl.MapaEspanhaServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/historia-espanha")
@RequiredArgsConstructor
public class MapaController {

    private final MapaEspanhaServiceImpl mapaEspanhaServiceImpl;

    @GetMapping("/eventos/fecha/{fecha_inicio}")
    public List<Evento> eventosPorFecha(@PathVariable Long fecha_inicio){
        return mapaEspanhaServiceImpl.buscarEventosPorFechaInicio(fecha_inicio);
    }

    @GetMapping("/eventos/nombre/{nombreEvento}")
    public List<Evento> eventosPorNombreEvento(@PathVariable String nombreEvento){
        return mapaEspanhaServiceImpl.buscarEventoPorNombre(nombreEvento);
    }

    @GetMapping("/eventos/personajes/{nombreEvento}")
    public List<Personaje> personajesEnEvento(@PathVariable String nombreEvento){
        return mapaEspanhaServiceImpl.buscarPersonajesDeEvento(nombreEvento);
    }

    @GetMapping("/personajes/{nombrePersonaje}")
    public List<Personaje> personajesPorNombre(@PathVariable String nombrePersonaje){
        System.out.println("eYSNAINDJIASPIDBNASIBDIAbsdihasbfoiabsdofb");
        return mapaEspanhaServiceImpl.buscarPersonajePorNombre(nombrePersonaje);
    }
}
