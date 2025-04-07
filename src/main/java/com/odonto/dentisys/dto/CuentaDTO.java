package com.odonto.dentisys.dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class CuentaDTO {
    private Long id;
    private Long categoriaId;
    private String categoriaNombre;
    private Long cobranzaId;
    private Double monto;
    private LocalDate fechaMovimiento;
    private String descripcion;
    private Boolean estado;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}