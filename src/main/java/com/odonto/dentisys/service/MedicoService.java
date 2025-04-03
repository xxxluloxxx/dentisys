package com.odonto.dentisys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odonto.dentisys.model.Medico;
import com.odonto.dentisys.repository.MedicoRepository;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Transactional(readOnly = true)
    public List<Medico> findAll() {
        return medicoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Medico> findById(Integer id) {
        return medicoRepository.findById(id);
    }

    @Transactional
    public Medico save(Medico medico) {
        if (medico.getId() == null) {
            if (medicoRepository.existsByNumeroDocumento(medico.getNumeroDocumento())) {
                throw new RuntimeException("Ya existe un médico con ese número de documento");
            }
            if (medicoRepository.existsByEmail(medico.getEmail())) {
                throw new RuntimeException("Ya existe un médico con ese email");
            }
        }
        return medicoRepository.save(medico);
    }

    @Transactional
    public void deleteById(Integer id) {
        medicoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Medico> findByNumeroDocumento(String numeroDocumento) {
        return medicoRepository.findAll().stream()
                .filter(m -> m.getNumeroDocumento().equals(numeroDocumento))
                .findFirst();
    }
}