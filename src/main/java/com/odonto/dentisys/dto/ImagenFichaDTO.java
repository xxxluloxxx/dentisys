package com.odonto.dentisys.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ImagenFichaDTO {
    private Integer id;
    private Integer fichaId;
    private String nombre;
    private String imagenBase64; // La imagen se enviará como base64
    private String tipoImagen;
    private String descripcion;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}