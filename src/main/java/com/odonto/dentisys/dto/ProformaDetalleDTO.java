package com.odonto.dentisys.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class ProformaDetalleDTO {
    private Integer id;
    private Integer pacienteId;
    private String pacienteNombre;
    private Integer medicoId;
    private String medicoNombre;
    private Double subtotal;
    private Double iva;
    private Double total;
    private String estado;
    private String observaciones;
    private LocalDateTime createdAt;
    private List<DetalleProformaDTO> detalles;
}