package com.sena.tinylog.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.tinylog.models.usuarios.Docente;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {
}