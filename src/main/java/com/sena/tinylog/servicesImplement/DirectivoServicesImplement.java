package com.sena.tinylog.servicesImplement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sena.tinylog.dtos.DirectivoDto;
import com.sena.tinylog.mappers.DirectivoMapper;
import com.sena.tinylog.models.auth.UsuarioAuth;
import com.sena.tinylog.models.auth.UsuarioAuthRol;
import com.sena.tinylog.models.usuarios.Directivo;
import com.sena.tinylog.models.usuarios.TipoDocumento;
import com.sena.tinylog.repositoryes.DirectivoRepository;
import com.sena.tinylog.repositoryes.RolRepository;
import com.sena.tinylog.repositoryes.TipoDocumentoRepository;
import com.sena.tinylog.repositoryes.UsuarioAuthRepository;
import com.sena.tinylog.repositoryes.UsuarioAuthRolRepository;
import com.sena.tinylog.services.DirectivoServices;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class DirectivoServicesImplement implements DirectivoServices {

private final DirectivoRepository directivoRepository;
private final DirectivoMapper directivoMapper;
private final TipoDocumentoRepository tipoDocumentoRepository;
private final RolRepository rolRepository;
private final UsuarioAuthRepository usuarioAuthRepository;
private final UsuarioAuthRolRepository usuarioAuthRolRepository;
private final PasswordEncoder passwordEncoder;

    // Guardar o crear Directivo (Solo permite 1 en todo el sistema)
@Override
@Transactional
public DirectivoDto guardar(DirectivoDto dto) {

        // 1. validar que usuario no exista
        if (usuarioAuthRepository.exisexistsByUser(dto.getUser())){
                throw new IllegalArgumentException(
                        "El nombre del usuario ya esta registrado"
                );
        }

        //2.Buscar el tipo de docuemnto
        TipoDocumento tipoDocumento = tipoDocumentoRepository
                .findById(dto.getTipoDoc())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Tipo de documento no encontrado"
                ));
        
        
        //4.Convertir de Dto a entidad
        Directivo directivo = directivoMapper.toEntidad(dto);

        //5.asignar datos
        directivo.setId(null);
        directivo.setTipoDoc(tipoDocumento);
        directivo.setNumDoc(null);

        //5.guardar directivo

        Directivo directivoGuardado = directivoRepository.save(directivo);

        //6.crear cuenta de autenticacion
        UsuarioAuth auth = UsuarioAuth.builder()
                .usuario(directivoGuardado)
                .user(dto.getUser())
                .pass(passwordEncoder.encode(dto.getPassword()))
                .build();
                
                usuarioAuthRepository.save(auth);

        

        //7.asignar automaticamente el rol correspondinte
        UsuarioAuthRol asignacion = UsuarioAuthRol.builder()
                .usuarioAuth(auth)
                .rol(null)
                .activo(true)
                .fechaAsignacion(LocalDateTime.now())
                .observacion("Rol asignado automaticamente segun el cargo")
                .build();

                usuarioAuthRolRepository.save(asignacion);

        //8. retornar los datos del directivo
        return directivoMapper.toDto(directivoGuardado);

        }
        //COnsultar docente por id
        @Override 
        @Transactional(readOnly = true)
        public List<DirectivoDto> listarDir(){
                return directivoRepository.findAll()
                .stream()
                .map(directivoMapper::toDto)
                .toList();
        }
        //directivo por id
        @Override 
        @Transactional(readOnly = true)
        public DirectivoDto buscarPorId(Long id){
                Directivo directivo = directivoRepository.findById(id)
                        .orElseThrow(()-> new EntityNotFoundException(
                                "no se encontro directivo co id" + id));

                return directivoMapper.toDto(directivo);
        }
        @Override
        @Transactional
        public DirectivoDto actualizar(Long id, DirectivoDto dto) {
        Directivo directivoExistente = directivoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No se encontro trabajador con Id: " + id));

        // Actualiza datos heredados de Usuario
        directivoExistente.setNom(dto.getNomb());
        directivoExistente.setApe(dto.getApell());
        directivoExistente.setDir(dto.getDirec());
        directivoExistente.setTel(dto.getTele());

        Directivo directivoActualizado = directivoRepository.save(directivoExistente);

        return directivoMapper.toDto(directivoActualizado);



        }
        @Override 
        @Transactional 
        public void  eliminar(Long id){
                if(!directivoRepository.existsById(id)){
                        throw new EntityNotFoundException(
                                "no se encontro directivo con id" + id);
                }
                directivoRepository.deleteById(id);
                }
                }
