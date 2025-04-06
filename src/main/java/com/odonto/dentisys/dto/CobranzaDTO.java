package com.odonto.dentisys.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CobranzaDTO {
    private Long id;
    private Long proformaId;
    private LocalDate fechaPago;
    private Double monto;
    private String metodoPago;
    private String estado;
    private String observaciones;
}