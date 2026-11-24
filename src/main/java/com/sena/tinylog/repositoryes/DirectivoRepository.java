package com.sena.tinylog.repositoryes;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.tinylog.models.usuarios.Directivo;

public interface DirectivoRepository extends JpaRepository<Directivo, Long> {

    Directivo findByTipoDoc_IdAndNumDoc(Byte tipoDocId, String numDoc);

    boolean existsByTipoDoc_IdAndNumDoc(Byte tipoDocId, String numDoc);

    Optional<Directivo> findByNumDoc(String numDoc);
}