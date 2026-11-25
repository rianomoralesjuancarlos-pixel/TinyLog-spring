package com.sena.tinylog.mappers;



import org.mapstruct.Mapper;

import com.sena.tinylog.dtos.DirectivoDto;
import com.sena.tinylog.dtos.DirectivoDtoResponse;

import com.sena.tinylog.models.usuarios.Directivo;

import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface DirectivoMapper  {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tipoDoc", ignore = true)
    @Mapping(target = "ciudad", ignore = true)
    Directivo toEntidad(DirectivoDto directivoDto);

    DirectivoDtoResponse toDto(Directivo directivo);
}