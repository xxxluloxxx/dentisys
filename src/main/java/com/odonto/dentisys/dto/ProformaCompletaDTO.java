package com.odonto.dentisys.dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import lombok.Data;

@Data
public class ProformaCompletaDTO {
    private Long id;
    private Long pacienteId;
    private String pacienteNombre;
    private Long medicoId;
    private String medicoNombre;
    private LocalDate fechaEmision;
    private Double subtotal;
    private Double iva;
    private Double total;
    private String estado;
    private String observaciones;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private List<DetalleProformaDTO> detalles;
    private List<CobranzaDTO> cobranzas;
}