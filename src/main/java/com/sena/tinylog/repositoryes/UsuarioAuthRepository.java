package com.sena.tinylog.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.tinylog.models.auth.UsuarioAuth;



public interface UsuarioAuthRepository extends JpaRepository<UsuarioAuth, Long>{

    boolean exisexistsByUser(String user);

}
