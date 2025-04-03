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

import com.odonto.dentisys.dto.RolPermisoDTO;
import com.odonto.dentisys.service.RolPermisoService;

@RestController
@RequestMapping("/api/roles-permisos")
public class RolPermisoController {

    @Autowired
    private RolPermisoService rolPermisoService;

    @GetMapping
    public ResponseEntity<List<RolPermisoDTO>> findAll() {
        return ResponseEntity.ok(rolPermisoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolPermisoDTO> findById(@PathVariable Long id) {
        return rolPermisoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RolPermisoDTO> create(@RequestBody RolPermisoDTO rolPermisoDTO) {
        return ResponseEntity.ok(rolPermisoService.save(rolPermisoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolPermisoDTO> update(@PathVariable Long id, @RequestBody RolPermisoDTO rolPermisoDTO) {
        rolPermisoDTO.setId(id);
        return ResponseEntity.ok(rolPermisoService.save(rolPermisoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        rolPermisoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/rol/{rolId}")
    public ResponseEntity<List<RolPermisoDTO>> findByRolId(@PathVariable Long rolId) {
        return ResponseEntity.ok(rolPermisoService.findByRolId(rolId));
    }
}