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

import com.odonto.dentisys.model.Cobranza;
import com.odonto.dentisys.model.Paciente;
import com.odonto.dentisys.model.Proforma;
import com.odonto.dentisys.service.CobranzaService;

@RestController
@RequestMapping("/api/cobranzas")
public class CobranzaController {

    @Autowired
    private CobranzaService cobranzaService;

    @GetMapping
    public ResponseEntity<List<Cobranza>> getAllCobranzas() {
        return ResponseEntity.ok(cobranzaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cobranza> getCobranzaById(@PathVariable Integer id) {
        return ResponseEntity.ok(cobranzaService.findById(id));
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Cobranza>> getCobranzasByPaciente(@PathVariable Integer pacienteId) {
        Paciente paciente = new Paciente();
        paciente.setId(pacienteId);
        return ResponseEntity.ok(cobranzaService.findByPaciente(paciente));
    }

    @GetMapping("/proforma/{proformaId}")
    public ResponseEntity<List<Cobranza>> getCobranzasByProforma(@PathVariable Integer proformaId) {
        Proforma proforma = new Proforma();
        proforma.setId(proformaId);
        return ResponseEntity.ok(cobranzaService.findByProforma(proforma));
    }

    @GetMapping("/fecha")
    public ResponseEntity<List<Cobranza>> getCobranzasByFechaPago(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        return ResponseEntity.ok(cobranzaService.findByFechaPagoBetween(fechaInicio, fechaFin));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Cobranza>> getCobranzasByEstado(@PathVariable String estado) {
        return ResponseEntity.ok(cobranzaService.findByEstado(estado));
    }

    @GetMapping("/paciente/{pacienteId}/estado/{estado}")
    public ResponseEntity<List<Cobranza>> getCobranzasByPacienteAndEstado(
            @PathVariable Integer pacienteId,
            @PathVariable String estado) {
        Paciente paciente = new Paciente();
        paciente.setId(pacienteId);
        return ResponseEntity.ok(cobranzaService.findByPacienteAndEstado(paciente, estado));
    }

    @PostMapping
    public ResponseEntity<Cobranza> createCobranza(@RequestBody Cobranza cobranza) {
        return ResponseEntity.ok(cobranzaService.save(cobranza));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cobranza> updateCobranza(@PathVariable Integer id, @RequestBody Cobranza cobranza) {
        cobranza.setId(id);
        return ResponseEntity.ok(cobranzaService.save(cobranza));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCobranza(@PathVariable Integer id) {
        cobranzaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}