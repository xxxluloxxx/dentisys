package com.odonto.dentisys.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String numeroDocumento;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String telefono;
    private Boolean estado;
    private LocalDateTime ultimoAcceso;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long rolId;
}