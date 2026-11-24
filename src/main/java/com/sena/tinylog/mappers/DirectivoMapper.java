package com.sena.tinylog.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sena.tinylog.dtos.DirectivoDto;
import com.sena.tinylog.dtos.DirectivoDtoResponse;
import com.sena.tinylog.models.Ciudad;
import com.sena.tinylog.models.usuarios.Directivo;
import com.sena.tinylog.models.usuarios.TipoDocumento;

@Mapper(componentModel = "spring")
public interface DirectivoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nom", source = "nomb")
    @Mapping(target = "ape", source = "apell")
    @Mapping(target = "dir", source = "direc")
    @Mapping(target = "tel", source = "tele")
    @Mapping(target = "tipoDoc", ignore = true)
    @Mapping(target = "ciudad", ignore = true)
    Directivo toEntidad(DirectivoDto dto);

    @Mapping(target = "nomb", source = "nom")
    @Mapping(target = "apell", source = "ape")
    @Mapping(target = "direc", source = "dir")
    @Mapping(target = "tele", source = "tel")
    @Mapping(target = "tipoDoc", expression = "java(mapTipoDocumento(entity.getTipoDoc()))")
    @Mapping(target = "ciudad", expression = "java(mapCiudad(entity.getCiudad()))")
    @Mapping(target = "departamento", expression = "java(mapDepartamento(entity.getCiudad()))")
    DirectivoDtoResponse toDto(Directivo entity);

    default String mapTipoDocumento(TipoDocumento tipoDoc) {
        return tipoDoc != null ? tipoDoc.getNombre() : null;
    }

    default String mapCiudad(Ciudad ciudad) {
        return ciudad != null ? ciudad.getNombre() : null;
    }

    default String mapDepartamento(Ciudad ciudad) {
        return (ciudad != null && ciudad.getDepartamento() != null)
                ? ciudad.getDepartamento().getNombre()
                : null;
    }
}