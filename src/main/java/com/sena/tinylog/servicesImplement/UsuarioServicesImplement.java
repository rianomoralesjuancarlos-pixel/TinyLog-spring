package com.sena.tinylog.servicesImplement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sena.tinylog.dtos.UsuarioDto;
import com.sena.tinylog.mappers.UsuarioMapper;
import com.sena.tinylog.models.Usuario;
import com.sena.tinylog.repositoryes.UsuarioRepository;
import com.sena.tinylog.services.UsuarioServices;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 


public class UsuarioServicesImplement implements UsuarioServices {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Override 
    public UsuarioDto guardar(UsuarioDto usuarioDto){
    Usuario usuario = usuarioMapper.ConvierteaEntidad(usuarioDto);

    Usuario Usuarioguardado = usuarioRepository.save(usuario);

    return usuarioMapper.ConvierteDto(Usuarioguardado);


    }

    //listar todos los usuarios

    @Override 
    public List<UsuarioDto>ListarTodos(){
        return usuarioRepository.findAll()
        .stream()
        .map(usuarioMapper::ConvierteDto)
        .toList();
    }
    //consultar usuario por id

    @Override 
    public UsuarioDto buscarPorId (Long id){
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() ->new EntityNotFoundException("usuario no encontrado"+id)
    );
    
        return usuarioMapper.ConvierteDto(usuario);

    } 

    //actualizar usuario
    @Override
    public UsuarioDto actualizar(Long id, UsuarioDto usuarioDto) {
        Usuario usuarioEncontrado = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con id: " + id));

                //Actualizar datos
                usuarioEncontrado.setNom(usuarioDto.getNomb());
                usuarioEncontrado.setApe(usuarioDto.getApell());
                usuarioEncontrado.setCorr(usuarioDto.getCorre());
                usuarioEncontrado.setTel(usuarioDto.getTele());


                Usuario usuarioActualizado = usuarioRepository.save(usuarioEncontrado);
                return usuarioMapper.ConvierteDto(usuarioActualizado);
        
    
    }

    @Override
    public UsuarioDto eliminar(Long id) {
        Usuario usuarioEncontrado = usuarioRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con id: " + id));

        usuarioRepository.delete(usuarioEncontrado);
        return usuarioMapper.ConvierteDto(usuarioEncontrado);

}
}



