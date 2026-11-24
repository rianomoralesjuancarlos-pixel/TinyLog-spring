package com.sena.tinylog.services;

import java.util.List;

import com.sena.tinylog.dtos.DirectivoDto;
import com.sena.tinylog.dtos.DirectivoDtoResponse;
import com.sena.tinylog.dtos.UsuarioDtoResponse;

public interface DirectivoServices {

    DirectivoDtoResponse guardar(DirectivoDto directivoDto);

    List<DirectivoDtoResponse> listarTodos();

    DirectivoDtoResponse buscarPorId(Long id);

    DirectivoDtoResponse buscarPorNumDoc(String numDoc);

    DirectivoDtoResponse actualizar(Long id, DirectivoDto dto);

    void eliminar(Long id);

}
