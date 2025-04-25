package com.odonto.dentisys.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoFrecuenteDTO {
    private String producto;
    private double porcentaje;
}