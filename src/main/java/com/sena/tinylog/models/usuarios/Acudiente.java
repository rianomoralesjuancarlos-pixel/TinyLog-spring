package com.sena.tinylog.models.usuarios;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter@Setter
@Entity
@Table(name = "acudientes")
@PrimaryKeyJoinColumn (name = "acudiente_id")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Acudiente extends Usuario {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "tipo_Acudiente", nullable = false)
    private TiposAcudiente tipoAcudiente;

}
