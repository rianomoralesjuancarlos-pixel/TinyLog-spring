package com.sena.tinylog.repositoryes;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sena.tinylog.models.usuarios.TipoDocumento;
import com.sena.tinylog.models.usuarios.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long>  {

    boolean existsByTipoDocAndNumDoc(TipoDocumento tipoDoc, String numDoc);

    // Metodo para buscar un usuario por numero de documento
    Usuario findByNumDoc(String numDoc);

    // buscar un usuario por numero de documento usando una consulta nativa
    @Query(value = "SELECT * FROM usuarios WHERE num_doc = :numDoc", nativeQuery = true)
    Optional<Usuario> findByNumDocNativo(@Param("numDoc") String numDoc);
}
