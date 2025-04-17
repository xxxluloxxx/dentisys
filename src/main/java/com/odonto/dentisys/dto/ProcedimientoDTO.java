package com.odonto.dentisys.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProcedimientoDTO {
    private Integer id;
    private Integer fichaId;
    private String procedimiento;
    private String observaciones;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}