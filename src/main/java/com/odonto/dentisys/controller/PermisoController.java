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

import com.odonto.dentisys.dto.PermisoDTO;
import com.odonto.dentisys.service.PermisoService;

@RestController
@RequestMapping("/api/permisos")
public class PermisoController {

    @Autowired
    private PermisoService permisoService;

    @GetMapping
    public ResponseEntity<List<PermisoDTO>> findAll() {
        return ResponseEntity.ok(permisoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermisoDTO> findById(@PathVariable Long id) {
        return permisoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PermisoDTO> create(@RequestBody PermisoDTO permisoDTO) {
        return ResponseEntity.ok(permisoService.save(permisoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermisoDTO> update(@PathVariable Long id, @RequestBody PermisoDTO permisoDTO) {
        permisoDTO.setId(id);
        return ResponseEntity.ok(permisoService.save(permisoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        permisoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}