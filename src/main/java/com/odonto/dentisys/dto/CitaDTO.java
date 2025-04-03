package com.odonto.dentisys.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class CitaDTO {
    private Integer id;
    private Integer pacienteId;
    private String pacienteNombre;
    private Integer medicoId;
    private String medicoNombre;
    private LocalDate fechaCita;
    private LocalTime horaCita;
    private String estado;
    private String observaciones;
    private String createdAt;
    private String updatedAt;
}