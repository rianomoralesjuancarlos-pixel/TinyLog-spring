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
    public DocenteDto guardar(DocenteDto docenteDto) {
        TipoDocumento tipoDocumento = tipoDocumentoRepository
                .findById(docenteDto.getTipoDoc())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de documento no encontrado"));

        Ciudad ciudad = ciudadRepository
                .findById(docenteDto.getCiudad())
                .orElseThrow(() -> new EntityNotFoundException("Ciudad no encontrada"));

        Barrio barrio = barrioRepository
                .findById(docenteDto.getBarrio())
                .orElseThrow(() -> new EntityNotFoundException("Barrio no encontrado"));

        Rol rolDocente = rolRepository
                .findByNombre("ROLE_DOCENTE") // Cambia por el método real de RolRepository si tiene otro nombre
                .orElseThrow(() -> new EntityNotFoundException("Rol DOCENTE no encontrado"));

        String userNormalizado = docenteDto.getUser().trim().toLowerCase(); // Ajusta al getter de usuario en DocenteDto

        Docente docente = docenteMapper.toEntidad(docenteDto);
        docente.setId(null);
        docente.setTipoDoc(tipoDocumento);
        docente.setNumDoc(docenteDto.getNumDoc());
        docente.setCiudad(ciudad);
        docente.setBarrio(barrio);

        Docente docenteGuardado = docenteRepository.save(docente);

        UsuarioAuth auth = UsuarioAuth.builder()
                .usuario(docenteGuardado)
                .user(userNormalizado)
                .pass(passwordEncoder.encode(docenteDto.getPassword()))
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
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Docente no encontrado con id: " + id));
        return docenteMapper.toDto(docente);
    }

    @Override
    @Transactional
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
        return docenteMapper.toDto(docenteActualizado);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!docenteRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuario con :" + id + " No encontrado");
        }
        docenteRepository.deleteById(id);
    }
}