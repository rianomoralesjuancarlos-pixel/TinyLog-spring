package com.sena.tinylog.mappers;

import org.springframework.stereotype.Component;

import com.sena.tinylog.dtos.AcudienteDto;
import com.sena.tinylog.dtos.AcudienteDtoResponse;
import com.sena.tinylog.models.usuarios.Acudiente;


@Component
public class AcudienteMapperImpl implements AcudienteMapper{

    @Override
    public AcudienteDtoResponse toDto (Acudiente acudiente) {

        // valida que tenga datos
        if (acudiente == null) {
            return null;
        }

        // concertir de Acudiente a AcudienteDto
        return AcudienteDtoResponse.builder()
                // atributos de UsuarioDto
                .id(acudiente.getId())
                .nomb(acudiente.getNom())
                .apell(acudiente.getApe())
                .direc(acudiente.getDir())
                .tele(acudiente.getTel())
                .tipoDoc(acudiente.getTipoDoc().getId())
                .nomTipoDoc(acudiente.getTipoDoc().getNombre())
                .numDoc(acudiente.getNumDoc())

                // atributos de AcudienteDto
                .tipAcu(acudiente.getTipoAcudiente().getNom_TipoAcu())
                .build();
    }

    @Override
    public Acudiente toEntidad(AcudienteDto acudienteDto) {

        // valida que tenga datos
        if (acudienteDto == null) {
            return null;
        }

        return Acudiente.builder()
                // Atrubutos de usuarioDto
                .id(acudienteDto.getId())
                .nom(acudienteDto.getNomb())
                .ape(acudienteDto.getApell())
                .tel(acudienteDto.getTele())
                .dir(acudienteDto.getDirec())

                // numero de documento y tipo de document
                .numDoc(acudienteDto.getNumDoc())
                // atributos de AcudienteDto
                .tipoAcudiente(null) // Se establece como null, ya que se asignará posteriormente en el servicio

                // No es necesario mapear tipo de acudiente porque
                // tiene relacion con la entidad TiposAcudiente
                .build();
    }

}