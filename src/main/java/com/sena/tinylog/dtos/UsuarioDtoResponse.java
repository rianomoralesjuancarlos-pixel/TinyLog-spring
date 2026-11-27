package com.sena.tinylog.dtos;

<<<<<<< HEAD
import com.sena.tinylog.models.Barrio;
import com.sena.tinylog.models.Ciudad;
import com.sena.tinylog.models.usuarios.TipoDocumento;

=======
>>>>>>> b4ccfe9d7b16505d6cb6fd5f7b31a05fe323b185
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
    private String tipoDoc;

    private String numDoc;

    // Ubicación
    private String ciudad;

    private Barrio barrio;
}
