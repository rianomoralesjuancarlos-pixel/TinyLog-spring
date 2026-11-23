package com.sena.tinylog.models.usuarios;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "tipo_Acudiente")
@Getter
@NoArgsConstructor
public class TiposAcudiente {

    @Id 
    private Byte id;

    @Column (nullable = false, unique = true)
    private String nom_TipoAcu;

}
