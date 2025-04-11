package com.ben3li.historiaespanha.entidades;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Localizaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Localizacion {

    @Id
    private int id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private double latitud;

    @Column(nullable = false)
    private double longitud;
    
    @Column(nullable = false)
    private boolean esPais;

    @OneToMany(mappedBy = "localizacion",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Evento> eventos;
}
