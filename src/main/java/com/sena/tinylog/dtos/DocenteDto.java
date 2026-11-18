package com.sena.tinylog.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DocenteDto {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 60,
        message = "El nombre debe tener entre 2 y 60 caracteres")
    private String nomb;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 60,
        message = "El apellido debe tener entre 2 y 60 caracteres")
    private String apell;
    
    @NotBlank(message = "El número es obligatorio")
    @Pattern(regexp = "^[0-9\\+]+$", message = "El campo debe tener un formato de número válido")
    private String tele;

    @NotBlank(message = "El correo es obligatorio")
    @Size(max = 150,
        message = "El correo no puede superar los 150 caracteres")
    private String corre;
}