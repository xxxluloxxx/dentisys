package com.odonto.dentisys.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odonto.dentisys.model.FichaOdontologica;
import com.odonto.dentisys.repository.FichaOdontologicaRepository;

@Service
public class FichaOdontologicaService {

    @Autowired
    private FichaOdontologicaRepository fichaOdontologicaRepository;

    @Transactional(readOnly = true)
    public List<FichaOdontologica> findAll() {
        return fichaOdontologicaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<FichaOdontologica> findById(Integer id) {
        return fichaOdontologicaRepository.findById(id);
    }

    @Transactional
    public FichaOdontologica save(FichaOdontologica fichaOdontologica) {
        return fichaOdontologicaRepository.save(fichaOdontologica);
    }

    @Transactional
    public void deleteById(Integer id) {
        fichaOdontologicaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<FichaOdontologica> findByPacienteId(Integer pacienteId) {
        return fichaOdontologicaRepository.findByPacienteId(pacienteId);
    }

    @Transactional(readOnly = true)
    public List<FichaOdontologica> findByMedicoId(Integer medicoId) {
        return fichaOdontologicaRepository.findByMedicoId(medicoId);
    }

    @Transactional(readOnly = true)
    public List<FichaOdontologica> findByPacienteIdAndFechaCreacionBetween(Integer pacienteId, LocalDate fechaInicio,
            LocalDate fechaFin) {
        return fichaOdontologicaRepository.findByPacienteIdAndFechaCreacionBetween(pacienteId, fechaInicio, fechaFin);
    }

    @Transactional(readOnly = true)
    public List<FichaOdontologica> findByMedicoIdAndFechaCreacionBetween(Integer medicoId, LocalDate fechaInicio,
            LocalDate fechaFin) {
        return fichaOdontologicaRepository.findByMedicoIdAndFechaCreacionBetween(medicoId, fechaInicio, fechaFin);
    }
}