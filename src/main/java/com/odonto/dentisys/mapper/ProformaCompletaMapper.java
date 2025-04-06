package com.odonto.dentisys.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.odonto.dentisys.dto.CobranzaDTO;
import com.odonto.dentisys.dto.ProformaCompletaDTO;
import com.odonto.dentisys.dto.ProformaDTO;
import com.odonto.dentisys.dto.ProformaDetalleDTO;
import com.odonto.dentisys.model.Cobranza;
import com.odonto.dentisys.model.Proforma;
import com.odonto.dentisys.service.CobranzaService;

@Component
public class ProformaCompletaMapper {

    @Autowired
    private ProformaMapper proformaMapper;

    @Autowired
    private ProformaDetalleMapper proformaDetalleMapper;

    @Autowired
    private CobranzaService cobranzaService;

    public ProformaCompletaDTO toDTO(Proforma proforma) {
        if (proforma == null) {
            return null;
        }

        ProformaCompletaDTO dto = new ProformaCompletaDTO();

        // Mapear datos básicos de la proforma
        ProformaDTO proformaDTO = proformaMapper.toDTO(proforma);
        dto.setId(proformaDTO.getId());
        dto.setPacienteId(proformaDTO.getPacienteId());
        dto.setPacienteNombre(proformaDTO.getPacienteNombre());
        dto.setMedicoId(proformaDTO.getMedicoId());
        dto.setMedicoNombre(proformaDTO.getMedicoNombre());
        dto.setFechaEmision(proformaDTO.getFechaEmision());
        dto.setSubtotal(proformaDTO.getSubtotal());
        dto.setIva(proformaDTO.getIva());
        dto.setTotal(proformaDTO.getTotal());
        dto.setEstado(proformaDTO.getEstado());
        dto.setObservaciones(proformaDTO.getObservaciones());
        dto.setCreatedAt(proformaDTO.getCreatedAt());
        dto.setUpdatedAt(proformaDTO.getUpdatedAt());

        // Mapear detalles de la proforma
        ProformaDetalleDTO detalleDTO = proformaDetalleMapper.toDTO(proforma);
        dto.setDetalles(detalleDTO.getDetalles());

        // Mapear cobranzas
        List<Cobranza> cobranzas = cobranzaService.findByProforma(proforma);
        List<CobranzaDTO> cobranzasDTO = cobranzas.stream()
                .map(this::toCobranzaDTO)
                .collect(Collectors.toList());
        dto.setCobranzas(cobranzasDTO);

        return dto;
    }

    private CobranzaDTO toCobranzaDTO(Cobranza cobranza) {
        if (cobranza == null) {
            return null;
        }

        CobranzaDTO dto = new CobranzaDTO();
        dto.setId(cobranza.getId());
        dto.setProformaId(cobranza.getProforma().getId());
        dto.setFechaPago(cobranza.getFechaPago());
        dto.setMonto(cobranza.getMonto());
        dto.setMetodoPago(cobranza.getMetodoPago());
        dto.setEstado(cobranza.getEstado());
        dto.setObservaciones(cobranza.getObservaciones());

        return dto;
    }
}