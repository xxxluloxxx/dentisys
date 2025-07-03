package com.odonto.dentisys.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class ProformaCobranzaDTO {
    private Long id;
    private Long pacienteId;
    private String pacienteNombre;
    private Long medicoId;
    private String medicoNombre;
    private Double subtotal;
    private Double iva;
    private Double descuento;
    private Double total;
    private String estado;
    private String observaciones;
    private LocalDateTime createdAt;
    private List<CobranzaDTO> cobranzas;

    @Data
    public static class CobranzaDTO {
        private Long id;
        private java.time.LocalDate fechaPago;
        private Double monto;
        private String metodoPago;
        private String estado;
        private String observaciones;
        private LocalDateTime createdAt;
    }
}