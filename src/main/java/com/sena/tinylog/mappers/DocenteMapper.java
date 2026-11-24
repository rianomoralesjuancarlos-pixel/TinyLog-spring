package com.sena.tinylog.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.sena.tinylog.dtos.DocenteDto;
import com.sena.tinylog.models.usuarios.Docente;
import com.sena.tinylog.models.usuarios.TipoDocumento;

@Mapper(componentModel = "spring")
public interface DocenteMapper extends UsuarioMapper {

    @Mapping(target = "cargo", constant = "profesor")
    @Mapping(source = "salario", target = "sueldo")
    @Mapping(source = "nom", target = "nomb")
    @Mapping(source = "ape", target = "apell")
    @Mapping(source = "dir", target = "direc")
    @Mapping(source = "tel", target = "tele")
    DocenteDto toDto(Docente docente);

    @Mapping(source = "sueldo", target = "salario")
    @Mapping(source = "nomb", target = "nom")
    @Mapping(source = "apell", target = "ape")
    @Mapping(source = "direc", target = "dir")
    @Mapping(source = "tele", target = "tel")
    @Mapping(target = "tipoDoc", ignore = true)
    Docente toEntidad(DocenteDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "sueldo", target = "salario")
    @Mapping(source = "nomb", target = "nom")
    @Mapping(source = "apell", target = "ape")
    @Mapping(source = "direc", target = "dir")
    @Mapping(source = "tele", target = "tel")
    @Mapping(target = "tipoDoc", ignore = true)
    void actualizarEntidadDesdeDto(DocenteDto dto, @MappingTarget Docente docente);

    default Byte mapTipoDocumentoToByte(TipoDocumento tipoDocumento) {
        if (tipoDocumento == null || tipoDocumento.getId() == null) {
            return null;
        }
        return tipoDocumento.getId().byteValue();
    }

    default DocenteDto convierteDto(Docente docente) {
        return toDto(docente);
    }

    default Docente convierteaEntidad(DocenteDto dto) {
        return toEntidad(dto);
    }

    default void updateEntityFromDto(DocenteDto dto, Docente docente) {
        actualizarEntidadDesdeDto(dto, docente);
    }
}