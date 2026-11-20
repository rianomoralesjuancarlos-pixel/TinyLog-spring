package com.sena.tinylog.repositoryes;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.tinylog.models.auth.Rol;

public interface RolRepository extends JpaRepository <Rol, Byte> {


    Optional<Rol> findByNombre(String nombre);

}
