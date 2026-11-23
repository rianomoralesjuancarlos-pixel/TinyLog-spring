package com.sena.tinylog.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.tinylog.models.usuarios.Acudiente;


public interface AcudienteRepository extends JpaRepository <Acudiente, Long>{

}
