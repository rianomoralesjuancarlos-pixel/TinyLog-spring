package com.sena.tinylog.services;

import java.util.List;
import com.sena.tinylog.dtos.AcudienteDto;

public interface AcudienteServices {

    //Registrar nuevo acudiente
    AcudienteDto guardar(AcudienteDto acudienteDto);

    //listar todos los acudientes
    List<AcudienteDto> ListarTodos();

    //Consultar acudiente por id
    AcudienteDto buscarPorId(Long id);

    //Actualizar acudiente
    AcudienteDto actualizar(Long id, AcudienteDto acudienteDto);

    //Eliminar acudiente
    AcudienteDto eliminar(Long id);

}
