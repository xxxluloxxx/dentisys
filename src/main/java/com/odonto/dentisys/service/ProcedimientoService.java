package com.odonto.dentisys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odonto.dentisys.model.Procedimiento;
import com.odonto.dentisys.repository.ProcedimientoRepository;

@Service
public class ProcedimientoService {

    @Autowired
    private ProcedimientoRepository procedimientoRepository;

    @Transactional(readOnly = true)
    public List<Procedimiento> findAll() {
        return procedimientoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Procedimiento> findById(Integer id) {
        return procedimientoRepository.findById(id);
    }

    @Transactional
    public Procedimiento save(Procedimiento procedimiento) {
        return procedimientoRepository.save(procedimiento);
    }

    @Transactional
    public void deleteById(Integer id) {
        procedimientoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Procedimiento> findByFichaId(Integer fichaId) {
        return procedimientoRepository.findByFichaId(fichaId);
    }
}