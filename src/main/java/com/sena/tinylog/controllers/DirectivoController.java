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
import com.sena.tinylog.services.DirectivoServices;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/directivos")
@RequiredArgsConstructor
public class DirectivoController {

    private final DirectivoServices directivoServices;

    // Crear / registrar el Directivo (Rector)
    @PostMapping
    public ResponseEntity<DirectivoDto> guardar(@RequestBody DirectivoDto dto) {
        DirectivoDto creado = directivoServices.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // Listar directivos
    @GetMapping
    public ResponseEntity<List<DirectivoDto>> listarDir() {
        return ResponseEntity.ok(directivoServices.listarDir());
    }

    // Buscar directivo por ID
    @GetMapping("/{id}")
    public ResponseEntity<DirectivoDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(directivoServices.buscarPorId(id));
    }

    // Actualizar directivo por ID
    @PutMapping("/{id}")
    public ResponseEntity<DirectivoDto> actualizar(
            @PathVariable Long id,
            @RequestBody DirectivoDto dto) {
        return ResponseEntity.ok(directivoServices.actualizar(id, dto));
    }

    // Eliminar directivo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        directivoServices.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}