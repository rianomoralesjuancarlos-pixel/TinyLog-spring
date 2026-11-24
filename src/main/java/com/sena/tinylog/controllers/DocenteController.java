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

import com.sena.tinylog.dtos.DocenteDto;
import com.sena.tinylog.services.DocenteServices;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/docentes")
@RequiredArgsConstructor
public class DocenteController {

    private final DocenteServices docenteServices;

    @PostMapping
    public ResponseEntity<DocenteDto> guardar(@Valid @RequestBody DocenteDto dto) {
        DocenteDto creado = docenteServices.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<DocenteDto>> listarDoc() {
        return ResponseEntity.ok(docenteServices.listarDoc());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocenteDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(docenteServices.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocenteDto> actualizar(
            @PathVariable Long id,
            @RequestBody DocenteDto dto) {
        return ResponseEntity.ok(docenteServices.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        docenteServices.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}