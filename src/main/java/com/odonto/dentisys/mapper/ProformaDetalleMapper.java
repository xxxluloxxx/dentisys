package com.odonto.dentisys.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.odonto.dentisys.dto.DetalleProformaDTO;
import com.odonto.dentisys.dto.ProformaDetalleDTO;
import com.odonto.dentisys.model.DetalleProforma;
import com.odonto.dentisys.model.Proforma;

@Component
public class ProformaDetalleMapper {

    public ProformaDetalleDTO toDTO(Proforma proforma) {
        if (proforma == null) {
            return null;
        }

        ProformaDetalleDTO dto = new ProformaDetalleDTO();
        dto.setId(proforma.getId());
        dto.setPacienteId(proforma.getPaciente().getId());
        dto.setPacienteNombre(proforma.getPaciente().getNombre() + " " + proforma.getPaciente().getApellido());
        dto.setMedicoId(proforma.getMedico().getId());
        dto.setMedicoNombre(proforma.getMedico().getNombre() + " " + proforma.getMedico().getApellido());
        dto.setSubtotal(proforma.getSubtotal());
        dto.setIva(proforma.getIva());
        dto.setTotal(proforma.getTotal());
        dto.setEstado(proforma.getEstado());
        dto.setObservaciones(proforma.getObservaciones());
        dto.setCreatedAt(proforma.getCreatedAt());

        if (proforma.getDetalles() != null) {
            dto.setDetalles(proforma.getDetalles().stream()
                    .map(this::toDetalleDTO)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    private DetalleProformaDTO toDetalleDTO(DetalleProforma detalle) {
        if (detalle == null) {
            return null;
        }

        DetalleProformaDTO dto = new DetalleProformaDTO();
        dto.setId(detalle.getId());
        dto.setProductoId(detalle.getProducto().getId());
        dto.setProductoNombre(detalle.getProducto().getNombre());
        dto.setCantidad(detalle.getCantidad());
        dto.setPrecioUnitario(detalle.getPrecioUnitario());
        dto.setSubtotal(detalle.getSubtotal());

        return dto;
    }
}