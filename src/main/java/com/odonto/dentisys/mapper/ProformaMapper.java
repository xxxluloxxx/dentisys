package com.odonto.dentisys.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.odonto.dentisys.dto.ProformaDTO;
import com.odonto.dentisys.model.Proforma;

@Component
public class ProformaMapper {

    public ProformaDTO toDTO(Proforma proforma) {
        if (proforma == null) {
            return null;
        }

        ProformaDTO dto = new ProformaDTO();
        dto.setId(proforma.getId());
        dto.setPacienteId(proforma.getPaciente().getId());
        dto.setPacienteNombre(proforma.getPaciente().getNombre() + " " + proforma.getPaciente().getApellido());
        dto.setMedicoId(proforma.getMedico().getId());
        dto.setMedicoNombre(proforma.getMedico().getNombre() + " " + proforma.getMedico().getApellido());
        dto.setSubtotal(proforma.getSubtotal());
        dto.setIva(proforma.getIva());
        dto.setDescuento(proforma.getDescuento());
        dto.setTotal(proforma.getTotal());
        dto.setEstado(proforma.getEstado());
        dto.setObservaciones(proforma.getObservaciones());
        dto.setCreatedAt(proforma.getCreatedAt());
        dto.setUpdatedAt(proforma.getUpdatedAt());

        return dto;
    }

    public List<ProformaDTO> toDTOList(List<Proforma> proformas) {
        return proformas.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}