package com.sena.tinylog.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.tinylog.dtos.CalendarioDirDto;
import com.sena.tinylog.services.CalendarioDirServices;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/calendarioDir")
@RequiredArgsConstructor
public class CalendarioDirController {
    
    private final CalendarioDirServices calendarioDirService;

    @PostMapping
    public ResponseEntity<CalendarioDirDto> agregar(@RequestBody CalendarioDirDto dto) {
        CalendarioDirDto creada = calendarioDirService.agregar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CalendarioDirDto> editar(
            @PathVariable Long id,
            @RequestBody CalendarioDirDto dto) {
        CalendarioDirDto actualizada = calendarioDirService.editar(id, dto);
        return ResponseEntity.ok(actualizada);
    }
}


