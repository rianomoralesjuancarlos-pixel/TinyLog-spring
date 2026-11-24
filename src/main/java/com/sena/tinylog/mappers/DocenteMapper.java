package com.sena.tinylog.mappers;


import com.sena.tinylog.dtos.DocenteDto;
import com.sena.tinylog.models.usuarios.Docente;

public interface DocenteMapper  {

    DocenteDto convierteDto(Docente docente);

    Docente convierteEntidad(DocenteDto docenteDto);

}
