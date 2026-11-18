package com.sena.tinylog.services;

import java.util.List;

import com.sena.tinylog.dtos.DocenteDto;

public interface DocenteServices {

    // Registrar un nuevo docente
    DocenteDto guardar(DocenteDto docenteDto);

    // Listar todos los docentes
    List<DocenteDto> listarTodos();

    // Consultar docente por ID
    DocenteDto buscarPorId(Long id);

    // Actualizar docente
    DocenteDto actualizar(Long id, DocenteDto docenteDto);

    // Eliminar docente
    DocenteDto eliminar(Long id);
}