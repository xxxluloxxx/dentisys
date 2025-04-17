package com.odonto.dentisys.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.odonto.dentisys.dto.ProcedimientoDTO;
import com.odonto.dentisys.model.Procedimiento;

@Component
public class ProcedimientoMapper {

    public ProcedimientoDTO toDTO(Procedimiento procedimiento) {
        if (procedimiento == null) {
            return null;
        }

        ProcedimientoDTO dto = new ProcedimientoDTO();
        dto.setId(procedimiento.getId());
        dto.setFichaId(procedimiento.getFicha().getId());
        dto.setProcedimiento(procedimiento.getProcedimiento());
        dto.setObservaciones(procedimiento.getObservaciones());
        dto.setCreatedAt(procedimiento.getCreatedAt());
        dto.setUpdatedAt(procedimiento.getUpdatedAt());

        return dto;
    }

    public List<ProcedimientoDTO> toDTOList(List<Procedimiento> procedimientos) {
        return procedimientos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}