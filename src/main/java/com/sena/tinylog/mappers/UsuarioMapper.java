package com.sena.tinylog.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sena.tinylog.dtos.UsuarioDto;
import com.sena.tinylog.dtos.UsuarioDtoResponse;
import com.sena.tinylog.models.usuarios.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper{

 // convertir de Entidad a DTO
    @Mapping(source = "nom", target = "nomb")
    @Mapping(source = "ape", target = "apell")
    @Mapping(source = "dir", target = "direc")
    @Mapping(source = "tel", target = "tele")
    @Mapping(source = "tipoDoc.nombre", target = "tipoDoc")
    @Mapping(source = "numDoc", target = "numDoc")
<<<<<<< HEAD
    @Mapping(source = "barrio", target ="barrio")
    @Mapping(source = "ciudad", target = "ciudad")
=======
    @Mapping(source = "ciudad.nombre", target = "ciudad")
    @Mapping(source = "ciudad.departamento.nombre", target = "departamento")
>>>>>>> b4ccfe9d7b16505d6cb6fd5f7b31a05fe323b185
    UsuarioDtoResponse convierteDto(Usuario usuario);

    // convertir de DTO a Entidad
    @Mapping(source = "nomb", target = "nom")
    @Mapping(source = "apell", target = "ape")
    @Mapping(source = "direc", target = "dir")
    @Mapping(source = "tele", target = "tel")
    @Mapping(source = "numDoc", target = "numDoc")

    // Las relaciones se asignan desde el services
    @Mapping(target = "tipoDoc", ignore = true)
    @Mapping(target = "barrio", ignore = true)
    @Mapping(target = "ciudad", ignore = true)
    Usuario convierteEntidad(UsuarioDto usuarioDto);
;

}
