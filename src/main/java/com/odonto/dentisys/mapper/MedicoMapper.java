package com.odonto.dentisys.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.odonto.dentisys.dto.MedicoDTO;
import com.odonto.dentisys.model.Medico;

@Component
public class MedicoMapper {

    public MedicoDTO toDTO(Medico medico) {
        if (medico == null) {
            return null;
        }

        MedicoDTO dto = new MedicoDTO();
        dto.setId(medico.getId());
        dto.setNombreCompleto(medico.getNombre() + " " + medico.getApellido());
        dto.setNombre(medico.getNombre());
        dto.setApellido(medico.getApellido());
        dto.setEspecialidad(medico.getEspecialidad());
        dto.setNumeroDocumento(medico.getNumeroDocumento());
        dto.setEmail(medico.getEmail());
        dto.setTelefono(medico.getTelefono());

        return dto;
    }

    public List<MedicoDTO> toDTOList(List<Medico> medicos) {
        return medicos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}