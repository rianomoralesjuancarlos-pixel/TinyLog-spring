package com.sena.tinylog.mappers;

<<<<<<< HEAD

import com.sena.tinylog.dtos.DocenteDto;
import com.sena.tinylog.models.usuarios.Docente;

<<<<<<< HEAD
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
    DocenteDto convierteDto(Docente docente);

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
=======
public interface DocenteMapper  {

    DocenteDto convierteDto(Docente docente);

>>>>>>> b4ccfe9d7b16505d6cb6fd5f7b31a05fe323b185
    Docente convierteEntidad(DocenteDto docenteDto);
}
<<<<<<< HEAD
=======
=======
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
>>>>>>> e84aa2f56d5c581ce4fa2ec600d01cbad1cbe5ad
>>>>>>> b4ccfe9d7b16505d6cb6fd5f7b31a05fe323b185
