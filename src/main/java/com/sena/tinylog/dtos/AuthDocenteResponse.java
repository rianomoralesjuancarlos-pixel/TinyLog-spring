package com.sena.tinylog.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthDocenteResponse {

    private Long idDocente;
    private String nombre;
    private String correo;
    private String rol;
    private String mensaje;
}