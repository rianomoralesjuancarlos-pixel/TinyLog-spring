package com.sena.tinylog.servicesImplement;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sena.tinylog.dtos.CalendarioDirDto;
import com.sena.tinylog.mappers.CalendarioDirMapper;
import com.sena.tinylog.models.usuarios.Calendario;
import com.sena.tinylog.repositoryes.CalendarioRepository;
import com.sena.tinylog.services.CalendarioDirServices;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalendarioDirServiceImpl implements CalendarioDirServices {

    private final CalendarioRepository calendarioRepository;
    private final CalendarioDirMapper calendarioMapper;

    @Override
    @Transactional
    public CalendarioDirDto agregar(CalendarioDirDto dto) {
        Calendario entidad = calendarioMapper.toEntidad(dto);
        Calendario guardado = calendarioRepository.save(entidad);
        return calendarioMapper.toDto(guardado);
    }

    @Override
    @Transactional
    public CalendarioDirDto editar(Long id, CalendarioDirDto dto) {
        Calendario existente = calendarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registro de calendario con id: " + id + " no encontrado"));

        existente.setTitulo(dto.getTitulo());
        existente.setDescripcion(dto.getDescripcion());
        existente.setFechaInicio(dto.getFechaInicio());
        existente.setFechaFin(dto.getFechaFin());
        existente.setTipo(dto.getTipo());

        Calendario actualizado = calendarioRepository.save(existente);
        return calendarioMapper.toDto(actualizado);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Calendario calendario = calendarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registro de calendario con id: " + id + " no encontrado"));
        calendarioRepository.delete(calendario);
    }
}