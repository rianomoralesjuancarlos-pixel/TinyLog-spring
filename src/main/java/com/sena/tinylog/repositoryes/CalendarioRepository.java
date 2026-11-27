package com.sena.tinylog.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.tinylog.models.usuarios.Calendario;

public interface CalendarioRepository extends JpaRepository<Calendario, Long> {

}
