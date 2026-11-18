package com.sena.tinylog.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sena.tinylog.dtos.DocenteDto;
import com.sena.tinylog.models.Docente;

@Mapper(componentModel = "spring")
public interface DocenteMapper {

    // Convertir de entidad a Dto  
    @Mapping(source = "nom", target = "nomb")
    @Mapping(source = "ape", target = "apell")
    @Mapping(source = "corr", target = "corre")
    @Mapping(source = "tel", target = "tele")
    DocenteDto convierteDto(Docente docente);

    // Convertir Dto a Entidad
    @Mapping(source = "nomb", target = "nom")
    @Mapping(source = "apell", target = "ape")
    @Mapping(source = "corre", target = "corr")
    @Mapping(source = "tele", target = "tel")
    Docente convierteaEntidad(DocenteDto docenteDto);

}