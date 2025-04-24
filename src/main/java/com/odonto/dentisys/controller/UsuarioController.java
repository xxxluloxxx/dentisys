package com.odonto.dentisys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.odonto.dentisys.dto.UsuarioDTO;
import com.odonto.dentisys.mapper.UsuarioMapper;
import com.odonto.dentisys.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
        return usuarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.save(usuarioDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        usuarioDTO.setId(id);
        return ResponseEntity.ok(usuarioService.save(usuarioDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioDTO> findByEmail(@PathVariable String email) {
        return usuarioService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/documento/{numeroDocumento}")
    public ResponseEntity<UsuarioDTO> findByNumeroDocumento(@PathVariable String numeroDocumento) {
        return usuarioService.findByNumeroDocumento(numeroDocumento)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/auth")
    public ResponseEntity<UsuarioDTO> authenticate(@RequestParam String email, @RequestParam String password) {
        return usuarioService.findByEmailAndPassword(email, password)
                .map(usuario -> ResponseEntity.ok(usuarioMapper.toDTO(usuario)))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}