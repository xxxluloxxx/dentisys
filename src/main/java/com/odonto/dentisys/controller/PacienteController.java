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

import jakarta.validation.Valid;

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
        Paciente paciente = pacienteService.findById(id);
        return ResponseEntity.ok(pacienteMapper.toDTO(paciente));
    }

    @GetMapping("/identificacion/{identificacion}")
    public ResponseEntity<PacienteDTO> findByIdentificacion(@PathVariable String identificacion) {
        Paciente paciente = pacienteService.findByIdentificacion(identificacion);
        return ResponseEntity.ok(pacienteMapper.toDTO(paciente));
    }

    @PostMapping
    public ResponseEntity<PacienteDTO> create(@Valid @RequestBody Paciente paciente) {
        Paciente savedPaciente = pacienteService.save(paciente);
        return ResponseEntity.ok(pacienteMapper.toDTO(savedPaciente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> update(@PathVariable Long id, @Valid @RequestBody Paciente paciente) {
        paciente.setId(id);
        Paciente updatedPaciente = pacienteService.save(paciente);
        return ResponseEntity.ok(pacienteMapper.toDTO(updatedPaciente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pacienteService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}