package com.sena.tinylog.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sena.tinylog.dtos.UsuarioDto;
import com.sena.tinylog.models.usuarios.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(source = "nom", target = "nomb")
    @Mapping(source = "ape", target = "apell")
    @Mapping(source = "dir", target = "direc")
    @Mapping(source = "tel", target = "tele")
    @Mapping(source = "tipoDoc.id", target = "tipoDoc")
    @Mapping(source = "numDoc", target = "numDoc")
    UsuarioDto convierteDto(Usuario usuario);

    // convertir de DTO a Entidad
    @Mapping(source = "nomb", target = "nom")
    @Mapping(source = "apell", target = "ape")
    @Mapping(source = "direc", target = "dir")
    @Mapping(source = "tele", target = "tel")
    @Mapping(target = "tipoDoc", ignore = true)
    @Mapping(source = "numDoc", target = "numDoc")
    Usuario convierteEntidad(UsuarioDto usuarioDto);

}
