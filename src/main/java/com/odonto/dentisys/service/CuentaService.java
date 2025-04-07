package com.odonto.dentisys.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odonto.dentisys.dto.CuentaDTO;
import com.odonto.dentisys.mapper.CuentaMapper;
import com.odonto.dentisys.model.Categoria;
import com.odonto.dentisys.model.Cobranza;
import com.odonto.dentisys.model.Cuenta;
import com.odonto.dentisys.repository.CategoriaRepository;
import com.odonto.dentisys.repository.CobranzaRepository;
import com.odonto.dentisys.repository.CuentaRepository;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CobranzaRepository cobranzaRepository;

    @Autowired
    private CuentaMapper cuentaMapper;

    @Transactional(readOnly = true)
    public List<CuentaDTO> findAll() {
        return cuentaRepository.findAll().stream()
                .map(cuentaMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<CuentaDTO> findById(Long id) {
        return cuentaRepository.findById(id)
                .map(cuentaMapper::toDTO);
    }

    @Transactional
    public CuentaDTO save(CuentaDTO cuentaDTO) {
        Cuenta cuenta = cuentaMapper.toEntity(cuentaDTO);

        // Cargar la categoría si existe
        if (cuentaDTO.getCategoriaId() != null) {
            cuenta.setCategoria(categoriaRepository.findById(cuentaDTO.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada")));
        }

        // Cargar la cobranza si existe
        if (cuentaDTO.getCobranzaId() != null) {
            cuenta.setCobranza(cobranzaRepository.findById(cuentaDTO.getCobranzaId())
                    .orElseThrow(() -> new RuntimeException("Cobranza no encontrada")));
        }

        cuenta = cuentaRepository.save(cuenta);
        return cuentaMapper.toDTO(cuenta);
    }

    @Transactional
    public void deleteById(Long id) {
        cuentaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<CuentaDTO> findByCategoria(Long categoriaId) {
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        return cuentaRepository.findByCategoria(categoria).stream()
                .map(cuentaMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CuentaDTO> findByCobranza(Long cobranzaId) {
        Cobranza cobranza = cobranzaRepository.findById(cobranzaId)
                .orElseThrow(() -> new RuntimeException("Cobranza no encontrada"));
        return cuentaRepository.findByCobranza(cobranza).stream()
                .map(cuentaMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CuentaDTO> findByFechaMovimientoBetween(LocalDate fechaInicio, LocalDate fechaFin) {
        return cuentaRepository.findByFechaMovimientoBetween(fechaInicio, fechaFin).stream()
                .map(cuentaMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CuentaDTO> findByEstado(Boolean estado) {
        return cuentaRepository.findByEstado(estado).stream()
                .map(cuentaMapper::toDTO)
                .toList();
    }
}