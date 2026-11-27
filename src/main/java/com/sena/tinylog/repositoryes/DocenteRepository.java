package com.sena.tinylog.repositoryes;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.tinylog.models.usuarios.Docente;

public interface DocenteRepository extends JpaRepository<Docente, Long> {

    Optional<Docente> findByNumDoc(String numDoc);

<<<<<<< HEAD
    Docente findByTipoDoc_IdAndNumDoc(Byte tipoDocId, String numDoc);
=======


    Optional<Docente> findByCorreo(String correo);


    Optional<Docente> findByUser(String user);

    boolean existsByUser(String user);

    Optional<Docente> findByCorreo1(String correo);

    boolean existsByCorreo(String correo);
>>>>>>> b4ccfe9d7b16505d6cb6fd5f7b31a05fe323b185
}