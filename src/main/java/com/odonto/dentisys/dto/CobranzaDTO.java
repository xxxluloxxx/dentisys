package com.odonto.dentisys.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CobranzaDTO {
    private Long id;
    private Long proformaId;
    private Long bancoId;
    private LocalDate fechaPago;
    private Double monto;
    private String metodoPago;
    private String estado;
    private String observaciones;
    private LocalDateTime createdAt;
}