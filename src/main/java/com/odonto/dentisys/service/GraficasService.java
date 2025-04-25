package com.odonto.dentisys.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.odonto.dentisys.dto.EstadisticasGeneralesDTO;
import com.odonto.dentisys.dto.EstadisticasProformaDTO;
import com.odonto.dentisys.dto.ProductoFrecuenteDTO;
import com.odonto.dentisys.model.Cita;
import com.odonto.dentisys.model.DetalleProforma;
import com.odonto.dentisys.model.FichaOdontologica;
import com.odonto.dentisys.model.Paciente;
import com.odonto.dentisys.model.Proforma;
import com.odonto.dentisys.repository.CitaRepository;
import com.odonto.dentisys.repository.DetalleProformaRepository;
import com.odonto.dentisys.repository.FichaOdontologicaRepository;
import com.odonto.dentisys.repository.PacienteRepository;
import com.odonto.dentisys.repository.ProformaRepository;

@Service
public class GraficasService {

    @Autowired
    private ProformaRepository proformaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private FichaOdontologicaRepository fichaOdontologicaRepository;

    @Autowired
    private DetalleProformaRepository detalleProformaRepository;

    public List<ProductoFrecuenteDTO> getProductosFrecuentes() {
        List<DetalleProforma> detalles = detalleProformaRepository.findAll();
        Map<String, Long> conteoProductos = new HashMap<>();
        long totalProductos = detalles.size();

        // Contar frecuencia de cada producto
        for (DetalleProforma detalle : detalles) {
            String nombreProducto = detalle.getProducto().getNombre();
            conteoProductos.put(nombreProducto, conteoProductos.getOrDefault(nombreProducto, 0L) + 1);
        }

        // Calcular porcentajes y crear DTOs
        List<ProductoFrecuenteDTO> productosFrecuentes = conteoProductos.entrySet().stream()
                .map(entry -> {
                    double porcentaje = (entry.getValue() * 100.0) / totalProductos;
                    return new ProductoFrecuenteDTO(
                            entry.getKey(),
                            Math.round(porcentaje) // Redondeamos a número entero
                    );
                })
                .sorted((a, b) -> Double.compare(b.getPorcentaje(), a.getPorcentaje()))
                .limit(7)
                .collect(Collectors.toList());

        return productosFrecuentes;
    }

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

    public EstadisticasGeneralesDTO getEstadisticasGenerales() {
        EstadisticasGeneralesDTO estadisticas = new EstadisticasGeneralesDTO();

        // Cantidad total de pacientes
        estadisticas.setTotalPacientes(pacienteRepository.count());

        // Pacientes nuevos del mes actual
        LocalDate hoy = LocalDate.now();
        LocalDateTime inicioMesActual = hoy.withDayOfMonth(1).atStartOfDay();
        LocalDateTime finMesActual = hoy.withDayOfMonth(hoy.lengthOfMonth()).atTime(23, 59, 59);

        List<Paciente> pacientesNuevosMesActual = pacienteRepository.findByCreatedAtBetween(inicioMesActual,
                finMesActual);
        estadisticas.setPacientesNuevosMesActual(pacientesNuevosMesActual.size());

        // Cantidad total de proformas
        estadisticas.setCantidadProformas(proformaRepository.count());

        // Cantidad de proformas del mes anterior
        LocalDate mesAnterior = hoy.minusMonths(1);
        LocalDateTime inicioMesAnterior = mesAnterior.withDayOfMonth(1).atStartOfDay();
        LocalDateTime finMesAnterior = mesAnterior.withDayOfMonth(mesAnterior.lengthOfMonth()).atTime(23, 59, 59);

        List<Proforma> proformasMesAnterior = proformaRepository.findByCreatedAtBetween(inicioMesAnterior,
                finMesAnterior);
        estadisticas.setCantidadProformasMesAnterior(proformasMesAnterior.size());

        // Fechas para las citas
        LocalDate ayer = hoy.minusDays(1);

        // Cantidad de citas de hoy
        List<Cita> citasHoy = citaRepository.findByFechaCita(hoy);
        estadisticas.setCantidadCitasHoy(citasHoy.size());

        // Cantidad de citas de ayer
        List<Cita> citasAyer = citaRepository.findByFechaCita(ayer);
        estadisticas.setCantidadCitasAyer(citasAyer.size());

        // Fechas para las fichas
        LocalDateTime inicioSemanaActual = hoy.atStartOfDay()
                .with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
        LocalDateTime inicioSemanaAnterior = inicioSemanaActual.minusWeeks(1);
        LocalDateTime finSemanaActual = inicioSemanaActual.plusDays(7);
        LocalDateTime finSemanaAnterior = inicioSemanaActual;

        // Cantidad de fichas de esta semana
        List<FichaOdontologica> fichasEstaSemana = fichaOdontologicaRepository
                .findByCreatedAtBetween(inicioSemanaActual, finSemanaActual);
        estadisticas.setCantidadFichasEstaSemana(fichasEstaSemana.size());

        // Cantidad de fichas de la semana anterior
        List<FichaOdontologica> fichasSemanaAnterior = fichaOdontologicaRepository
                .findByCreatedAtBetween(inicioSemanaAnterior, finSemanaAnterior);
        estadisticas.setCantidadFichasSemanaAnterior(fichasSemanaAnterior.size());

        return estadisticas;
    }
}