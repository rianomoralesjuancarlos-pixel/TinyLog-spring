package com.sena.tinylog.mappers;

import com.sena.tinylog.dtos.AcudienteDto;

import com.sena.tinylog.models.usuarios.Acudiente;
import com.sena.tinylog.dtos.AcudienteDtoResponse;


public interface AcudienteMapper {

    //De Acudiente a AcudienteDto
    Acudiente toEntidad(AcudienteDto acudienteDto);

    //De AcudeinteDto a Acudiente
    AcudienteDtoResponse toDto(Acudiente acudiente);

}
