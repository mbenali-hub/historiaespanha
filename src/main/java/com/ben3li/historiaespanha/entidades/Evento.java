package com.ben3li.historiaespanha.entidades;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Eventos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Evento {

    @Id
    private int id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    private String tipo;

    @Column(nullable = false)
    private long fechaInicio;

    @Column(nullable = true)
    private Long fechaFin;

    @ManyToOne
    @JoinColumn(name="localizacion_id", nullable = true)
    @JsonManagedReference
    private Localizacion localizacion;

    @ManyToOne
    @JoinColumn(name="periodo_id", nullable=false)
    @JsonManagedReference
    private Periodo periodo;

    @ManyToMany(mappedBy = "eventos")
    @JsonManagedReference
    private List<Personaje> personajes;

}
