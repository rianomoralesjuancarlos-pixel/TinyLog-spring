package com.sena.tinylog.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DocenteDto extends UsuarioDto {

    @Builder.Default
    private String cargo = "profesor";

    private Double sueldo;
}