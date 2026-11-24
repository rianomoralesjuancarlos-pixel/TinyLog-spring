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
public class DocenteServicesImplement implements DocenteServices {

    private final DocenteRepository docenteRepository;
    private final DocenteMapper docenteMapper;

    @Override
    @Transactional
    public DocenteDto guardar(DocenteDto docenteDto) {
        Docente docente = docenteMapper.convierteEntidad(docenteDto);
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
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Docente no encontrado con id: " + id));
        return docenteMapper.convierteDto(docente);
    }

    @Override
    @Transactional
    public DocenteDto actualizar(Long id, DocenteDto docenteDto) {
        Docente docenteEncontrado = docenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Docente no encontrado con id: " + id));

        docenteEncontrado.setNom(docenteDto.getNomb());
        docenteEncontrado.setApe(docenteDto.getApell());
        docenteEncontrado.setTel(docenteDto.getTele());
        // Agrega aquí dir, numDoc o relaciones si aplica

        Docente docenteActualizado = docenteRepository.save(docenteEncontrado);
        return docenteMapper.convierteDto(docenteActualizado);
    }

    @Override
    @Transactional
    public DocenteDto eliminar(Long id) {
        Docente docenteEncontrado = docenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Docente no encontrado con id: " + id));

        DocenteDto dtoEliminado = docenteMapper.convierteDto(docenteEncontrado);
        docenteRepository.delete(docenteEncontrado);
        return dtoEliminado;
    }
}
