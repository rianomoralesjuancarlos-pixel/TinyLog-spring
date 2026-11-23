package com.sena.tinylog.mappers;

import com.sena.tinylog.dtos.DocenteDto;
import com.sena.tinylog.models.usuarios.Docente;

public interface DocenteMapper {

    default DocenteDto convierteDto(Docente docente) {
        if (docente == null) {
            return null;
        }

        return DocenteDto.builder()
                .cargo("profesor")
                .sueldo(docente.getSalario())
                .build();
    }

    default Docente convierteaEntidad(DocenteDto dto) {
        if (dto == null) {
            return null;
        }

        Docente docente = new Docente();
        docente.setSalario(dto.getSueldo());
        return docente;
    }

    default void actualizarEntidadDesdeDto(DocenteDto dto, Docente docente) {
        if (dto == null || docente == null) {
            return;
        }

        docente.setSalario(dto.getSueldo());
    }

    default DocenteDto toDto(Docente docente) {
        return convierteDto(docente);
    }

    default Docente toEntity(DocenteDto dto) {
        return convierteaEntidad(dto);
    }

    default void updateEntityFromDto(DocenteDto dto, Docente docente) {
        actualizarEntidadDesdeDto(dto, docente);
    }
}