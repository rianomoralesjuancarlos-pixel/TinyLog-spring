package com.sena.tinylog.dtos;

import com.sena.tinylog.models.Ciudad;
import com.sena.tinylog.models.usuarios.TipoDocumento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter  @Setter 
@AllArgsConstructor 
@NoArgsConstructor 
@SuperBuilder 

public class UsuarioDtoResponse {

    private Long id;

    private String nomb;

    private String apell;

    private String direc;

    private String tele;

    // Nombre del tipo de documento
    private TipoDocumento tipoDoc;

    private String numDoc;

    // Ubicación
    private Ciudad ciudad;

    private String departamento;
}
