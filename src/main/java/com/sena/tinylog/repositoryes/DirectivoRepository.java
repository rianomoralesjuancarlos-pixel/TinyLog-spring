package com.sena.tinylog.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.tinylog.models.usuarios.Directivo;

public interface DirectivoRepository extends JpaRepository <Directivo, Long > {

}
