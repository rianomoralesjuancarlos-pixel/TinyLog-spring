package com.sena.tinylog.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter @Setter
@Entity 
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor 
@NoArgsConstructor 
@SuperBuilder 
public class Usuario {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "nombres", nullable = false, length = 60)
    private String nom;

    @Column(name = "apellidos", nullable = false, length = 60)
    private String ape;

    @Column(name = "correo", nullable = false, unique = true, length = 150)
    private String corr;

    @Column(name = "telefono", length = 20)
    private String tel;



}
