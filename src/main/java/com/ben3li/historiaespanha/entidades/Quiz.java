package com.ben3li.historiaespanha.entidades;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Quiz")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int puntuacion;

    // @ManyToOne
    // private Usuario usuario;

    @ManyToMany
    @JoinTable(
        name="quizes_preguntas",
        joinColumns = @JoinColumn(name="quiz_id"),
        inverseJoinColumns = @JoinColumn(name="pregunta_id")
    )
    private List<Pregunta> preguntas;
}
