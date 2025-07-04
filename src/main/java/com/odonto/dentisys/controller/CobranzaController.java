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

import com.odonto.dentisys.dto.CobranzaDTO;
import com.odonto.dentisys.dto.CuentaDTO;
import com.odonto.dentisys.mapper.CobranzaMapper;
import com.odonto.dentisys.model.Banco;
import com.odonto.dentisys.model.Cobranza;
import com.odonto.dentisys.model.Paciente;
import com.odonto.dentisys.model.Proforma;
import com.odonto.dentisys.service.BancoService;
import com.odonto.dentisys.service.CategoriaService;
import com.odonto.dentisys.service.CobranzaService;
import com.odonto.dentisys.service.CuentaService;

@RestController
@RequestMapping("/api/cobranzas")
public class CobranzaController {

    @Autowired
    private CobranzaService cobranzaService;

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private BancoService bancoService;

    @Autowired
    private CobranzaMapper cobranzaMapper;

    @GetMapping
    public ResponseEntity<List<Cobranza>> getAllCobranzas() {
        return ResponseEntity.ok(cobranzaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cobranza> getCobranzaById(@PathVariable Long id) {
        return ResponseEntity.ok(cobranzaService.findById(id));
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Cobranza>> getCobranzasByPaciente(@PathVariable Long pacienteId) {
        Paciente paciente = new Paciente();
        paciente.setId(pacienteId);
        return ResponseEntity.ok(cobranzaService.findByPaciente(paciente));
    }

    @GetMapping("/proforma/{proformaId}")
    public ResponseEntity<List<CobranzaDTO>> getCobranzasByProforma(@PathVariable Long proformaId) {
        Proforma proforma = new Proforma();
        proforma.setId(proformaId);
        List<Cobranza> cobranzas = cobranzaService.findByProforma(proforma);
        List<CobranzaDTO> cobranzasDTO = cobranzaMapper.toDTOList(cobranzas);
        return ResponseEntity.ok(cobranzasDTO);
    }

    @GetMapping("/banco/{bancoId}")
    public ResponseEntity<List<Cobranza>> getCobranzasByBanco(@PathVariable Long bancoId) {
        Banco banco = new Banco();
        banco.setId(bancoId);
        return ResponseEntity.ok(cobranzaService.findByBanco(banco));
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
            @PathVariable Long pacienteId,
            @PathVariable String estado) {
        Paciente paciente = new Paciente();
        paciente.setId(pacienteId);
        return ResponseEntity.ok(cobranzaService.findByPacienteAndEstado(paciente, estado));
    }

    @PostMapping
    public ResponseEntity<Cobranza> createCobranza(@RequestBody Cobranza cobranza) {
        // Guardar la cobranza
        Cobranza cobranzaGuardada = cobranzaService.save(cobranza);

        // Crear un registro en la cuenta
        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setCategoriaId(1L); // ID de la categoría "Ingresos"
        cuentaDTO.setCobranzaId(cobranzaGuardada.getId());
        cuentaDTO.setMonto(cobranzaGuardada.getMonto());
        cuentaDTO.setFechaMovimiento(cobranzaGuardada.getFechaPago());
        cuentaDTO.setDescripcion("{Pago de Proforma} " + cobranzaGuardada.getObservaciones());

        // Guardar la cuenta
        cuentaService.save(cuentaDTO);

        return ResponseEntity.ok(cobranzaGuardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cobranza> updateCobranza(@PathVariable Long id, @RequestBody Cobranza cobranza) {
        return ResponseEntity.ok(cobranzaService.update(id, cobranza));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCobranza(@PathVariable Long id) {
        cobranzaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}