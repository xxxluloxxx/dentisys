package com.odonto.dentisys.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PacienteDTO {
    private Long id;
    private String nombreCompleto;
    private String nombre;
    private String apellido;
    private String identificacion;
    private LocalDate fechaNacimiento;
    private String genero;
    private String telefono;
    private String email;
    private String direccion;
}