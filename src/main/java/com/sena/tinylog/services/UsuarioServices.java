package com.sena.tinylog.services;

import java.util.List;

import com.sena.tinylog.dtos.UsuarioDto;
import com.sena.tinylog.dtos.UsuarioDtoResponse;

public interface UsuarioServices {
 //registrar un nuevo usuario
    UsuarioDtoResponse guardar(UsuarioDto usuarioDto);

    //listar todos los usuario
    List<UsuarioDtoResponse> listarTodos();

    //Consultar usuario por id
    UsuarioDtoResponse buscarPorId(Long id);

    UsuarioDtoResponse buscarPorNumDoc(String numDoc);

    //Actualizar usuario
    UsuarioDtoResponse actualizar(Long id, UsuarioDto usuarioDto);
    //Eliminar usuario
    void eliminar(Long id);
}
