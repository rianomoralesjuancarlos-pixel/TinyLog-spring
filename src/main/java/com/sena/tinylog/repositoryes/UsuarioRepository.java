package com.sena.tinylog.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.tinylog.models.usuarios.TipoDocumento;
import com.sena.tinylog.models.usuarios.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long>  {

    boolean existsByTipoDocAndNumDoc(TipoDocumento tipoDoc, String numDoc);
}
