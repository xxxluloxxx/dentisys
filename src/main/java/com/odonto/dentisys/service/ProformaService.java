package com.odonto.dentisys.service;

import java.time.LocalDate;
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
    public Proforma findById(Integer id) {
        return proformaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proforma no encontrada con ID: " + id));
    }

    @Transactional
    public Proforma save(Proforma proforma) {
        if (proforma.getSubtotal() < 0 || proforma.getIva() < 0 || proforma.getTotal() < 0) {
            throw new RuntimeException("Los montos no pueden ser negativos");
        }
        if (proforma.getTotal() != (proforma.getSubtotal() + proforma.getIva())) {
            throw new RuntimeException("El total debe ser igual a la suma del subtotal y el IVA");
        }
        return proformaRepository.save(proforma);
    }

    @Transactional
    public void deleteById(Integer id) {
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
    public List<Proforma> findByFechaEmisionBetween(LocalDate fechaInicio, LocalDate fechaFin) {
        return proformaRepository.findByFechaEmisionBetween(fechaInicio, fechaFin);
    }

    @Transactional(readOnly = true)
    public List<Proforma> findByEstado(String estado) {
        return proformaRepository.findByEstado(estado);
    }
}