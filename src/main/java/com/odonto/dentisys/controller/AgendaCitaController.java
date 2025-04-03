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

import com.odonto.dentisys.model.AgendaCita;
import com.odonto.dentisys.service.AgendaCitaService;

@RestController
@RequestMapping("/api/agenda-citas")
public class AgendaCitaController {

    @Autowired
    private AgendaCitaService agendaCitaService;

    @GetMapping
    public ResponseEntity<List<AgendaCita>> findAll() {
        return ResponseEntity.ok(agendaCitaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaCita> findById(@PathVariable Integer id) {
        return agendaCitaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/medico/{medicoId}/fecha/{fecha}")
    public ResponseEntity<List<AgendaCita>> findByMedicoIdAndFecha(
            @PathVariable Integer medicoId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(agendaCitaService.findByMedicoIdAndFecha(medicoId, fecha));
    }

    @GetMapping("/medico/{medicoId}/fecha")
    public ResponseEntity<List<AgendaCita>> findByMedicoIdAndFechaBetween(
            @PathVariable Integer medicoId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        return ResponseEntity.ok(agendaCitaService.findByMedicoIdAndFechaBetween(medicoId, fechaInicio, fechaFin));
    }

    @PostMapping
    public ResponseEntity<AgendaCita> create(@RequestBody AgendaCita agendaCita) {
        return ResponseEntity.ok(agendaCitaService.save(agendaCita));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendaCita> update(@PathVariable Integer id, @RequestBody AgendaCita agendaCita) {
        return agendaCitaService.findById(id)
                .map(existingAgenda -> {
                    agendaCita.setId(id);
                    return ResponseEntity.ok(agendaCitaService.save(agendaCita));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return agendaCitaService.findById(id)
                .map(agenda -> {
                    agendaCitaService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}