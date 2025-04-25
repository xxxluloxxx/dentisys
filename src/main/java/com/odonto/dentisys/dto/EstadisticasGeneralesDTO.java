package com.odonto.dentisys.dto;

import lombok.Data;

@Data
public class EstadisticasGeneralesDTO {
    private long totalPacientes;
    private long pacientesNuevosMesActual;
    private long cantidadProformas;
    private long cantidadProformasMesAnterior;
    private long cantidadCitasHoy;
    private long cantidadCitasAyer;
    private long cantidadFichasEstaSemana;
    private long cantidadFichasSemanaAnterior;
}