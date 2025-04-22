package com.odonto.dentisys.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.odonto.dentisys.dto.PacienteDTO;
import com.odonto.dentisys.model.Paciente;

@Component
public class PacienteMapper {

    public PacienteDTO toDTO(Paciente paciente) {
        if (paciente == null) {
            return null;
        }

        PacienteDTO dto = new PacienteDTO();
        dto.setId(paciente.getId());
        dto.setNombreCompleto(paciente.getNombre() + " " + paciente.getApellido());
        dto.setIdentificacion(paciente.getIdentificacion());
        dto.setFechaNacimiento(paciente.getFechaNacimiento());
        dto.setGenero(paciente.getGenero());
        dto.setTelefono(paciente.getTelefono());
        dto.setEmail(paciente.getEmail());
        dto.setDireccion(paciente.getDireccion());

        return dto;
    }

    public List<PacienteDTO> toDTOList(List<Paciente> pacientes) {
        return pacientes.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}