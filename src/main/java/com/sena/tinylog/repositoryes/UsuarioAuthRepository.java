package com.sena.tinylog.repositoryes;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.tinylog.models.auth.UsuarioAuth;

public interface UsuarioAuthRepository extends JpaRepository<UsuarioAuth, Long> {

    boolean existsByUser(String user);
    Optional<UsuarioAuth> findByUser(String user);

    Optional<UsuarioAuth> findByUsuario_Id(Long id);
}


