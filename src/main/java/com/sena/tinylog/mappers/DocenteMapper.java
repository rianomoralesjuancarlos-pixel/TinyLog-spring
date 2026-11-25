package com.sena.tinylog.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sena.tinylog.dtos.DocenteDto;
import com.sena.tinylog.models.usuarios.Docente;


@Mapper(componentModel = "spring")
public interface DocenteMapper {

    @Mapping(source = "tipoDoc.id", target = "tipoDoc")
    @Mapping(source = "ciudad.nombre", target = "ciudad")
    DocenteDto convierteDto(Docente docente);

    @Mapping(target = "tipoDoc", ignore = true)
    @Mapping(target = "ciudad", ignore = true)
    Docente convierteEntidad(DocenteDto docenteDto);

}


