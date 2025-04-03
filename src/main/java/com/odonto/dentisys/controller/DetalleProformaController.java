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

import com.odonto.dentisys.model.DetalleProforma;
import com.odonto.dentisys.model.Proforma;
import com.odonto.dentisys.service.DetalleProformaService;

@RestController
@RequestMapping("/api/detalles-proforma")
public class DetalleProformaController {

    @Autowired
    private DetalleProformaService detalleProformaService;

    @GetMapping
    public ResponseEntity<List<DetalleProforma>> getAllDetallesProforma() {
        return ResponseEntity.ok(detalleProformaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleProforma> getDetalleProformaById(@PathVariable Integer id) {
        return ResponseEntity.ok(detalleProformaService.findById(id));
    }

    @GetMapping("/proforma/{proformaId}")
    public ResponseEntity<List<DetalleProforma>> getDetallesByProforma(@PathVariable Integer proformaId) {
        Proforma proforma = new Proforma();
        proforma.setId(proformaId);
        return ResponseEntity.ok(detalleProformaService.findByProforma(proforma));
    }

    @PostMapping
    public ResponseEntity<DetalleProforma> createDetalleProforma(@RequestBody DetalleProforma detalleProforma) {
        return ResponseEntity.ok(detalleProformaService.save(detalleProforma));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleProforma> updateDetalleProforma(@PathVariable Integer id,
            @RequestBody DetalleProforma detalleProforma) {
        detalleProforma.setId(id);
        return ResponseEntity.ok(detalleProformaService.save(detalleProforma));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalleProforma(@PathVariable Integer id) {
        detalleProformaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}