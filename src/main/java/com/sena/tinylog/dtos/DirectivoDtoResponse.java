package com.sena.tinylog.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
<<<<<<< HEAD
import lombok.experimental.SuperBuilder;

@Getter @Setter 
=======
import lombok.SuperBuilder;

@Getter @Setter
>>>>>>> b4ccfe9d7b16505d6cb6fd5f7b31a05fe323b185
@AllArgsConstructor 
@NoArgsConstructor 
@SuperBuilder 
public class DirectivoDtoResponse extends UsuarioDtoResponse{

    private String cargo;

    private String correoInstitucional;

}
