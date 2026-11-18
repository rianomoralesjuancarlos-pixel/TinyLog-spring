package com.sena.tinylog.models;
 Kevin-Velasquez
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
 main
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

Kevin-Velasquez
@Getter 
@Setter
@Entity 
@Table(name = "docentes")
@PrimaryKeyJoinColumn(name = "id_usuario") // 
@NoArgsConstructor 
@SuperBuilder 
public class Docente extends Usuario {
@Enumerated (EnumType.STRING)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "tipo_Docente", nullable = false)
    private Long id;
    private String cargo;
    private Double salario;

}
@Getter @Setter 
@AllArgsConstructor 
@NoArgsConstructor 
@SuperBuilder 
public class Docente {
    

}
main
