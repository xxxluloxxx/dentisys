package com.odonto.dentisys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Optional<Paciente> findById(Integer id) {
        return pacienteRepository.findById(id);
    }

    @Transactional
    public Paciente save(Paciente paciente) {
        if (paciente.getId() == null && pacienteRepository.existsByIdentificacion(paciente.getIdentificacion())) {
            throw new RuntimeException("Ya existe un paciente con esa identificación");
        }
        return pacienteRepository.save(paciente);
    }

    @Transactional
    public void deleteById(Integer id) {
        pacienteRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Paciente> findByIdentificacion(String identificacion) {
        return pacienteRepository.findAll().stream()
                .filter(p -> p.getIdentificacion().equals(identificacion))
                .findFirst();
    }
}