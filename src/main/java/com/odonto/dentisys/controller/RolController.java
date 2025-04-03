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

import com.odonto.dentisys.dto.RolDTO;
import com.odonto.dentisys.service.RolService;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping
    public ResponseEntity<List<RolDTO>> findAll() {
        return ResponseEntity.ok(rolService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolDTO> findById(@PathVariable Long id) {
        return rolService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RolDTO> create(@RequestBody RolDTO rolDTO) {
        return ResponseEntity.ok(rolService.save(rolDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolDTO> update(@PathVariable Long id, @RequestBody RolDTO rolDTO) {
        rolDTO.setId(id);
        return ResponseEntity.ok(rolService.save(rolDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        rolService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}