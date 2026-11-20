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

    // 1. Registrar nuevo docente
    @PostMapping
    public ResponseEntity<DocenteDto> guardar(@Valid @RequestBody DocenteDto docenteDto) {
        DocenteDto docenteGuardado = docenteServices.guardar(docenteDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(docenteGuardado);
    }

    // 2. Listar todos los docentes
    @GetMapping
    public ResponseEntity<List<DocenteDto>> listarTodos() {
        return ResponseEntity.ok(docenteServices.listarTodos());
    }

    // 3. Consultar docente por ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<DocenteDto> consultarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(docenteServices.buscarPorId(id));
    }

    // 4. Actualizar docente (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<DocenteDto> actualizar(@PathVariable Long id, @Valid @RequestBody DocenteDto docenteDto) {
        DocenteDto docenteActualizado = docenteServices.actualizar(id, docenteDto);
        return ResponseEntity.ok(docenteActualizado);
    }

    // 5. Eliminar docente por ID (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<DocenteDto> eliminar(@PathVariable Long id) {
        DocenteDto docenteEliminado = docenteServices.eliminar(id);
        return ResponseEntity.ok(docenteEliminado);
    }
}      