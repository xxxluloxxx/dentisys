package com.odonto.dentisys.dto;

import lombok.Data;

@Data
public class DetalleProformaDTO {
    private Integer id;
    private Integer productoId;
    private String productoNombre;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;
}