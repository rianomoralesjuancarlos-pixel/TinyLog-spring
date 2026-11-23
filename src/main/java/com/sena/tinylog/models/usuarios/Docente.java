package com.sena.tinylog.models.usuarios;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter @Setter
@AllArgsConstructor 
@NoArgsConstructor 
@SuperBuilder 
@Entity 
@Table(name = "docentes")
@PrimaryKeyJoinColumn(name = "id_docente") 
public class Docente extends Usuario {


    @Column(name = "salario")
    private Double salario;
}