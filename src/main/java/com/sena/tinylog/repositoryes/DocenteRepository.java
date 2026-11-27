package com.sena.tinylog.repositoryes;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.tinylog.models.usuarios.Docente;

public interface DocenteRepository extends JpaRepository<Docente, Long> {

    Optional<Docente> findByNumDoc(String numDoc);
}