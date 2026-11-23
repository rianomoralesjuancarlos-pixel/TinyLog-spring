package com.sena.tinylog.servicesImplement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sena.tinylog.dtos.DocenteDto;
import com.sena.tinylog.mappers.DocenteMapper;
import com.sena.tinylog.models.usuarios.Docente;
import com.sena.tinylog.repositoryes.DocenteRepository;
import com.sena.tinylog.services.DocenteServices;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class DocenteServicesImplement implements DocenteServices {

    private final DocenteRepository docenteRepository;
    private final DocenteMapper docenteMapper;

    @Override 
    public DocenteDto guardar(DocenteDto docenteDto) {
        Docente docente = docenteMapper.convierteaEntidad(docenteDto);
        // CORREGIDO: docenteRepository en minúscula
        Docente docenteGuardado = docenteRepository.save(docente);
        return docenteMapper.convierteDto(docenteGuardado);
    }

    // Listar todos los docentes
    @Override 
    public List<DocenteDto> listarTodos() {
        // CORREGIDO: docenteRepository en minúscula
        return docenteRepository.findAll()
                .stream()
                .map(docenteMapper::convierteDto)
                .toList();
    }

    // Consultar docente por ID
    @Override 
    public DocenteDto buscarPorId(Long id) {
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Docente no encontrado con id: " + id));
        return docenteMapper.convierteDto(docente);
    } 

    // Actualizar docente
    @Override
    public DocenteDto actualizar(Long id, DocenteDto docenteDto) {
        Docente docenteEncontrado = docenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Docente no encontrado con id: " + id));

        // Actualizar datos
        docenteEncontrado.setNom(docenteDto.getNomb());
        docenteEncontrado.setApe(docenteDto.getApell());
        docenteEncontrado.setCorr(docenteDto.getCorre());
        docenteEncontrado.setTel(docenteDto.getTele());

        Docente docenteActualizado = docenteRepository.save(docenteEncontrado);
        return docenteMapper.convierteDto(docenteActualizado);
    }

    // Eliminar docente
    @Override
    public DocenteDto eliminar(Long id) {
        Docente docenteEncontrado = docenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Docente no encontrado con id: " + id));

        docenteRepository.delete(docenteEncontrado);
        return docenteMapper.convierteDto(docenteEncontrado);
    }

}

