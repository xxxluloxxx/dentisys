package com.odonto.dentisys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odonto.dentisys.dto.EstadisticasCitasSemanaDTO;
import com.odonto.dentisys.dto.EstadisticasGeneralesDTO;
import com.odonto.dentisys.dto.EstadisticasProformaDTO;
import com.odonto.dentisys.dto.ProductoFrecuenteDTO;
import com.odonto.dentisys.service.GraficasService;

@RestController
@RequestMapping("/api/graficas")
public class GraficasController {

    @Autowired
    private GraficasService graficasService;

    @GetMapping("/estadisticas-proformas")
    public ResponseEntity<EstadisticasProformaDTO> getEstadisticasProformas() {
        return ResponseEntity.ok(graficasService.getEstadisticasProformas());
    }

    @GetMapping("/estadisticas-generales")
    public ResponseEntity<EstadisticasGeneralesDTO> getEstadisticasGenerales() {
        return ResponseEntity.ok(graficasService.getEstadisticasGenerales());
    }

    @GetMapping("/productos-frecuentes")
    public ResponseEntity<List<ProductoFrecuenteDTO>> getProductosFrecuentes() {
        return ResponseEntity.ok(graficasService.getProductosFrecuentes());
    }

    @GetMapping("/estadisticas-citas-semana")
    public ResponseEntity<EstadisticasCitasSemanaDTO> getEstadisticasCitasSemana() {
        return ResponseEntity.ok(graficasService.getEstadisticasCitasSemana());
    }
}