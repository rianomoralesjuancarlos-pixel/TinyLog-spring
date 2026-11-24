package com.sena.tinylog.servicesImplement;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sena.tinylog.dtos.AcudienteDto;
import com.sena.tinylog.dtos.AcudienteDtoResponse;
import com.sena.tinylog.mappers.AcudienteMapper;
import com.sena.tinylog.models.auth.Rol;
import com.sena.tinylog.models.auth.UsuarioAuth;
import com.sena.tinylog.models.auth.UsuarioAuthRol;
import com.sena.tinylog.models.usuarios.Acudiente;
import com.sena.tinylog.models.usuarios.TipoDocumento;
import com.sena.tinylog.models.usuarios.TiposAcudiente;
import com.sena.tinylog.repositoryes.AcudienteRepository;
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
        private final PasswordEncoder passwordEncoder;
        private final UsuarioAuthRolRepository usuarioAuthRolRepository;
        private final RolRepository rolRepository;
        private final TipoDocumentoRepository tipoDocumentoRepository;

        @Override
        // garantiza que todas las operaciones de base de datos dentro de un método se
        // ejecuten como una única unidad de trabajo indivisible (todo se guarda o todo
        // se deshace si algo falla).
        // Si ocurre una excepción que provoque rollback, se revierten los cambios
        // realizados dentro de ella.
        // import org.springframework.transaction.annotation.Transactional;
        @Transactional
        public AcudienteDtoResponse guardar(AcudienteDto acudienteDto) {

                // 1. buscar id de tipoAcudiente en base de datos
                TiposAcudiente tipo = ticlirepo.findById(acudienteDto.getTipAcu())
                                .orElseThrow(() -> new EntityNotFoundException("tipo Acudiente no encontrado"));

                // 2. Validar que el tipo de documento exista
                TipoDocumento tipoDocumento = tipoDocumentoRepository
                                .findById(acudienteDto.getTipoDoc())
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "Tipo de documento no encontrado"));

                // 2. Convierte DTO en Entidad
                Acudiente acudiente = acudienteMapper.toEntidad(acudienteDto);

                // 3.El ID del acudiente debe ser nul porque es autoincremental
                acudiente.setId(null);

                // 4.asigna tipoacudiente"id"
                acudiente.setTipoAcudiente(tipo);

                // Inicializa los puntos en 0 al crear un nuevo acudiente
                acudiente.setTipoDoc(tipoDocumento);

                // 5.guarda acudiente en base de datos
                Acudiente acudienteGuardado = acudienteRepository.save(acudiente);

                // 6. Crear cuenta de autenticación
                // Validar que el nombre de usuario no exista en la base de datos antes de crear
                // la cuenta de autenticación. Esto evita duplicados y asegura que cada acudiente
                // tenga un nombre de usuario único para iniciar sesión.
                if (usuarioAuthRepository.existsByUser(acudienteDto.getUser())) {
                        throw new IllegalArgumentException(
                                        "El nombre de usuario ya está registrado");
                }
                UsuarioAuth auth = UsuarioAuth.builder()
                                .usuario(acudienteGuardado)
                                .user(acudienteDto.getUser())
                                .pass(passwordEncoder.encode(acudienteDto.getPassword()))
                                .build();

                usuarioAuthRepository.save(auth);

                // 7. Buscar el rol de acudiente en la base de datos para extraer su ID y
                // asignarlo al usuario creado. Esto asegura que el nuevo acudiente tenga los
                // permisos y accesos correspondientes a su rol en el sistema.
                Rol rolAcudiente = rolRepository.findByNombre("ROLE_ACUDIENTE")
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "Rol de acudiente no encontrado"));

                // 8. Registrar asignación del rol
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

        // Consultar todos los acudiente
        @Override
        @Transactional(readOnly = true )
        public List<AcudienteDtoResponse> ListarTodos() {
                return acudienteRepository.findAll()
                                .stream()
                                .map(acudienteMapper::toDto)
                                .toList();
        }

        // Consulta acudiente por ID
        @Override
        @Transactional(readOnly = true)
        public AcudienteDtoResponse buscarAcuId(Long id) {

                Acudiente acudiente = acudienteRepository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "No se encontro usuario con id: " + id));

                return acudienteMapper.toDto(acudiente);
        }

        // 4 Actualizar Acudiente
        @Override
        @Transactional
        public AcudienteDtoResponse actualizar(Long id, AcudienteDto acudienteDto) {
                Acudiente acudienteExistente = acudienteRepository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "No se encontro el acudiente con ID: " + id));

                // Artibutos heredadosdel Usuario
                acudienteExistente.setNom(acudienteDto.getNomb());
                acudienteExistente.setApe(acudienteDto.getApell());
                acudienteExistente.setDir(acudienteDto.getDirec());
                acudienteExistente.setTel(acudienteDto.getTele());

                // Atributos propios del Acudiente
                acudienteExistente.setTipoAcudiente(ticlirepo.findById(acudienteDto.getTipAcu())
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "No se encuentra tipo de acudiente " + acudienteDto.getTipAcu())));
                // acudienteExistente.setPuntos(acudienteDto.getPts());
                // Los puntos deben modificarse mediante una operación específica, por ejemplo,
                // cuando el acudiente realiza una compra o recibe un servicio que acumula puntos.

                Acudiente acudienteActualizado = acudienteRepository.save(acudienteExistente);

                return acudienteMapper.toDto(acudienteActualizado);
        }

        // Eliminar Acudiente
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
