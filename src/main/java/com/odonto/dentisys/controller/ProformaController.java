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

import com.odonto.dentisys.dto.ProformaCompletaDTO;
import com.odonto.dentisys.dto.ProformaDTO;
import com.odonto.dentisys.dto.ProformaDetalleDTO;
import com.odonto.dentisys.mapper.ProformaCompletaMapper;
import com.odonto.dentisys.mapper.ProformaDetalleMapper;
import com.odonto.dentisys.mapper.ProformaMapper;
import com.odonto.dentisys.model.Medico;
import com.odonto.dentisys.model.Paciente;
import com.odonto.dentisys.model.Proforma;
import com.odonto.dentisys.service.ProformaService;

@RestController
@RequestMapping("/api/proformas")
public class ProformaController {

    @Autowired
    private ProformaService proformaService;

    @Autowired
    private ProformaMapper proformaMapper;

    @Autowired
    private ProformaDetalleMapper proformaDetalleMapper;

    @Autowired
    private ProformaCompletaMapper proformaCompletaMapper;

    @GetMapping
    public ResponseEntity<List<ProformaDTO>> getAllProformas() {
        return ResponseEntity.ok(proformaMapper.toDTOList(proformaService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProformaCompletaDTO> getProformaById(@PathVariable Long id) {
        return ResponseEntity.ok(proformaCompletaMapper.toDTO(proformaService.findById(id)));
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<ProformaDTO>> getProformasByPaciente(@PathVariable Long pacienteId) {
        Paciente paciente = new Paciente();
        paciente.setId(pacienteId);
        return ResponseEntity.ok(proformaMapper.toDTOList(proformaService.findByPaciente(paciente)));
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<ProformaDTO>> getProformasByMedico(@PathVariable Long medicoId) {
        Medico medico = new Medico();
        medico.setId(medicoId);
        return ResponseEntity.ok(proformaMapper.toDTOList(proformaService.findByMedico(medico)));
    }

    @GetMapping("/fecha")
    public ResponseEntity<List<ProformaDTO>> getProformasByFechaEmision(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        return ResponseEntity
                .ok(proformaMapper.toDTOList(proformaService.findByFechaEmisionBetween(fechaInicio, fechaFin)));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ProformaDTO>> getProformasByEstado(@PathVariable String estado) {
        return ResponseEntity.ok(proformaMapper.toDTOList(proformaService.findByEstado(estado)));
    }

    @PostMapping
    public ResponseEntity<ProformaDTO> createProforma(@RequestBody Proforma proforma) {
        return ResponseEntity.ok(proformaMapper.toDTO(proformaService.save(proforma)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProformaDTO> updateProforma(@PathVariable Long id, @RequestBody Proforma proforma) {
        proforma.setId(id);
        return ResponseEntity.ok(proformaMapper.toDTO(proformaService.save(proforma)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProforma(@PathVariable Long id) {
        proformaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/detalles")
    public ResponseEntity<ProformaDetalleDTO> getProformaConDetalles(@PathVariable Long id) {
        Proforma proforma = proformaService.findById(id);
        return ResponseEntity.ok(proformaDetalleMapper.toDTO(proforma));
    }

    @GetMapping("/detalles")
    public ResponseEntity<List<ProformaDetalleDTO>> getAllProformasConDetalles() {
        List<Proforma> proformas = proformaService.findAll();
        List<ProformaDetalleDTO> proformasDTO = proformas.stream()
                .map(proformaDetalleMapper::toDTO)
                .collect(java.util.stream.Collectors.toList());
        return ResponseEntity.ok(proformasDTO);
    }
}