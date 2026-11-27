package com.sena.tinylog.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sena.tinylog.dtos.DirectivoDto;
import com.sena.tinylog.dtos.DirectivoDtoResponse;
import com.sena.tinylog.models.usuarios.Directivo;

@Mapper(componentModel = "spring")
public interface DirectivoMapper {

    @Mapping(source = "nomb", target = "nom")
    @Mapping(source = "apell", target = "ape")
    @Mapping(source = "direc", target = "dir")
    @Mapping(source = "tele", target = "tel")
    @Mapping(source = "numDoc", target = "numDoc")
    @Mapping(source = "cargo", target = "cargo")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tipoDoc", ignore = true)
    @Mapping(target = "barrio", ignore = true)
    @Mapping(target = "ciudad", ignore = true)
    Directivo toEntidad(DirectivoDto directivoDto);

    @Mapping(source = "nom", target = "nomb")
    @Mapping(source = "ape", target = "apell")
    @Mapping(source = "dir", target = "direc")
    @Mapping(source = "tel", target = "tele")
    @Mapping(source = "numDoc", target = "numDoc")
    @Mapping(source = "cargo", target = "cargo")
    @Mapping(source = "tipoDoc.id", target = "tipoDoc")
    @Mapping(source = "barrio", target = "barrio")
    @Mapping(source = "ciudad.", target = "ciudad")
    DirectivoDtoResponse toDto(Directivo directivo);

}