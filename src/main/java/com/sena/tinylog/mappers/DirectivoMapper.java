package com.sena.tinylog.mappers;

import com.sena.tinylog.dtos.DirectivoDto;
import com.sena.tinylog.models.usuarios.Directivo;

public interface DirectivoMapper extends UsuarioMapper{

    // convertir de entidad a Dto
    DirectivoDto toEntidad(Directivo directivo);
    
    //convertir de Dto a entidad
    Directivo toDto(DirectivoDto directivoDto);

}
