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

import com.odonto.dentisys.dto.ImagenFichaDTO;
import com.odonto.dentisys.model.ImagenFicha;
import com.odonto.dentisys.service.ImagenFichaService;

@RestController
@RequestMapping("/api/imagenes-fichas")
public class ImagenFichaController {

    @Autowired
    private ImagenFichaService imagenFichaService;

    @GetMapping
    public ResponseEntity<List<ImagenFichaDTO>> findAll() {
        return ResponseEntity.ok(imagenFichaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImagenFichaDTO> findById(@PathVariable Integer id) {
        return imagenFichaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/ficha/{fichaId}")
    public ResponseEntity<List<ImagenFichaDTO>> findByFichaId(@PathVariable Integer fichaId) {
        return ResponseEntity.ok(imagenFichaService.findByFichaId(fichaId));
    }

    @GetMapping("/tipo/{tipoImagen}")
    public ResponseEntity<List<ImagenFichaDTO>> findByTipoImagen(@PathVariable String tipoImagen) {
        return ResponseEntity.ok(imagenFichaService.findByTipoImagen(tipoImagen));
    }

    @PostMapping
    public ResponseEntity<ImagenFicha> create(@RequestBody ImagenFichaDTO imagenFichaDTO) {
        return ResponseEntity.ok(imagenFichaService.save(imagenFichaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImagenFicha> update(@PathVariable Integer id, @RequestBody ImagenFichaDTO imagenFichaDTO) {
        imagenFichaDTO.setId(id);
        return ResponseEntity.ok(imagenFichaService.save(imagenFichaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        imagenFichaService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}