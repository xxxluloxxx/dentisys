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

import com.odonto.dentisys.model.FichaOdontologica;
import com.odonto.dentisys.service.FichaOdontologicaService;

@RestController
@RequestMapping("/api/fichas-medicas")
public class FichaOdontologicaController {

    @Autowired
    private FichaOdontologicaService fichaOdontologicaService;

    @GetMapping
    public ResponseEntity<List<FichaOdontologica>> findAll() {
        return ResponseEntity.ok(fichaOdontologicaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FichaOdontologica> findById(@PathVariable Integer id) {
        return fichaOdontologicaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<Boolean> findByPacienteId(@PathVariable Integer pacienteId) {
        List<FichaOdontologica> fichas = fichaOdontologicaService.findByPacienteId(pacienteId);
        return ResponseEntity.ok(!fichas.isEmpty());
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<FichaOdontologica>> findByMedicoId(@PathVariable Integer medicoId) {
        return ResponseEntity.ok(fichaOdontologicaService.findByMedicoId(medicoId));
    }

    @PostMapping
    public ResponseEntity<FichaOdontologica> create(@RequestBody FichaOdontologica fichaOdontologica) {
        return ResponseEntity.ok(fichaOdontologicaService.save(fichaOdontologica));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FichaOdontologica> update(@PathVariable Integer id,
            @RequestBody FichaOdontologica fichaOdontologica) {
        return fichaOdontologicaService.findById(id)
                .map(existingFicha -> {
                    fichaOdontologica.setId(id);
                    fichaOdontologica.setCreatedAt(existingFicha.getCreatedAt());
                    return ResponseEntity.ok(fichaOdontologicaService.save(fichaOdontologica));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return fichaOdontologicaService.findById(id)
                .map(ficha -> {
                    fichaOdontologicaService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}