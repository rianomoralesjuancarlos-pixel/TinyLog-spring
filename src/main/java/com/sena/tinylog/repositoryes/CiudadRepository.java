package com.sena.tinylog.repositoryes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.tinylog.models.Ciudad;

public interface CiudadRepository extends JpaRepository<Ciudad, String>{

    List<Ciudad> findByDepartamentoCodigo(String codigo);

}
