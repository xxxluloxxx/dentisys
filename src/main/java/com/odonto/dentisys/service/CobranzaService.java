package com.odonto.dentisys.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odonto.dentisys.model.Cobranza;
import com.odonto.dentisys.model.Paciente;
import com.odonto.dentisys.model.Proforma;
import com.odonto.dentisys.repository.CobranzaRepository;

@Service
public class CobranzaService {

    @Autowired
    private CobranzaRepository cobranzaRepository;

    @Transactional(readOnly = true)
    public List<Cobranza> findAll() {
        return cobranzaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Cobranza findById(Integer id) {
        return cobranzaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cobranza no encontrada con ID: " + id));
    }

    @Transactional
    public Cobranza save(Cobranza cobranza) {
        if (cobranza.getMonto() < 0) {
            throw new RuntimeException("El monto no puede ser negativo");
        }
        return cobranzaRepository.save(cobranza);
    }

    @Transactional
    public void deleteById(Integer id) {
        cobranzaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Cobranza> findByPaciente(Paciente paciente) {
        return cobranzaRepository.findByPaciente(paciente);
    }

    @Transactional(readOnly = true)
    public List<Cobranza> findByProforma(Proforma proforma) {
        return cobranzaRepository.findByProforma(proforma);
    }

    @Transactional(readOnly = true)
    public List<Cobranza> findByFechaPagoBetween(LocalDate fechaInicio, LocalDate fechaFin) {
        return cobranzaRepository.findByFechaPagoBetween(fechaInicio, fechaFin);
    }

    @Transactional(readOnly = true)
    public List<Cobranza> findByEstado(String estado) {
        return cobranzaRepository.findByEstado(estado);
    }

    @Transactional(readOnly = true)
    public List<Cobranza> findByPacienteAndEstado(Paciente paciente, String estado) {
        return cobranzaRepository.findByPacienteAndEstado(paciente, estado);
    }
}