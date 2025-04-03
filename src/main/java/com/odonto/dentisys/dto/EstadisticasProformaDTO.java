package com.odonto.dentisys.dto;

import lombok.Data;

@Data
public class EstadisticasProformaDTO {
    private long cantidadPendientes;
    private long cantidadCanceladas;
    private long cantidadPagadas;
    private double totalPendientes;
    private double totalCanceladas;
    private double totalPagadas;
}