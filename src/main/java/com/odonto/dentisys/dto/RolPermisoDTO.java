package com.odonto.dentisys.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RolPermisoDTO {
    private Long id;
    private Long rolId;
    private Long permisoId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}