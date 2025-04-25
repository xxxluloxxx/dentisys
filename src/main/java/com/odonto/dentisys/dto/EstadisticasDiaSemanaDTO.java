package com.odonto.dentisys.dto;

import lombok.Data;

@Data
public class EstadisticasDiaSemanaDTO {
    private int lunes;
    private int martes;
    private int miercoles;
    private int jueves;
    private int viernes;
    private int sabado;
    private int domingo;
}