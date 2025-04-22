package com.odonto.dentisys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odonto.dentisys.dto.PacienteDTO;
import com.odonto.dentisys.mapper.PacienteMapper;
import com.odonto.dentisys.model.Paciente;
import com.odonto.dentisys.service.PacienteService;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private PacienteMapper pacienteMapper;

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> findAll() {
        return ResponseEntity.ok(pacienteMapper.toDTOList(pacienteService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> findById(@PathVariable Long id) {
        return pacienteService.findById(id)
                .map(paciente -> ResponseEntity.ok(pacienteMapper.toDTO(paciente)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/identificacion/{identificacion}")
    public ResponseEntity<PacienteDTO> findByIdentificacion(@PathVariable String identificacion) {
        return pacienteService.findByIdentificacion(identificacion)
                .map(paciente -> ResponseEntity.ok(pacienteMapper.toDTO(paciente)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PacienteDTO> create(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteMapper.toDTO(pacienteService.save(paciente)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> update(@PathVariable Long id, @RequestBody Paciente paciente) {
        return pacienteService.findById(id)
                .map(existingPaciente -> {
                    paciente.setId(id);
                    return ResponseEntity.ok(pacienteMapper.toDTO(pacienteService.save(paciente)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return pacienteService.findById(id)
                .map(paciente -> {
                    pacienteService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}