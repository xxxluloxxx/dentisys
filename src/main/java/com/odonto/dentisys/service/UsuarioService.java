package com.odonto.dentisys.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odonto.dentisys.dto.UsuarioDTO;
import com.odonto.dentisys.mapper.UsuarioMapper;
import com.odonto.dentisys.model.Usuario;
import com.odonto.dentisys.repository.RolRepository;
import com.odonto.dentisys.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Transactional(readOnly = true)
    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findById(Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> findEntityById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Transactional
    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);

        // Si es una actualización (tiene ID), verificar si la contraseña está vacía
        if (usuario.getId() != null) {
            Usuario usuarioExistente = usuarioRepository.findById(usuario.getId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            // Si la contraseña está vacía en el DTO, mantener la contraseña existente
            if (usuarioDTO.getPassword() == null || usuarioDTO.getPassword().trim().isEmpty()) {
                usuario.setPassword(usuarioExistente.getPassword());
            }
        }

        // Si es una actualización y no se especifica rol, mantener el rol existente
        if (usuario.getId() != null && usuarioDTO.getRolId() == null) {
            Usuario usuarioExistente = usuarioRepository.findById(usuario.getId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            usuario.setRol(usuarioExistente.getRol());
        }

        // Cargar el rol si existe
        if (usuarioDTO.getRolId() != null) {
            usuario.setRol(rolRepository.findById(usuarioDTO.getRolId())
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado")));
        }

        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuario);
    }

    @Transactional
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .map(usuarioMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findByNumeroDocumento(String numeroDocumento) {
        return usuarioRepository.findByNumeroDocumento(numeroDocumento)
                .map(usuarioMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public boolean existsByNumeroDocumento(String numeroDocumento) {
        return usuarioRepository.existsByNumeroDocumento(numeroDocumento);
    }

    @Transactional
    public Optional<Usuario> findByEmailAndPassword(String email, String password) {
        return usuarioRepository.findByEmailAndPassword(email, password)
                .map(usuario -> {
                    usuario.setUltimoAcceso(LocalDateTime.now());
                    return usuarioRepository.save(usuario);
                });
    }
}