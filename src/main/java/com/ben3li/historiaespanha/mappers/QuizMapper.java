package com.ben3li.historiaespanha.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.ben3li.historiaespanha.dto.QuizDTO;
import com.ben3li.historiaespanha.entidades.Quiz;

@Mapper(componentModel="spring", unmappedTargetPolicy=ReportingPolicy.IGNORE)
public interface QuizMapper {
    Quiz toEntity(QuizDTO quizDTO);

}
