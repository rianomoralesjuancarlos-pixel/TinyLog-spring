package com.sena.tinylog.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.tinylog.models.usuarios.TipoDocumento;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Byte> {

}

