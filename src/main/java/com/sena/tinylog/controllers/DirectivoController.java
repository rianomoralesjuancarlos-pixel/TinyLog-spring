package com.sena.tinylog.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.tinylog.dtos.DirectivoDto;
import com.sena.tinylog.dtos.DirectivoDtoResponse;
import com.sena.tinylog.services.DirectivoServices;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/directivos")
@RequiredArgsConstructor
public class DirectivoController {

    private final DirectivoServices directivoServices;

    @PostMapping
    public ResponseEntity<DirectivoDtoResponse> guardar(@Valid @RequestBody DirectivoDto dto) {
        DirectivoDtoResponse creado = directivoServices.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<DirectivoDtoResponse>> listarTodos() {
        return ResponseEntity.ok(directivoServices.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectivoDtoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(directivoServices.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DirectivoDtoResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody DirectivoDto dto) {
        return ResponseEntity.ok(directivoServices.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        directivoServices.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}