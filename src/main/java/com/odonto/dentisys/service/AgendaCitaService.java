package com.odonto.dentisys.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odonto.dentisys.model.AgendaCita;
import com.odonto.dentisys.repository.AgendaCitaRepository;

@Service
public class AgendaCitaService {

    @Autowired
    private AgendaCitaRepository agendaCitaRepository;

    @Transactional(readOnly = true)
    public List<AgendaCita> findAll() {
        return agendaCitaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<AgendaCita> findById(Integer id) {
        return agendaCitaRepository.findById(id);
    }

    @Transactional
    public AgendaCita save(AgendaCita agendaCita) {
        return agendaCitaRepository.save(agendaCita);
    }

    @Transactional
    public void deleteById(Integer id) {
        agendaCitaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<AgendaCita> findByMedicoIdAndFecha(Integer medicoId, LocalDate fecha) {
        return agendaCitaRepository.findByMedicoIdAndFecha(medicoId, fecha);
    }

    @Transactional(readOnly = true)
    public List<AgendaCita> findByMedicoIdAndFechaBetween(Integer medicoId, LocalDate fechaInicio, LocalDate fechaFin) {
        return agendaCitaRepository.findByMedicoIdAndFechaBetween(medicoId, fechaInicio, fechaFin);
    }
}