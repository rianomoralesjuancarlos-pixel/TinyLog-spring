package com.sena.tinylog.models.usuarios;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter 
@Entity
@Table(name = "directivo")
@Inheritance (strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
public class Directivo extends Usuario {
    @Column(nullable = false)
    private String cargo;
}


