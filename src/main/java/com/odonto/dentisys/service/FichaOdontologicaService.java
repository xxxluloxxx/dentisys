package com.odonto.dentisys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odonto.dentisys.dto.ProformaCobranzaDTO;
import com.odonto.dentisys.mapper.ProformaCobranzaMapper;
import com.odonto.dentisys.model.FichaOdontologica;
import com.odonto.dentisys.repository.FichaOdontologicaRepository;

@Service
public class FichaOdontologicaService {

    @Autowired
    private FichaOdontologicaRepository fichaOdontologicaRepository;

    @Autowired
    private ProformaService proformaService;

    @Autowired
    private CobranzaService cobranzaService;

    @Autowired
    private ProformaCobranzaMapper proformaCobranzaMapper;

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
    public List<ProformaCobranzaDTO> getProformasCobranzasByFichaId(Integer fichaId) {
        Optional<FichaOdontologica> fichaOpt = fichaOdontologicaRepository.findById(fichaId);
        if (fichaOpt.isEmpty()) {
            return List.of();
        }

        FichaOdontologica ficha = fichaOpt.get();
        var paciente = ficha.getPaciente();

        // Obtener todas las proformas del paciente
        var proformas = proformaService.findByPaciente(paciente);

        return proformas.stream()
                .map(proforma -> {
                    // Obtener las cobranzas de cada proforma
                    var cobranzas = cobranzaService.findByProforma(proforma);
                    return proformaCobranzaMapper.toDTO(proforma, cobranzas);
                })
                .toList();
    }
}