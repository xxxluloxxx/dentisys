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

import com.odonto.dentisys.dto.CuentaDTO;
import com.odonto.dentisys.service.CuentaService;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public ResponseEntity<List<CuentaDTO>> findAll() {
        return ResponseEntity.ok(cuentaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaDTO> findById(@PathVariable Long id) {
        return cuentaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<CuentaDTO>> findByCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(cuentaService.findByCategoria(categoriaId));
    }

    @GetMapping("/cobranza/{cobranzaId}")
    public ResponseEntity<List<CuentaDTO>> findByCobranza(@PathVariable Long cobranzaId) {
        return ResponseEntity.ok(cuentaService.findByCobranza(cobranzaId));
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<CuentaDTO>> findByMedico(@PathVariable Long medicoId) {
        return ResponseEntity.ok(cuentaService.findByMedico(medicoId));
    }

    @GetMapping("/fecha")
    public ResponseEntity<List<CuentaDTO>> findByFechaMovimientoBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        return ResponseEntity.ok(cuentaService.findByFechaMovimientoBetween(fechaInicio, fechaFin));
    }

    @PostMapping
    public ResponseEntity<CuentaDTO> create(@RequestBody CuentaDTO cuentaDTO) {
        return ResponseEntity.ok(cuentaService.save(cuentaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaDTO> update(@PathVariable Long id, @RequestBody CuentaDTO cuentaDTO) {
        return cuentaService.findById(id)
                .map(existingCuenta -> {
                    cuentaDTO.setId(id);
                    return ResponseEntity.ok(cuentaService.save(cuentaDTO));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return cuentaService.findById(id)
                .map(cuenta -> {
                    cuentaService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}