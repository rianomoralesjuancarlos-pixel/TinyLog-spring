package com.sena.tinylog.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter @Setter
@AllArgsConstructor 
@NoArgsConstructor 
@SuperBuilder 
public class DirectivoDtoResponse extends UsuarioDtoResponse{
    private String cargo;

}
