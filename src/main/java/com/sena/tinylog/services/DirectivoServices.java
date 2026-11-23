package com.sena.tinylog.services;

import java.util.List;

import com.sena.tinylog.dtos.DirectivoDto;

public interface DirectivoServices {

    DirectivoDto guardar(DirectivoDto directivoDto);

    List<DirectivoDto> listarDir();

    DirectivoDto buscarPorId(Long id);

    DirectivoDto actualizar(Long id, DirectivoDto dto);

    void eliminar(Long id);

}
