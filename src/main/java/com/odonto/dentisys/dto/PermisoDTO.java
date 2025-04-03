package com.odonto.dentisys.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PermisoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Boolean estado;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}