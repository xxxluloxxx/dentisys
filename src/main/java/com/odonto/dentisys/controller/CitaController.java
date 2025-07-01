package com.odonto.dentisys.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.odonto.dentisys.dto.CitaDTO;
import com.odonto.dentisys.model.Cita;
import com.odonto.dentisys.service.CitaService;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping
    public ResponseEntity<List<CitaDTO>> findAll() {
        return ResponseEntity.ok(citaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaDTO> findById(@PathVariable Long id) {
        return citaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<CitaDTO>> findByPacienteId(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(citaService.findByPacienteId(pacienteId));
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<CitaDTO>> findByMedicoId(@PathVariable Long medicoId) {
        return ResponseEntity.ok(citaService.findByMedicoId(medicoId));
    }

    @GetMapping("/paciente/{pacienteId}/fecha")
    public ResponseEntity<List<CitaDTO>> findByPacienteIdAndFechaCitaBetween(
            @PathVariable Long pacienteId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        return ResponseEntity.ok(citaService.findByPacienteIdAndFechaCitaBetween(pacienteId, fechaInicio, fechaFin));
    }

    @GetMapping("/medico/{medicoId}/fecha")
    public ResponseEntity<List<CitaDTO>> findByMedicoIdAndFechaCitaBetween(
            @PathVariable Long medicoId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        return ResponseEntity.ok(citaService.findByMedicoIdAndFechaCitaBetween(medicoId, fechaInicio, fechaFin));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<CitaDTO>> findByEstado(@PathVariable String estado) {
        return ResponseEntity.ok(citaService.findByEstado(estado));
    }

    @PostMapping
    public ResponseEntity<Cita> create(@RequestBody Cita cita) {
        System.out.println("Datos recibidos en create: " + cita);
        System.out.println("Hora recibida: " + cita.getHoraCita());
        System.out.println("Hora fin recibida: " + cita.getHoraCitaFin());
        return ResponseEntity.ok(citaService.save(cita));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> update(@PathVariable Long id, @RequestBody Cita cita) {
        System.out.println("Datos recibidos en update: " + cita);
        System.out.println("Hora recibida: " + cita.getHoraCita());
        System.out.println("Hora fin recibida: " + cita.getHoraCitaFin());
        return citaService.findCitaById(id)
                .map(existingCita -> {
                    cita.setId(id);
                    return ResponseEntity.ok(citaService.save(cita));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return citaService.findById(id)
                .map(cita -> {
                    citaService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}