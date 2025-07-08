package com.odonto.dentisys.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odonto.dentisys.dto.UsuarioDTO;
import com.odonto.dentisys.exception.DuplicadoException;
import com.odonto.dentisys.exception.RecursoNoEncontradoException;
import com.odonto.dentisys.exception.ValidacionException;
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
    public UsuarioDTO findById(Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioMapper::toDTO)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el usuario con ID: " + id));
    }

    @Transactional(readOnly = true)
    public Usuario findEntityById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el usuario con ID: " + id));
    }

    @Transactional
    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);

        // Validar duplicados para nuevos usuarios
        if (usuario.getId() == null) {
            if (usuarioRepository.existsByEmail(usuario.getEmail())) {
                throw new DuplicadoException("Ya existe un usuario con el email: " + usuario.getEmail());
            }
            if (usuarioRepository.existsByNumeroDocumento(usuario.getNumeroDocumento())) {
                throw new DuplicadoException(
                        "Ya existe un usuario con el número de documento: " + usuario.getNumeroDocumento());
            }
        }

        // Si es una actualización (tiene ID), verificar si la contraseña está vacía
        if (usuario.getId() != null) {
            Usuario usuarioExistente = usuarioRepository.findById(usuario.getId())
                    .orElseThrow(() -> new RecursoNoEncontradoException(
                            "No se encontró el usuario con ID: " + usuario.getId()));

            // Si la contraseña está vacía en el DTO, mantener la contraseña existente
            if (usuarioDTO.getPassword() == null || usuarioDTO.getPassword().trim().isEmpty()) {
                usuario.setPassword(usuarioExistente.getPassword());
            }
        }

        // Si es una actualización y no se especifica rol, mantener el rol existente
        if (usuario.getId() != null && usuarioDTO.getRolId() == null) {
            Usuario usuarioExistente = usuarioRepository.findById(usuario.getId())
                    .orElseThrow(() -> new RecursoNoEncontradoException(
                            "No se encontró el usuario con ID: " + usuario.getId()));
            usuario.setRol(usuarioExistente.getRol());
        }

        // Cargar el rol si existe
        if (usuarioDTO.getRolId() != null) {
            usuario.setRol(rolRepository.findById(usuarioDTO.getRolId())
                    .orElseThrow(() -> new RecursoNoEncontradoException(
                            "No se encontró el rol con ID: " + usuarioDTO.getRolId())));
        }

        // Validar que el email y número de documento no estén vacíos
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new ValidacionException("El email es obligatorio");
        }
        if (usuario.getNumeroDocumento() == null || usuario.getNumeroDocumento().trim().isEmpty()) {
            throw new ValidacionException("El número de documento es obligatorio");
        }

        Usuario savedUsuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(savedUsuario);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No se encontró el usuario con ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public UsuarioDTO findByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .map(usuarioMapper::toDTO)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el usuario con email: " + email));
    }

    @Transactional(readOnly = true)
    public UsuarioDTO findByNumeroDocumento(String numeroDocumento) {
        return usuarioRepository.findByNumeroDocumento(numeroDocumento)
                .map(usuarioMapper::toDTO)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No se encontró el usuario con número de documento: " + numeroDocumento));
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
    public Usuario findByEmailAndPassword(String email, String password) {
        return usuarioRepository.findByEmailAndPassword(email, password)
                .map(usuario -> {
                    usuario.setUltimoAcceso(LocalDateTime.now());
                    return usuarioRepository.save(usuario);
                })
                .orElseThrow(() -> new ValidacionException("Credenciales inválidas"));
    }
}