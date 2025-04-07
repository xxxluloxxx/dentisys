package com.odonto.dentisys.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class ProformaDetalleDTO {
    private Long id;
    private Long pacienteId;
    private String pacienteNombre;
    private Long medicoId;
    private String medicoNombre;
    private BigDecimal subtotal;
    private BigDecimal iva;
    private BigDecimal descuento;
    private BigDecimal total;
    private String estado;
    private String observaciones;
    private LocalDateTime createdAt;
    private List<DetalleProformaDTO> detalles;
}