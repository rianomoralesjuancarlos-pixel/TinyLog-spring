package com.sena.tinylog.services;

import java.util.List;

import com.sena.tinylog.dtos.DocenteDto;

public interface DocenteServices {

    DocenteDto guardar(DocenteDto docenteDto);

    List<DocenteDto> listarDoc();

    DocenteDto buscarPorId(Long id);

    DocenteDto actualizar(Long id, DocenteDto dto);

    void eliminar(Long id);

}