package com.ben3li.historiaespanha.repositorios;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ben3li.historiaespanha.entidades.Quiz;

@Repository
public interface QuizRepositorio extends JpaRepository<Quiz,UUID>{

}
