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

import com.odonto.dentisys.dto.MedicoCreateDTO;
import com.odonto.dentisys.dto.MedicoDTO;
import com.odonto.dentisys.mapper.MedicoMapper;
import com.odonto.dentisys.model.Medico;
import com.odonto.dentisys.model.Usuario;
import com.odonto.dentisys.service.MedicoService;
import com.odonto.dentisys.service.UsuarioService;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MedicoMapper medicoMapper;

    @GetMapping
    public ResponseEntity<List<MedicoDTO>> findAll() {
        return ResponseEntity.ok(medicoMapper.toDTOList(medicoService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> findById(@PathVariable Long id) {
        return medicoService.findById(id)
                .map(medico -> ResponseEntity.ok(medicoMapper.toDTO(medico)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/documento/{numeroDocumento}")
    public ResponseEntity<MedicoDTO> findByNumeroDocumento(@PathVariable String numeroDocumento) {
        return medicoService.findByNumeroDocumento(numeroDocumento)
                .map(medico -> ResponseEntity.ok(medicoMapper.toDTO(medico)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MedicoDTO> create(@RequestBody MedicoCreateDTO medicoCreateDTO) {
        // Buscar el usuario por ID
        Usuario usuario = usuarioService.findEntityById(medicoCreateDTO.getUsuarioId());

        // Crear el médico
        Medico medico = new Medico();
        medico.setUsuario(usuario);
        medico.setEspecialidad(medicoCreateDTO.getEspecialidad());

        return ResponseEntity.ok(medicoMapper.toDTO(medicoService.save(medico)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoDTO> update(@PathVariable Long id, @RequestBody Medico medico) {
        return medicoService.findById(id)
                .map(existingMedico -> {
                    existingMedico.setEspecialidad(medico.getEspecialidad());
                    return ResponseEntity.ok(medicoMapper.toDTO(medicoService.save(existingMedico)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return medicoService.findById(id)
                .map(medico -> {
                    medicoService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}