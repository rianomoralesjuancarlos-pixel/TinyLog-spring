package com.sena.tinylog.models.usuarios;

import com.sena.tinylog.models.Ciudad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_doc", nullable = false)
    private TipoDocumento tipoDoc;

    @Column(name = "num_doc", nullable = false, length = 30)
    private String numDoc;

    @Column(name = "nombres", nullable = false, length = 60)
    private String nom;

    @Column(name = "apellidos", nullable = false, length = 60)
    private String ape;

    @Column(name = "direccion", length = 120)
    private String dir;

    @Column(name = "telefono", length = 20)
    private String tel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ciudad_codigo", nullable = false)
    private Ciudad ciudad;

@Column(name = "correo", unique = true, length = 80)
private String user;

@Column(name = "password", length = 255)
private String password;
}   
