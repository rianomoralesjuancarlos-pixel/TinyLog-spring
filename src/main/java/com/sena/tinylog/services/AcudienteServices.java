package com.sena.tinylog.services;

import java.util.List;
import com.sena.tinylog.dtos.AcudienteDto;
import com.sena.tinylog.dtos.AcudienteDtoResponse;

public interface AcudienteServices {

    //Registrar nuevo acudiente
    AcudienteDtoResponse guardar(AcudienteDto acudienteDto);

    //listar todos los acudientes
    List<AcudienteDtoResponse> ListarTodos();

    //Consultar acudiente por id
    AcudienteDtoResponse buscarAcuId(Long id);

    //Actualizar acudiente
    AcudienteDtoResponse actualizar(Long id, AcudienteDto acudienteDto);

    //Eliminar acudiente
    void eliminar(Long id);

}
