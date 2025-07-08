package com.odonto.dentisys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odonto.dentisys.exception.DuplicadoException;
import com.odonto.dentisys.exception.RecursoNoEncontradoException;
import com.odonto.dentisys.model.Paciente;
import com.odonto.dentisys.repository.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional(readOnly = true)
    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Paciente findById(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el paciente con ID: " + id));
    }

    @Transactional
    public Paciente save(Paciente paciente) {
        // Validar si ya existe un paciente con la misma identificación
        if (paciente.getId() == null && pacienteRepository.existsByIdentificacion(paciente.getIdentificacion())) {
            throw new DuplicadoException(
                    "Ya existe un paciente con la identificación: " + paciente.getIdentificacion());
        }

        // Si es una actualización, verificar que el paciente existe
        if (paciente.getId() != null && !pacienteRepository.existsById(paciente.getId())) {
            throw new RecursoNoEncontradoException("No se encontró el paciente con ID: " + paciente.getId());
        }

        return pacienteRepository.save(paciente);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No se encontró el paciente con ID: " + id);
        }
        pacienteRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Paciente findByIdentificacion(String identificacion) {
        return pacienteRepository.findAll().stream()
                .filter(p -> p.getIdentificacion().equals(identificacion))
                .findFirst()
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No se encontró el paciente con identificación: " + identificacion));
    }
}