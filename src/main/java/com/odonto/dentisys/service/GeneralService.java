package com.odonto.dentisys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odonto.dentisys.model.General;
import com.odonto.dentisys.repository.GeneralRepository;

@Service
public class GeneralService {

    @Autowired
    private GeneralRepository generalRepository;

    @Transactional(readOnly = true)
    public List<General> findAll() {
        return generalRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<General> findById(Integer id) {
        return generalRepository.findById(id);
    }

    @Transactional
    public General save(General general) {
        if (general.getId() == null && generalRepository.existsByParametro(general.getParametro())) {
            throw new RuntimeException("Ya existe un parámetro con ese nombre");
        }
        return generalRepository.save(general);
    }

    @Transactional
    public void deleteById(Integer id) {
        generalRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Optional<General> findByParametro(String parametro) {
        return generalRepository.findAll().stream()
                .filter(g -> g.getParametro().equals(parametro))
                .findFirst();
    }
}