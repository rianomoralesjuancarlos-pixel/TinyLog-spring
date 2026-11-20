package com.sena.tinylog.servicesImplement;

import java.util.List;
import org.springframework.stereotype.Service;
import com.sena.tinylog.dtos.AcudienteDto;
import com.sena.tinylog.mappers.AcudienteMapper;
import com.sena.tinylog.models.Acudiente;
import com.sena.tinylog.repositoryes.AcudienteRepository;
import com.sena.tinylog.services.AcudienteServices;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class AcudienteServicesImplement implements AcudienteServices{
    
    private final AcudienteRepository acudienteRepository;
    private final AcudienteMapper acudienteMapper;

    @Override
    public AcudienteDto guardar(AcudienteDto acudienteDto){
    Acudiente acudiente = acudienteMapper.toEntidad(acudienteDto);

    Acudiente acudienteguardado = acudienteRepository.save(acudiente);

    return acudienteMapper.toDto(acudienteguardado);

    }

    //listar todos los acudientes

    @Override
    public List<AcudienteDto>ListarTodos(){
        return acudienteRepository.findAll()
        .stream()
        .map(acudienteMapper::toDto)
        .toList();
    }

    //consultar acudiente por id

    @Override
    public AcudienteDto buscarPorId (Long id){
        Acudiente acudiente = acudienteRepository.findById(id)
            .orElseThrow(() ->new EntityNotFoundException("acudiente no encontrado"+id)
    );
    
        return acudienteMapper.toDto(acudiente);

    } 

    //actualizar acudiente
    @Override
    public AcudienteDto actualizar(Long id, AcudienteDto acudienteDto) {
        Acudiente acudienteEncontrado = acudienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Acudiente no encontrado con id: " + id));

                //Actualizar datos
                acudienteEncontrado.setNom(acudienteDto.getNomb());
                acudienteEncontrado.setApe(acudienteDto.getApell());
                acudienteEncontrado.setCorr(acudienteDto.getCorre());
                acudienteEncontrado.setTel(acudienteDto.getTele());


                Acudiente acudienteActualizado = acudienteRepository.save(acudienteEncontrado);
                return acudienteMapper.toDto(acudienteActualizado);
        
    }

    @Override
    public AcudienteDto eliminar(Long id) {
        Acudiente acudienteEncontrado= acudienteRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Acudiente no encontrado con id: " + id));

        acudienteRepository.delete(acudienteEncontrado);
        return acudienteMapper.toDto(acudienteEncontrado);

}

}
