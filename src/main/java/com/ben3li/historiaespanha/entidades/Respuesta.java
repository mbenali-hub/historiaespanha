package com.ben3li.historiaespanha.entidades;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Respuestas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Respuesta {
    @Id
    private int id;
    
    @Column(nullable = false)
    private String respuesta;

    @OneToOne(mappedBy = "respuesta")
    private Pregunta pregunta;

}
