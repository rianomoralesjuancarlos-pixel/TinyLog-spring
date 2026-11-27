package com.sena.tinylog.models.usuarios;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "directivo")
@PrimaryKeyJoinColumn(name = "id_directivo") // ajusta al nombre real de la FK en tu tabla directivo
public class Directivo extends Usuario {

    @Builder .Default
    @Column(nullable = false, length = 50)
    private String cargo = "Rector";

    @Column(name = "correo_institucional", unique = true, length = 150)
    private String correoInstitucional;
}