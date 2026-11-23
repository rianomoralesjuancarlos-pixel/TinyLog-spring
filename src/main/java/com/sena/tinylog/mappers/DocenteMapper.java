package com.sena.tinylog.mappers;

import org.springframework.stereotype.Component;
import com.sena.tinylog.dtos.DocenteDto;
import com.sena.tinylog.models.usuarios.Docente;

@Component
public class DocenteMapper {

    public DocenteDto convierteDto(Docente docente) {
        if (docente == null) {
            return null;
        }

        return DocenteDto.builder()
                .cargo("profesor")
                .sueldo(docente.getSalario())
                .build();
    }

    public Docente convierteaEntidad(DocenteDto dto) {
        if (dto == null) {
            return null;
        }

        Docente docente = new Docente();
        aplicarDtoAEntidad(dto, docente);
        return docente;
    }

    public void actualizarEntidadDesdeDto(DocenteDto dto, Docente docente) {
        if (dto == null || docente == null) {
            return;
        }

        aplicarDtoAEntidad(dto, docente);
    }

    public DocenteDto toDto(Docente docente) {
        return convierteDto(docente);
    }

    public Docente toEntity(DocenteDto dto) {
        return convierteaEntidad(dto);
    }

    public void updateEntityFromDto(DocenteDto dto, Docente docente) {
        actualizarEntidadDesdeDto(dto, docente);
    }

    private void aplicarDtoAEntidad(DocenteDto dto, Docente docente) {
        docente.setSalario(dto.getSueldo());
    }
}