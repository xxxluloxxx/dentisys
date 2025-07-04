package com.odonto.dentisys.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BancoDTO {
    private Long id;
    private String nombreCuenta;
    private String banco;
    private String cuenta;
    private String color;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 