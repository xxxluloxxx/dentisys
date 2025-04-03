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

import com.odonto.dentisys.model.General;
import com.odonto.dentisys.service.GeneralService;

@RestController
@RequestMapping("/api/general")
public class GeneralController {

    @Autowired
    private GeneralService generalService;

    @GetMapping
    public ResponseEntity<List<General>> findAll() {
        return ResponseEntity.ok(generalService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<General> findById(@PathVariable Integer id) {
        return generalService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/parametro/{parametro}")
    public ResponseEntity<General> findByParametro(@PathVariable String parametro) {
        return generalService.findByParametro(parametro)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<General> create(@RequestBody General general) {
        return ResponseEntity.ok(generalService.save(general));
    }

    @PutMapping("/{id}")
    public ResponseEntity<General> update(@PathVariable Integer id, @RequestBody General general) {
        return generalService.findById(id)
                .map(existingGeneral -> {
                    general.setId(id);
                    return ResponseEntity.ok(generalService.save(general));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return generalService.findById(id)
                .map(general -> {
                    generalService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}