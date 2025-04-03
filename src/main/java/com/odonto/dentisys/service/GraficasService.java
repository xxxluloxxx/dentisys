package com.odonto.dentisys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.odonto.dentisys.dto.EstadisticasProformaDTO;
import com.odonto.dentisys.model.Proforma;
import com.odonto.dentisys.repository.ProformaRepository;

@Service
public class GraficasService {

    @Autowired
    private ProformaRepository proformaRepository;

    public EstadisticasProformaDTO getEstadisticasProformas() {
        List<Proforma> proformas = proformaRepository.findAll();
        EstadisticasProformaDTO estadisticas = new EstadisticasProformaDTO();

        for (Proforma proforma : proformas) {
            switch (proforma.getEstado()) {
                case "PENDIENTE":
                    estadisticas.setCantidadPendientes(estadisticas.getCantidadPendientes() + 1);
                    estadisticas.setTotalPendientes(estadisticas.getTotalPendientes() + proforma.getTotal());
                    break;
                case "CANCELADA":
                    estadisticas.setCantidadCanceladas(estadisticas.getCantidadCanceladas() + 1);
                    estadisticas.setTotalCanceladas(estadisticas.getTotalCanceladas() + proforma.getTotal());
                    break;
                case "PAGADA":
                    estadisticas.setCantidadPagadas(estadisticas.getCantidadPagadas() + 1);
                    estadisticas.setTotalPagadas(estadisticas.getTotalPagadas() + proforma.getTotal());
                    break;
            }
        }

        return estadisticas;
    }
}