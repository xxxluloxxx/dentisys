package com.odonto.dentisys.dto;

import lombok.Data;

@Data
public class MedicoDTO {
    private Long id;
    private Long usuarioId;
    private String nombreCompleto;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String numeroDocumento;
    private String email;
    private String telefono;
}