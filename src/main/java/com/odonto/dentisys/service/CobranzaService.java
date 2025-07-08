package com.odonto.dentisys.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odonto.dentisys.model.Banco;
import com.odonto.dentisys.model.Cobranza;
import com.odonto.dentisys.model.Paciente;
import com.odonto.dentisys.model.Proforma;
import com.odonto.dentisys.repository.BancoRepository;
import com.odonto.dentisys.repository.CobranzaRepository;
import com.odonto.dentisys.repository.ProformaRepository;

@Service
public class CobranzaService {

    @Autowired
    private CobranzaRepository cobranzaRepository;

    @Autowired
    private ProformaRepository proformaRepository;

    @Autowired
    private BancoRepository bancoRepository;

    @Autowired
    private CuentaService cuentaService;

    @Transactional(readOnly = true)
    public List<Cobranza> findAll() {
        return cobranzaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Cobranza findById(Long id) {
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
    public void deleteById(Long id) {
        // Buscar las cuentas asociadas a esta cobranza y eliminarlas
        List<com.odonto.dentisys.dto.CuentaDTO> cuentasAsociadas = cuentaService.findByCobranza(id);
        for (com.odonto.dentisys.dto.CuentaDTO cuenta : cuentasAsociadas) {
            cuentaService.deleteById(cuenta.getId());
        }

        // Eliminar la cobranza
        cobranzaRepository.deleteById(id);
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
    public List<Cobranza> findByBanco(Banco banco) {
        return cobranzaRepository.findByBanco(banco);
    }

    @Transactional(readOnly = true)
    public List<Cobranza> findByPaciente(Paciente paciente) {
        List<Proforma> proformas = proformaRepository.findByPaciente(paciente);
        return cobranzaRepository.findByProformaIn(proformas);
    }

    @Transactional(readOnly = true)
    public List<Cobranza> findByPacienteAndEstado(Paciente paciente, String estado) {
        List<Proforma> proformas = proformaRepository.findByPaciente(paciente);
        return cobranzaRepository.findByProformaInAndEstado(proformas, estado);
    }

    @Transactional
    public Cobranza update(Long id, Cobranza cobranza) {
        // Actualizar la cobranza
        cobranza.setId(id);
        Cobranza cobranzaActualizada = cobranzaRepository.save(cobranza);

        // Buscar las cuentas asociadas a esta cobranza y actualizar solo el monto
        List<com.odonto.dentisys.dto.CuentaDTO> cuentasAsociadas = cuentaService.findByCobranza(id);
        for (com.odonto.dentisys.dto.CuentaDTO cuenta : cuentasAsociadas) {
            // Actualizar únicamente el monto de la cuenta
            cuenta.setMonto(cobranzaActualizada.getMonto());

            // Guardar la cuenta actualizada
            cuentaService.save(cuenta);
        }

        return cobranzaActualizada;
    }
}