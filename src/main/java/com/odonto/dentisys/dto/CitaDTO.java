package com.odonto.dentisys.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class CitaDTO {
    private Long id;
    private Long pacienteId;
    private String pacienteNombre;
    private Long medicoId;
    private String medicoNombre;
    private LocalDate fechaCita;
    private LocalTime horaCita;
    private LocalTime horaCitaFin;
    private String estado;
    private String observaciones;
    private String createdAt;
    private String updatedAt;
}