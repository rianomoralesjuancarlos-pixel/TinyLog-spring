package com.sena.tinylog.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sena.tinylog.dtos.DocenteDto;
import com.sena.tinylog.models.usuarios.Docente;


@Mapper(componentModel = "spring")
public interface DocenteMapper {

    // Docente (entidad) -> DocenteDto (salida)
    @Mapping(source = "nom", target = "nomb")
    @Mapping(source = "ape", target = "apell")
    @Mapping(source = "dir", target = "direc")
    @Mapping(source = "tel", target = "tele")
    @Mapping(source = "numDoc", target = "numDoc")
    @Mapping(source = "salario", target = "salario")
    @Mapping(source = "tipoDoc.id", target = "tipoDoc")
    @Mapping(source = "barrio.codigo", target = "barrio")
    @Mapping(source = "ciudad.codigo", target = "ciudad")
    @Mapping(target = "user", ignore = true)
    @Mapping(source = "cargo", target = "cargo")
    @Mapping(target = "password", ignore = true)
    DocenteDto toDto(Docente docente);

    // DocenteDto (entrada) -> Docente (entidad)
    @Mapping(source = "nomb", target = "nom")
    @Mapping(source = "apell", target = "ape")
    @Mapping(source = "direc", target = "dir")
    @Mapping(source = "tele", target = "tel")
    @Mapping(source = "numDoc", target = "numDoc")
    @Mapping(source = "salario", target = "salario")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tipoDoc", ignore = true)
    @Mapping(target = "barrio", ignore = true)
    @Mapping(target = "ciudad", ignore = true)
    Docente toEntidad(DocenteDto docenteDto);
}

