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

import com.odonto.dentisys.dto.ProcedimientoDTO;
import com.odonto.dentisys.mapper.ProcedimientoMapper;
import com.odonto.dentisys.model.Procedimiento;
import com.odonto.dentisys.service.ProcedimientoService;

@RestController
@RequestMapping("/api/procedimientos")
public class ProcedimientoController {

    @Autowired
    private ProcedimientoService procedimientoService;

    @Autowired
    private ProcedimientoMapper procedimientoMapper;

    @GetMapping
    public ResponseEntity<List<ProcedimientoDTO>> findAll() {
        return ResponseEntity.ok(procedimientoMapper.toDTOList(procedimientoService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcedimientoDTO> findById(@PathVariable Integer id) {
        return procedimientoService.findById(id)
                .map(procedimiento -> ResponseEntity.ok(procedimientoMapper.toDTO(procedimiento)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/ficha/{fichaId}")
    public ResponseEntity<List<ProcedimientoDTO>> findByFichaId(@PathVariable Integer fichaId) {
        return ResponseEntity.ok(procedimientoMapper.toDTOList(procedimientoService.findByFichaId(fichaId)));
    }

    @PostMapping
    public ResponseEntity<ProcedimientoDTO> create(@RequestBody Procedimiento procedimiento) {
        return ResponseEntity.ok(procedimientoMapper.toDTO(procedimientoService.save(procedimiento)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProcedimientoDTO> update(@PathVariable Integer id,
            @RequestBody Procedimiento procedimiento) {
        return procedimientoService.findById(id)
                .map(existingProcedimiento -> {
                    procedimiento.setId(id);
                    return ResponseEntity.ok(procedimientoMapper.toDTO(procedimientoService.save(procedimiento)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return procedimientoService.findById(id)
                .map(procedimiento -> {
                    procedimientoService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}