package com.sena.tinylog.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sena.tinylog.dtos.CalendarioDirDto;
import com.sena.tinylog.models.usuarios.Calendario;

@Mapper (componentModel = "Spring")
public interface CalendarioDirMapper {
// CalendarioDto (entrada) -> Calendario (entidad)
    @Mapping(target = "id", ignore = true)
    Calendario toEntidad(CalendarioDirDto calendarioDirDto);

    // Calendario (entidad) -> CalendarioDtoResponse (salida)
    CalendarioDirDto toDto(Calendario calendario);
}
