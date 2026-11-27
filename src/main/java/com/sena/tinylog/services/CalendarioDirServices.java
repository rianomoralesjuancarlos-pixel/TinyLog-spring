package com.sena.tinylog.services;

import com.sena.tinylog.dtos.CalendarioDirDto;

public interface CalendarioDirServices {

    CalendarioDirDto agregar(CalendarioDirDto dto);

    CalendarioDirDto editar(Long id, CalendarioDirDto dto);
    
    void eliminar(Long id);

}
