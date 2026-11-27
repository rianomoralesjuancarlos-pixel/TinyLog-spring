package com.sena.tinylog.servicesImplement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sena.tinylog.dtos.DocenteDto;
import com.sena.tinylog.mappers.DocenteMapper;
import com.sena.tinylog.models.Barrio;
import com.sena.tinylog.models.Ciudad;
import com.sena.tinylog.models.auth.Rol;
import com.sena.tinylog.models.auth.UsuarioAuth;
import com.sena.tinylog.models.auth.UsuarioAuthRol;
import com.sena.tinylog.models.usuarios.Docente;
import com.sena.tinylog.models.usuarios.TipoDocumento;
import com.sena.tinylog.repositoryes.BarrioRepository;
import com.sena.tinylog.repositoryes.CiudadRepository;
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
    private final CiudadRepository ciudadRepository;
    private final BarrioRepository barrioRepository;
    private final RolRepository rolRepository;
    private final UsuarioAuthRepository usuarioAuthRepository;
    private final UsuarioAuthRolRepository usuarioAuthRolRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
<<<<<<< HEAD
    public DocenteDto guardar(DocenteDto docenteDto) {
        Docente docente = docenteMapper.convierteEntidad(docenteDto);
=======
    public DocenteDto guardar(DocenteDto dto) {
        String userNormalizado = dto.getUser().trim().toLowerCase();

        if (usuarioAuthRepository.existsByUser(userNormalizado)) {
            throw new IllegalArgumentException("El nombre del usuario ya esta registrado");
        }

        TipoDocumento tipoDocumento = tipoDocumentoRepository
                .findById(dto.getTipoDoc())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de documento no encontrado"));

        Ciudad ciudad = ciudadRepository
                .findById(dto.getCiudad())
                .orElseThrow(() -> new EntityNotFoundException("Ciudad no encontrada"));

        Barrio barrio = barrioRepository
                .findById(dto.getBarrio())
                .orElseThrow(() -> new EntityNotFoundException("Barrio no encontrado"));

        Rol rolDocente = rolRepository
<<<<<<< HEAD
                .findByNombre("ROLE_DOCENTE")
=======
                .findByNombreIgnoreCase("DOCENTE")
>>>>>>> b4ccfe9d7b16505d6cb6fd5f7b31a05fe323b185
                .orElseThrow(() -> new EntityNotFoundException("Rol DOCENTE no encontrado"));

        Docente docente = docenteMapper.toEntidad(dto);
        docente.setId(null);
        docente.setTipoDoc(tipoDocumento);
        docente.setNumDoc(dto.getNumDoc());
<<<<<<< HEAD
        docente.setCiudad(ciudad);
        docente.setBarrio(barrio);
=======
        docente.setSalario(dto.getSueldo());
>>>>>>> b4ccfe9d7b16505d6cb6fd5f7b31a05fe323b185

>>>>>>> e84aa2f56d5c581ce4fa2ec600d01cbad1cbe5ad
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
<<<<<<< HEAD
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Docente no encontrado con id: " + id));
        return docenteMapper.convierteDto(docente);
=======
        Docente docente = obtenerDocentePorId(id);
        return docenteMapper.toDto(docente);
>>>>>>> e84aa2f56d5c581ce4fa2ec600d01cbad1cbe5ad
    }

    @Override
    @Transactional
<<<<<<< HEAD
    public DocenteDto actualizar(Long id, DocenteDto docenteDto) {
        Docente docenteEncontrado = docenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Docente no encontrado con id: " + id));

        Ciudad ciudad = ciudadRepository
                .findById(docenteDto.getCiudad())
                .orElseThrow(() -> new EntityNotFoundException("Ciudad no encontrada"));

        Barrio barrio = barrioRepository
                .findById(docenteDto.getBarrio())
                .orElseThrow(() -> new EntityNotFoundException("Barrio no encontrado"));

        docenteEncontrado.setNom(docenteDto.getNomb());
        docenteEncontrado.setApe(docenteDto.getApell());
        docenteEncontrado.setDir(docenteDto.getDirec());
        docenteEncontrado.setTel(docenteDto.getTele());
        docenteEncontrado.setNumDoc(docenteDto.getNumDoc());
        docenteEncontrado.setCiudad(ciudad);
        docenteEncontrado.setBarrio(barrio);

        Docente docenteActualizado = docenteRepository.save(docenteEncontrado);
        return docenteMapper.convierteDto(docenteActualizado);
=======
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
>>>>>>> e84aa2f56d5c581ce4fa2ec600d01cbad1cbe5ad
    }

    @Override
    @Transactional
<<<<<<< HEAD
    public void eliminar(Long id) {
        if (!docenteRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Usuario con :" + id + " No encontrado");
        }
        docenteRepository.deleteById(id);
    }
}
=======
<<<<<<< HEAD
    public DocenteDto eliminar(Long id) {
        Docente docenteEncontrado = docenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Docente no encontrado con id: " + id));

        DocenteDto dtoEliminado = docenteMapper.convierteDto(docenteEncontrado);
        docenteRepository.delete(docenteEncontrado);
        return dtoEliminado;
=======
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
>>>>>>> e84aa2f56d5c581ce4fa2ec600d01cbad1cbe5ad
    }
}
>>>>>>> b4ccfe9d7b16505d6cb6fd5f7b31a05fe323b185
