package com.odonto.dentisys.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odonto.dentisys.model.Medico;
import com.odonto.dentisys.model.Paciente;
import com.odonto.dentisys.model.Proforma;
import com.odonto.dentisys.repository.ProformaRepository;

@Service
public class ProformaService {

    @Autowired
    private ProformaRepository proformaRepository;

    @Transactional(readOnly = true)
    public List<Proforma> findAll() {
        return proformaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Proforma findById(Long id) {
        return proformaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proforma no encontrada con ID: " + id));
    }

    @Transactional
    public Proforma save(Proforma proforma) {
        return proformaRepository.save(proforma);
    }

    @Transactional
    public void deleteById(Long id) {
        proformaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Proforma> findByPaciente(Paciente paciente) {
        return proformaRepository.findByPaciente(paciente);
    }

    @Transactional(readOnly = true)
    public List<Proforma> findByMedico(Medico medico) {
        return proformaRepository.findByMedico(medico);
    }

    @Transactional(readOnly = true)
    public List<Proforma> findByCreatedAtBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return proformaRepository.findByCreatedAtBetween(fechaInicio, fechaFin);
    }

    @Transactional(readOnly = true)
    public List<Proforma> findByEstado(String estado) {
        return proformaRepository.findByEstado(estado);
    }
}