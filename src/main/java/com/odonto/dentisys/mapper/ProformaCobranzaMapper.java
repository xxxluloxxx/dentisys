package com.odonto.dentisys.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.odonto.dentisys.dto.ProformaCobranzaDTO;
import com.odonto.dentisys.model.Cobranza;
import com.odonto.dentisys.model.Proforma;

@Component
public class ProformaCobranzaMapper {

    public ProformaCobranzaDTO toDTO(Proforma proforma, List<Cobranza> cobranzas) {
        ProformaCobranzaDTO dto = new ProformaCobranzaDTO();
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

        List<ProformaCobranzaDTO.CobranzaDTO> cobranzasDTO = cobranzas.stream()
                .map(this::toCobranzaDTO)
                .collect(Collectors.toList());
        dto.setCobranzas(cobranzasDTO);

        return dto;
    }

    private ProformaCobranzaDTO.CobranzaDTO toCobranzaDTO(Cobranza cobranza) {
        ProformaCobranzaDTO.CobranzaDTO dto = new ProformaCobranzaDTO.CobranzaDTO();
        dto.setId(cobranza.getId());
        dto.setFechaPago(cobranza.getFechaPago());
        dto.setMonto(cobranza.getMonto());
        dto.setMetodoPago(cobranza.getMetodoPago());
        dto.setEstado(cobranza.getEstado());
        dto.setObservaciones(cobranza.getObservaciones());
        dto.setCreatedAt(cobranza.getCreatedAt());
        return dto;
    }
}