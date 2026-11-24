package com.sena.tinylog.servicesImplement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sena.tinylog.dtos.DocenteDto;
import com.sena.tinylog.mappers.DocenteMapper;
import com.sena.tinylog.models.auth.Rol;
import com.sena.tinylog.models.auth.UsuarioAuth;
import com.sena.tinylog.models.auth.UsuarioAuthRol;
import com.sena.tinylog.models.usuarios.Docente;
import com.sena.tinylog.models.usuarios.TipoDocumento;
import com.sena.tinylog.repositoryes.DocenteRepository;
import com.sena.tinylog.repositoryes.RolRepository;
import com.sena.tinylog.repositoryes.TipoDocumentoRepository;
import com.sena.tinylog.repositoryes.UsuarioAuthRepository;
import com.sena.tinylog.repositoryes.UsuarioAuthRolRepository;
import com.sena.tinylog.services.DocenteServices;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocenteServicesImplement implements DocenteServices {

    private final DocenteRepository docenteRepository;
    private final DocenteMapper docenteMapper;
    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final RolRepository rolRepository;
    private final UsuarioAuthRepository usuarioAuthRepository;
    private final UsuarioAuthRolRepository usuarioAuthRolRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public DocenteDto guardar(DocenteDto dto) {
        String userNormalizado = dto.getUser().trim().toLowerCase();

        if (usuarioAuthRepository.existsByUser(userNormalizado)) {
            throw new IllegalArgumentException("El nombre del usuario ya esta registrado");
        }

        TipoDocumento tipoDocumento = tipoDocumentoRepository
                .findById(dto.getTipoDoc())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de documento no encontrado"));

        Rol rolDocente = rolRepository
                .findByNombreIgnoreCase("DOCENTE")
                .orElseThrow(() -> new EntityNotFoundException("Rol DOCENTE no encontrado"));

        Docente docente = docenteMapper.toEntidad(dto);
        docente.setId(null);
        docente.setTipoDoc(tipoDocumento);
        docente.setNumDoc(dto.getNumDoc());
        docente.setSalario(dto.getSueldo());

        Docente docenteGuardado = docenteRepository.save(docente);

        UsuarioAuth auth = UsuarioAuth.builder()
                .usuario(docenteGuardado)
                .user(userNormalizado)
                .pass(passwordEncoder.encode(dto.getPassword()))
                .build();
        UsuarioAuth authGuardado = usuarioAuthRepository.save(auth);

        UsuarioAuthRol asignacion = UsuarioAuthRol.builder()
                .usuarioAuth(authGuardado)
                .rol(rolDocente)
                .activo(true)
                .fechaAsignacion(LocalDateTime.now())
                .observacion("Rol asignado automaticamente segun el cargo")
                .build();
        usuarioAuthRolRepository.save(asignacion);

        return docenteMapper.toDto(docenteGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocenteDto> listarDoc() {
        return docenteRepository.findAll()
                .stream()
                .map(docenteMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DocenteDto buscarPorId(Long id) {
        Docente docente = obtenerDocentePorId(id);
        return docenteMapper.toDto(docente);
    }

    @Override
    @Transactional
    public DocenteDto actualizar(Long id, DocenteDto dto) {
        Docente docenteExistente = obtenerDocentePorId(id);

        if (dto.getNomb() != null) {
            docenteExistente.setNom(dto.getNomb());
        }
        if (dto.getApell() != null) {
            docenteExistente.setApe(dto.getApell());
        }
        if (dto.getDirec() != null) {
            docenteExistente.setDir(dto.getDirec());
        }
        if (dto.getTele() != null) {
            docenteExistente.setTel(dto.getTele());
        }
        if (dto.getNumDoc() != null) {
            docenteExistente.setNumDoc(dto.getNumDoc());
        }
        if (dto.getSueldo() != null) {
            docenteExistente.setSalario(dto.getSueldo());
        }
        if (dto.getTipoDoc() != null) {
            TipoDocumento tipoDocumento = tipoDocumentoRepository
                    .findById(dto.getTipoDoc())
                    .orElseThrow(() -> new EntityNotFoundException("Tipo de documento no encontrado"));
            docenteExistente.setTipoDoc(tipoDocumento);
        }

        UsuarioAuth auth = usuarioAuthRepository.findByUsuario_Id(docenteExistente.getId()).orElse(null);

        if (dto.getUser() != null && !dto.getUser().isBlank()) {
            String nuevoUser = dto.getUser().trim().toLowerCase();
            usuarioAuthRepository.findByUser(nuevoUser).ifPresent(existente -> {
                if (!existente.getUsuario().getId().equals(docenteExistente.getId())) {
                    throw new IllegalArgumentException("El nombre del usuario ya esta registrado");
                }
            });
            if (auth != null) {
                auth.setUser(nuevoUser);
            }
        }

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            if (auth != null) {
                auth.setPass(passwordEncoder.encode(dto.getPassword()));
            }
        }

        if (auth != null) {
            usuarioAuthRepository.save(auth);
        }

        Docente docenteActualizado = docenteRepository.save(docenteExistente);
        return docenteMapper.toDto(docenteActualizado);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Docente docenteExistente = obtenerDocentePorId(id);

        usuarioAuthRepository.findByUsuario_Id(docenteExistente.getId()).ifPresent(auth -> {
            usuarioAuthRolRepository.deleteAllByUsuarioAuth_Id(auth.getId());
            usuarioAuthRepository.delete(auth);
        });

        docenteRepository.delete(docenteExistente);
    }

    private Docente obtenerDocentePorId(Long id) {
        return docenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontro docente con id: " + id));
    }
}