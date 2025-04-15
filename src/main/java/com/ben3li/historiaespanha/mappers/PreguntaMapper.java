package com.ben3li.historiaespanha.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.ben3li.historiaespanha.dto.PreguntaDTO;
import com.ben3li.historiaespanha.entidades.Pregunta;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PreguntaMapper {   
    PreguntaDTO toDto(Pregunta pregunta);
}
