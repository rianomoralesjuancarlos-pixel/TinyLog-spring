package com.sena.tinylog.mappers;

import org.springframework.stereotype.Component;
import com.sena.tinylog.dtos.AcudienteDto;
import com.sena.tinylog.dtos.AcudienteDtoResponse;
import com.sena.tinylog.models.usuarios.Acudiente;

@Component
public class AcudienteMapperImpl implements AcudienteMapper {

    @Override
    public AcudienteDtoResponse toDto(Acudiente acudiente) {

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
                .numDoc(acudiente.getNumDoc())

                // atributos de AcudienteDto
                .tipAcu(acudiente.getTipoAcudiente().getNom_TipoAcu())
                .build();
    }

    @Override
    public Acudiente toEntidad(AcudienteDto acudienteDto) {

        if (acudienteDto == null) {
            return null;
        }

        Acudiente acudiente = new Acudiente();
        // Atributos de Usuario
        acudiente.setId(acudienteDto.getId());
        acudiente.setNom(acudienteDto.getNomb());
        acudiente.setApe(acudienteDto.getApell());
        acudiente.setTel(acudienteDto.getTele());
        acudiente.setDir(acudienteDto.getDirec());
        acudiente.setNumDoc(acudienteDto.getNumDoc());

        // Se establece como null, ya que se asignará posteriormente en el servicio
        acudiente.setTipoAcudiente(null);
        acudiente.setTipoDoc(null);

        return acudiente;
    }

}