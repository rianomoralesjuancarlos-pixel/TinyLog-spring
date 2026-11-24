package com.sena.tinylog.servicesImplement;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sena.tinylog.dtos.DirectivoDto;
import com.sena.tinylog.dtos.DirectivoDtoResponse;
import com.sena.tinylog.mappers.DirectivoMapper;
import com.sena.tinylog.models.Ciudad;
import com.sena.tinylog.models.auth.UsuarioAuth;
import com.sena.tinylog.models.usuarios.Directivo;
import com.sena.tinylog.models.usuarios.TipoDocumento;
import com.sena.tinylog.repositoryes.CiudadRepository;
import com.sena.tinylog.repositoryes.DirectivoRepository;
import com.sena.tinylog.repositoryes.TipoDocumentoRepository;
import com.sena.tinylog.repositoryes.UsuarioAuthRepository;
import com.sena.tinylog.services.DirectivoServices;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DirectivoServicesImpl implements DirectivoServices {

    private final DirectivoRepository directivoRepository;
    private final UsuarioAuthRepository usuarioAuthRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final DirectivoMapper directivoMapper;
    private final CiudadRepository ciudadRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public DirectivoDtoResponse guardar(DirectivoDto directivoDto) {
        if (usuarioAuthRepository.existsByUser(directivoDto.getUser())) {
            throw new IllegalArgumentException("El user de usuario ya se encuentra registrado");
        }

        TipoDocumento tipoDocumento = tipoDocumentoRepository
                .findById(directivoDto.getTipoDoc())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de documento no encontrado"));

        if (directivoRepository.findByTipoDoc_IdAndNumDoc(
                tipoDocumento.getId(), directivoDto.getNumDoc()) != null) {
            throw new IllegalArgumentException("El documento ya se encuentra registrado");
        }

        Ciudad ciudad = ciudadRepository.findById(directivoDto.getCiudad())
                .orElseThrow(() -> new EntityNotFoundException("Ciudad no encontrada"));

        Directivo directivo = directivoMapper.toEntidad(directivoDto);
        directivo.setId(null);
        directivo.setTipoDoc(tipoDocumento);
        directivo.setCiudad(ciudad);
        if (directivoDto.getCargo() != null && !directivoDto.getCargo().isBlank()) {
            directivo.setCargo(directivoDto.getCargo());
        }

        Directivo directivoGuardado = directivoRepository.save(directivo);

        UsuarioAuth usuarioAuth = UsuarioAuth.builder()
                .usuario(directivoGuardado)
                .user(directivoDto.getUser())
                .pass(passwordEncoder.encode(directivoDto.getPassword()))
                .habilitado(true)
                .build();

        usuarioAuthRepository.save(usuarioAuth);

        return directivoMapper.toDto(directivoGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DirectivoDtoResponse> listarTodos() {
        return directivoRepository.findAll()
                .stream()
                .map(directivoMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DirectivoDtoResponse buscarPorId(Long id) {
        Directivo directivo = directivoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Directivo no encontrado con id: " + id));
        return directivoMapper.toDto(directivo);
    }

    @Override
    @Transactional(readOnly = true)
    public DirectivoDtoResponse buscarPorNumDoc(String numDoc) {
        if (numDoc == null || numDoc.isBlank()) {
            throw new IllegalArgumentException("El número de documento es obligatorio");
        }

        return directivoRepository.findByNumDoc(numDoc)
                .map(directivoMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Directivo con numero de documento: " + numDoc + " no encontrado"));
    }

    @Override
    @Transactional
    public DirectivoDtoResponse actualizar(Long id, DirectivoDto directivoDto) {
        Directivo directivoEncontrado = directivoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontro Directivo con Id: " + id));

        Ciudad ciudad = ciudadRepository.findById(directivoDto.getCiudad())
                .orElseThrow(() -> new EntityNotFoundException("Ciudad no encontrada"));

        directivoEncontrado.setNom(directivoDto.getNomb());
        directivoEncontrado.setApe(directivoDto.getApell());
        directivoEncontrado.setDir(directivoDto.getDirec());
        directivoEncontrado.setTel(directivoDto.getTele());
        directivoEncontrado.setCiudad(ciudad);
        if (directivoDto.getCargo() != null && !directivoDto.getCargo().isBlank()) {
            directivoEncontrado.setCargo(directivoDto.getCargo());
        }

        Directivo directivoActualizado = directivoRepository.save(directivoEncontrado);
        return directivoMapper.toDto(directivoActualizado);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Directivo directivo = directivoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Directivo con id: " + id + " no encontrado"));
        directivoRepository.delete(directivo);
    }
}