package com.odonto.dentisys.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.odonto.dentisys.dto.CobranzaDTO;
import com.odonto.dentisys.model.Cobranza;

@Component
public class CobranzaMapper {

    public CobranzaDTO toDTO(Cobranza cobranza) {
        if (cobranza == null) {
            return null;
        }

        CobranzaDTO dto = new CobranzaDTO();
        dto.setId(cobranza.getId());
        dto.setProformaId(cobranza.getProforma() != null ? cobranza.getProforma().getId() : null);
        dto.setFechaPago(cobranza.getFechaPago());
        dto.setMonto(cobranza.getMonto());
        dto.setMetodoPago(cobranza.getMetodoPago());
        dto.setEstado(cobranza.getEstado());
        dto.setObservaciones(cobranza.getObservaciones());
        dto.setCreatedAt(cobranza.getCreatedAt());

        return dto;
    }

    public List<CobranzaDTO> toDTOList(List<Cobranza> cobranzas) {
        if (cobranzas == null) {
            return null;
        }

        return cobranzas.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}