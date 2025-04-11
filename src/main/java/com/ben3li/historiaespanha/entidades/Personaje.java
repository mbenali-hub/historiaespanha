package com.ben3li.historiaespanha.entidades;

import java.util.List;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Personajes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Personaje {

    @Id
    private int id;
    
    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    private String nacimiento;

    private String fallecimiento;

    @ManyToMany
    @JoinTable(
        name= "personaje_evento",
        joinColumns = @JoinColumn(name="personaje_id"),
        inverseJoinColumns = @JoinColumn(name="evento_id")
    )
    @JsonBackReference
    private List<Evento> eventos;

}
