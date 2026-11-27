package com.sena.tinylog.servicesImplement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sena.tinylog.dtos.AcudienteDto;
import com.sena.tinylog.dtos.AcudienteDtoResponse;
import com.sena.tinylog.mappers.AcudienteMapper;
import com.sena.tinylog.models.Barrio;
import com.sena.tinylog.models.Ciudad;
import com.sena.tinylog.models.auth.Rol;
import com.sena.tinylog.models.auth.UsuarioAuth;
import com.sena.tinylog.models.auth.UsuarioAuthRol;
import com.sena.tinylog.models.usuarios.Acudiente;
import com.sena.tinylog.models.usuarios.TipoDocumento;
import com.sena.tinylog.models.usuarios.TiposAcudiente;
import com.sena.tinylog.repositoryes.AcudienteRepository;
import com.sena.tinylog.repositoryes.BarrioRepository;
import com.sena.tinylog.repositoryes.CiudadRepository;
import com.sena.tinylog.repositoryes.RolRepository;
import com.sena.tinylog.repositoryes.TipoAcudienteRepository;
import com.sena.tinylog.repositoryes.TipoDocumentoRepository;
import com.sena.tinylog.repositoryes.UsuarioAuthRepository;
import com.sena.tinylog.repositoryes.UsuarioAuthRolRepository;
import com.sena.tinylog.services.AcudienteServices;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AcudienteServicesImpl implements AcudienteServices {

        private final AcudienteRepository acudienteRepository;
        private final UsuarioAuthRepository usuarioAuthRepository;
        private final AcudienteMapper acudienteMapper;
        private final TipoAcudienteRepository ticlirepo;
        private final CiudadRepository ciudadRepository;
        private final BarrioRepository barrioRepository;
        private final PasswordEncoder passwordEncoder;
        private final UsuarioAuthRolRepository usuarioAuthRolRepository;
        private final RolRepository rolRepository;
        private final TipoDocumentoRepository tipoDocumentoRepository;

        @Override
        @Transactional
        public AcudienteDtoResponse guardar(AcudienteDto acudienteDto) {

                if (usuarioAuthRepository.existsByUser(acudienteDto.getUser())) {
                        throw new IllegalArgumentException(
                                        "El nombre de usuario ya está registrado");
                }

                // 1. Buscar tipo de acudiente y tipo de documento
                TiposAcudiente tipo = ticlirepo.findById(acudienteDto.getTipAcu())
                                .orElseThrow(() -> new EntityNotFoundException("tipo Acudiente no encontrado"));

                TipoDocumento tipoDocumento = tipoDocumentoRepository
                                .findById(acudienteDto.getTipoDoc())
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "Tipo de documento no encontrado"));

                // 2. Buscar ciudad y barrio
                Ciudad ciudad = ciudadRepository.findById(acudienteDto.getCiudad())
                                .orElseThrow(() -> new EntityNotFoundException("Ciudad no encontrada"));

                Barrio barrio = barrioRepository.findById(acudienteDto.getBarrio())
                                .orElseThrow(() -> new EntityNotFoundException("Barrio no encontrado"));

                // 3. Convertir DTO en Entidad y asignar relaciones
                Acudiente acudiente = acudienteMapper.toEntidad(acudienteDto);
                acudiente.setId(null);
                acudiente.setTipoAcudiente(tipo);
                acudiente.setTipoDoc(tipoDocumento);
                acudiente.setCiudad(ciudad);
                acudiente.setBarrio(barrio);

                // 4. Guardar acudiente en base de datos
                Acudiente acudienteGuardado = acudienteRepository.save(acudiente);

                // 5. Crear cuenta de autenticación
                UsuarioAuth auth = UsuarioAuth.builder()
                                .usuario(acudienteGuardado)
                                .user(acudienteDto.getUser())
                                .pass(passwordEncoder.encode(acudienteDto.getPassword()))
                                .build();

                usuarioAuthRepository.save(auth);

                // 6. Buscar rol y registrar asignación
                Rol rolAcudiente = rolRepository.findByNombre("ROLE_ACUDIENTE")
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "Rol de acudiente no encontrado"));

                UsuarioAuthRol asignacion = UsuarioAuthRol.builder()
                                .usuarioAuth(auth)
                                .rol(rolAcudiente)
                                .activo(true)
                                .fechaAsignacion(LocalDateTime.now())
                                .observacion("Rol asignado automáticamente al registrar el acudiente")
                                .build();

                usuarioAuthRolRepository.save(asignacion);

                return acudienteMapper.toDto(acudienteGuardado);
        }

        @Override
        @Transactional(readOnly = true)
        public List<AcudienteDtoResponse> ListarTodos() {
                return acudienteRepository.findAll()
                                .stream()
                                .map(acudienteMapper::toDto)
                                .toList();
        }

        @Override
        @Transactional(readOnly = true)
        public AcudienteDtoResponse buscarAcuId(Long id) {
                Acudiente acudiente = acudienteRepository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "No se encontro usuario con id: " + id));

                return acudienteMapper.toDto(acudiente);
        }

        @Override
        @Transactional
        public AcudienteDtoResponse actualizar(Long id, AcudienteDto acudienteDto) {
                Acudiente acudienteExistente = acudienteRepository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "No se encontro el acudiente con ID: " + id));

                Ciudad ciudad = ciudadRepository.findById(acudienteDto.getCiudad())
                                .orElseThrow(() -> new EntityNotFoundException("Ciudad no encontrada"));

                Barrio barrio = barrioRepository.findById(acudienteDto.getBarrio())
                                .orElseThrow(() -> new EntityNotFoundException("Barrio no encontrado"));

                // Atributos heredados del Usuario
                acudienteExistente.setNom(acudienteDto.getNomb());
                acudienteExistente.setApe(acudienteDto.getApell());
                acudienteExistente.setDir(acudienteDto.getDirec());
                acudienteExistente.setTel(acudienteDto.getTele());
                acudienteExistente.setCiudad(ciudad);
                acudienteExistente.setBarrio(barrio);

                // Atributos propios del Acudiente
                acudienteExistente.setTipoAcudiente(ticlirepo.findById(acudienteDto.getTipAcu())
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "No se encuentra tipo de acudiente " + acudienteDto.getTipAcu())));

                Acudiente acudienteActualizado = acudienteRepository.save(acudienteExistente);

                return acudienteMapper.toDto(acudienteActualizado);
        }

        @Override
        @Transactional
        public void eliminar(Long id) {
                if (!acudienteRepository.existsById(id)) {
                        throw new EntityNotFoundException(
                                        "No se encontro usuario con id: " + id);
                }

                acudienteRepository.deleteById(id);
        }
}