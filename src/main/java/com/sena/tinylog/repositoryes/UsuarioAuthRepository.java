package com.sena.tinylog.repositoryes;

<<<<<<< HEAD
=======
import java.util.Optional;
>>>>>>> e84aa2f56d5c581ce4fa2ec600d01cbad1cbe5ad

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.tinylog.models.auth.UsuarioAuth;

public interface UsuarioAuthRepository extends JpaRepository<UsuarioAuth, Long> {

    boolean existsByUser(String user);
<<<<<<< HEAD

}

=======

    Optional<UsuarioAuth> findByUser(String user);

    Optional<UsuarioAuth> findByUsuario_Id(Long id);

}
>>>>>>> e84aa2f56d5c581ce4fa2ec600d01cbad1cbe5ad
