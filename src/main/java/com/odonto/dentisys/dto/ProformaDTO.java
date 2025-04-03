package com.odonto.dentisys.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProformaDTO {
    private Integer id;
    private Integer pacienteId;
    private String pacienteNombre;
    private Integer medicoId;
    private String medicoNombre;
    private LocalDate fechaEmision;
    private Double subtotal;
    private Double iva;
    private Double total;
    private String estado;
    private String observaciones;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}