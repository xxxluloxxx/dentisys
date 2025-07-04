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

import com.odonto.dentisys.dto.BancoDTO;
import com.odonto.dentisys.service.BancoService;

@RestController
@RequestMapping("/api/bancos")
public class BancoController {

    @Autowired
    private BancoService bancoService;

    @GetMapping
    public ResponseEntity<List<BancoDTO>> findAll() {
        return ResponseEntity.ok(bancoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BancoDTO> findById(@PathVariable Long id) {
        return bancoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BancoDTO> create(@RequestBody BancoDTO bancoDTO) {
        return ResponseEntity.ok(bancoService.save(bancoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BancoDTO> update(@PathVariable Long id, @RequestBody BancoDTO bancoDTO) {
        bancoDTO.setId(id);
        return ResponseEntity.ok(bancoService.save(bancoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bancoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}