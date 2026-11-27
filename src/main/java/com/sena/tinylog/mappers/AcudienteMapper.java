package com.sena.tinylog.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sena.tinylog.dtos.AcudienteDto;
import com.sena.tinylog.dtos.AcudienteDtoResponse;
import com.sena.tinylog.models.usuarios.Acudiente;

@Mapper(componentModel = "spring")
public interface AcudienteMapper {

    // AcudienteDto (entrada) -> Acudiente (entidad)
    @Mapping(source = "nomb", target = "nom")
    @Mapping(source = "apell", target = "ape")
    @Mapping(source = "direc", target = "dir")
    @Mapping(source = "tele", target = "tel")
    @Mapping(source = "numDoc", target = "numDoc")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tipoDoc", ignore = true)
    @Mapping(target = "tipoAcudiente", ignore = true)
    @Mapping(target = "barrio", ignore = true)
    @Mapping(target = "ciudad", ignore = true)
    Acudiente toEntidad(AcudienteDto acudienteDto);

    // Acudiente (entidad) -> AcudienteDtoResponse (salida)
    @Mapping(source = "id", target = "id")
    @Mapping(source = "nom", target = "nomb")
    @Mapping(source = "ape", target = "apell")
    @Mapping(source = "dir", target = "direc")
    @Mapping(source = "tel", target = "tele")
    @Mapping(source = "numDoc", target = "numDoc")
    @Mapping(source = "tipoDoc.id", target = "tipoDoc")
    @Mapping(source = "tipoAcudiente.nom_TipoAcu", target = "tipAcu")
    @Mapping(source = "barrio.codigo", target = "barrio")
    @Mapping(source = "ciudad.codigo", target = "ciudad")
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "password", ignore = true)
    AcudienteDtoResponse toDto(Acudiente acudiente);
}