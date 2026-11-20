package com.sena.tinylog.services;

import java.util.List;

import com.sena.tinylog.dtos.UsuarioDto;

public interface UsuarioServices {
 //registrar un nuevo usuario
    UsuarioDto guardar(UsuarioDto usuarioDto);

    //listar todos los usuario
    List<UsuarioDto> listarTodos();

    //Consultar usuario por id
    UsuarioDto buscarPorId(Long id);

    //Actualizar usuario
    UsuarioDto actualizar(Long id, UsuarioDto usuarioDto);
    //Eliminar usuario
    void eliminar(Long id);
}
