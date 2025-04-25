package com.odonto.dentisys.dto;

import lombok.Data;

@Data
public class EstadisticasCitasSemanaDTO {
    private EstadisticasDiaSemanaDTO semanaActual;
    private EstadisticasDiaSemanaDTO semanaAnterior;
}