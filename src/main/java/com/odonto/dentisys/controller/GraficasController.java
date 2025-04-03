package com.odonto.dentisys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odonto.dentisys.dto.EstadisticasProformaDTO;
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
}