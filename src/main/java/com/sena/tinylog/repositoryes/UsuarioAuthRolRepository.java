package com.sena.tinylog.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.tinylog.models.auth.UsuarioAuthRol;

public interface UsuarioAuthRolRepository extends JpaRepository<UsuarioAuthRol, Long> {

    void deleteAllByUsuarioAuth_Id(Long id);

}