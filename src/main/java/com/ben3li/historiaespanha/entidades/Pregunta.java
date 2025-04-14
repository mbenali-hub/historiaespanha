package com.ben3li.historiaespanha.entidades;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
    
    @Column(nullable = false)
    private String epoca;

    @OneToOne
    @JoinColumn(name="respuesta_id", nullable = false)
    private Respuesta respuesta;

    @ElementCollection
    @CollectionTable(name = "opciones_pregunta", joinColumns = @JoinColumn(name="pregunta_id"))
    @Column(name = "opcion")
    private List<String> opciones;

    @ManyToMany(mappedBy = "preguntas")
    private Quiz quizzes;

}
