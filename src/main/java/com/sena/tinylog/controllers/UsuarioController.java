package com.sena.tinylog.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.tinylog.dtos.UsuarioDto;
import com.sena.tinylog.dtos.UsuarioDtoResponse;
import com.sena.tinylog.services.UsuarioServices;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping ("api/usuarios")
@RequiredArgsConstructor

public class UsuarioController {


    private final UsuarioServices usuarioServices;

    // 1 Registrar nuevo usuario
    @PostMapping
    public ResponseEntity<UsuarioDtoResponse> guardar(@Valid @RequestBody UsuarioDto usuarioDto) {

        UsuarioDtoResponse usuarioguardado = usuarioServices.guardar(usuarioDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioguardado);
    }

    // Listar Usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioDtoResponse>> listarTodos() {
        return ResponseEntity.ok(usuarioServices.listarTodos());
    }

    // Consulta usuario por Id
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDtoResponse> consutaId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioServices.buscarPorId(id));
    }

    // consulta por post
    @PostMapping("/buscar")
    public ResponseEntity<UsuarioDtoResponse> consutaIdpost(@RequestBody UsuarioDto dto) {
        UsuarioDtoResponse encontrado = usuarioServices.buscarPorId(dto.getId());

        return ResponseEntity.ok(encontrado);
    }

    // Consulta usuario por numero de documento
    @GetMapping("/buscaid")
    public ResponseEntity<UsuarioDtoResponse> consutaNumDoc(@RequestBody UsuarioDto dto) {
        UsuarioDtoResponse encontrado = usuarioServices.buscarPorNumDoc(dto.getNumDoc());

        return ResponseEntity.ok(encontrado);
    }

    // actualizar
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDtoResponse> update(
            @PathVariable Long id,
            @RequestBody UsuarioDto dto) {

        UsuarioDtoResponse usuarioActualizado = usuarioServices.actualizar(
                id, dto);

        return ResponseEntity.ok(usuarioActualizado);
    }

    // actualizar ocultando ID
    @PutMapping("/actualizar")
    public ResponseEntity<UsuarioDtoResponse> update2(
            @RequestBody UsuarioDto dto) {
        Long id = dto.getId();
        UsuarioDtoResponse usuarioActualizado = usuarioServices.actualizar(
                id, dto);

        return ResponseEntity.ok(usuarioActualizado);
    }

    // Eliminar Usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {

        usuarioServices.eliminar(id);

        return ResponseEntity.ok("Usuario con id :" + id + " Eliminado correctamente");
    }

}