package com.ben3li.historiaespanha.entidades;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Preguntas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pregunta {
    @Id
    private int id;
    
    @Column(nullable = false)
    private String pregunta;

    @OneToOne
    @JoinColumn(name="respuesta_id", nullable = false)
    private Respuesta respuesta;

    @OneToOne
    @JoinColumn(name="periodo_id", nullable = false)
    private Periodo periodo;
}
