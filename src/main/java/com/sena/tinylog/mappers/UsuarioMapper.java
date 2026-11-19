package com.sena.tinylog.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sena.tinylog.dtos.UsuarioDto;
import com.sena.tinylog.models.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    //convertir de entidad a Dto
    @Mapping (source = "nom" , target = "nomb")
    @Mapping (source = "ape" , target = "apell")
    @Mapping (source = "corr" , target = "corre")
    @Mapping (source = "tel" , target = "tele")
    UsuarioDto ConvierteDto(Usuario usuario);
     //convertir Dto a Entidad
    @Mapping (source = "nomb" , target = "nom")
    @Mapping (source = "apell" , target = "ape")
    @Mapping (source = "corre" , target = "corr")
    @Mapping (source = "tele" , target = "tel")
    Usuario ConvierteaEntidad(UsuarioDto usuarioDto);

}
