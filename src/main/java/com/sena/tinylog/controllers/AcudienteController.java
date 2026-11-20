package com.sena.tinylog.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sena.tinylog.dtos.AcudienteDto;
import com.sena.tinylog.services.AcudienteServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping ("api/acudientes")
@RequiredArgsConstructor

public class AcudienteController {

    private final AcudienteServices acudienteServices;

    //Registro de acudiente
    @PostMapping
    public ResponseEntity <AcudienteDto> guardar(@Valid @RequestBody AcudienteDto acudienteDto) {
        
        AcudienteDto acudienteguardado = acudienteServices.guardar(acudienteDto);
        return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(acudienteguardado);
    }

    //Listar acudientes
    @GetMapping
    public ResponseEntity <List<AcudienteDto>> ListarTodos() {
        return ResponseEntity.ok(acudienteServices.ListarTodos());
    }

    //Consultar por id
    @GetMapping("/{id}")
    public ResponseEntity <AcudienteDto> consultarId(@PathVariable Long id) {
        return ResponseEntity.ok(acudienteServices.buscarPorId(id));
    }

    //Consultar por post
    @PostMapping("/buscar")
    public ResponseEntity<AcudienteDto> consultarIdPost(@RequestBody AcudienteDto Dto) {
        AcudienteDto encontrado = acudienteServices.buscarPorId(Dto.getId());
        return ResponseEntity.ok(encontrado);
    }

    //Actualizar acudiente
    @PutMapping("/{id}")
    public ResponseEntity<AcudienteDto> actualizar(@PathVariable Long id, @RequestBody AcudienteDto acudienteDto) {
        AcudienteDto acudienteActualizado = acudienteServices.actualizar(id, acudienteDto);
        return ResponseEntity.ok(acudienteActualizado);
    }

    //Actualizar acudiente por post
    @PostMapping("/actualizar")
    public ResponseEntity <AcudienteDto> actualizarPost(@RequestBody AcudienteDto acudienteDto) {
        AcudienteDto acudienteActualizado = acudienteServices.actualizar(acudienteDto.getId(), acudienteDto);
        return ResponseEntity.ok(acudienteActualizado);
    }

    //Eliminar acudiente por id
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<AcudienteDto> eliminar(@PathVariable Long id) {
        AcudienteDto acudienteEliminado = acudienteServices.eliminar(id);
        return ResponseEntity.ok(acudienteEliminado);
    }

    //Eliminar acudiente por delete
    @DeleteMapping("/eliminar")
    public ResponseEntity<AcudienteDto> eliminarPorDetele(@RequestBody AcudienteDto acudienteDto) {
        AcudienteDto acudienteEliminado = acudienteServices.eliminar(acudienteDto.getId());
        return ResponseEntity.ok(acudienteEliminado);
    }
}
