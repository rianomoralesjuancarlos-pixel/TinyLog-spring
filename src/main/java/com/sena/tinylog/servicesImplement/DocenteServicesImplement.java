package com.sena.tinylog.servicesImplement;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sena.tinylog.dtos.DocenteDto;
import com.sena.tinylog.mappers.DocenteMapper;
import com.sena.tinylog.models.usuarios.Docente;
import com.sena.tinylog.repositoryes.DocenteRepository;
import com.sena.tinylog.services.DocenteServices;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class DocenteServicesImplement implements DocenteServices {

    private final DocenteRepository docenteRepository;
    private final DocenteMapper docenteMapper;

    @Override
    public DocenteDto guardar(DocenteDto docenteDto) {
        Docente docente = docenteMapper.convierteaEntidad(docenteDto);
        Docente docenteGuardado = docenteRepository.save(docente);
        return docenteMapper.convierteDto(docenteGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocenteDto> listarTodos() {
        return docenteRepository.findAll()
                .stream()
                .map(docenteMapper::convierteDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DocenteDto buscarPorId(Long id) {
        Docente docente = obtenerDocentePorId(id);
        return docenteMapper.convierteDto(docente);
    }

    @Override
    public DocenteDto actualizar(Long id, DocenteDto docenteDto) {
        Docente docenteEncontrado = obtenerDocentePorId(id);
        docenteMapper.actualizarEntidadDesdeDto(docenteDto, docenteEncontrado);
        Docente docenteActualizado = docenteRepository.save(docenteEncontrado);
        return docenteMapper.convierteDto(docenteActualizado);
    }

    @Override
    public DocenteDto eliminar(Long id) {
        Docente docenteEncontrado = obtenerDocentePorId(id);
        DocenteDto eliminadoDto = docenteMapper.convierteDto(docenteEncontrado);
        docenteRepository.delete(docenteEncontrado);
        return eliminadoDto;
    }

    private Docente obtenerDocentePorId(Long id) {
        return docenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Docente no encontrado con id: " + id));
    }
}