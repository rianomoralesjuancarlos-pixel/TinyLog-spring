package com.sena.tinylog.models.usuarios;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SuperBuilder;



@Getter@Setter
@Entity
@Table(name = "acudientes")
@PrimaryKeyJoinColumn (name = "acudiente_id")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Acudiente extends Usuario {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_acudiente")
    private Long id;

    @Column(name = "nombres", nullable = false, length = 60)
    private String nom;

    @Column(name = "apellidos", nullable = false, length = 60)
    private String ape;

    @Column(name = "correo", nullable = false, unique = true, length = 150)
    private String corr;

    @Column(name = "telefono", length = 20)
    private String tel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "tipo_Acudiente", nullable = false)
    private TiposAcudiente tipoAcudiente;

}
