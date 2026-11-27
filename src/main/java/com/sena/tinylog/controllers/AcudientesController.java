package com.sena.tinylog.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sena.tinylog.dtos.AcudienteDto;
import com.sena.tinylog.dtos.AcudienteDtoResponse;
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
public class AcudientesController {

    private final AcudienteServices acuser;

    //Crear
    @PostMapping
    public ResponseEntity<AcudienteDtoResponse> saveAcu(@Valid @RequestBody AcudienteDto dto) {
        
        AcudienteDtoResponse acusave = acuser.guardar(dto);
        
        return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(acusave);
    }

    //Consulta todos los acudientes
    @GetMapping
    public ResponseEntity<List<AcudienteDtoResponse>> todosAcu() {
        return ResponseEntity.ok(acuser.ListarTodos());
    }

    //Consultar Acudiente por Id por url
    @GetMapping("/{id}")
    public ResponseEntity<AcudienteDtoResponse> acuporId(@RequestParam Long id) {
        return ResponseEntity.ok(acuser.buscarAcuId(id));
    }

    //Consultar Acudiente por Id oculto
    @PostMapping("/buscar")
    public ResponseEntity<AcudienteDtoResponse> acuporId2(@RequestBody AcudienteDto dto) {
        return ResponseEntity.ok(acuser.buscarAcuId(dto.getId()));
    }

    //Actualizar Acudiente
    @PutMapping("/{id}")
    public ResponseEntity<AcudienteDtoResponse> upateCli(@Valid @PathVariable Long id, @RequestBody AcudienteDto dto) {
        
        AcudienteDtoResponse acudienteActualizado = acuser.actualizar(id, dto);
        
        return ResponseEntity.ok(acudienteActualizado);
    }

    //Eliminar Acudiente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable long id){
        acuser.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
