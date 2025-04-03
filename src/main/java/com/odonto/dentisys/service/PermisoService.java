package com.odonto.dentisys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odonto.dentisys.dto.PermisoDTO;
import com.odonto.dentisys.mapper.PermisoMapper;
import com.odonto.dentisys.model.Permiso;
import com.odonto.dentisys.repository.PermisoRepository;

@Service
public class PermisoService {

    @Autowired
    private PermisoRepository permisoRepository;

    @Autowired
    private PermisoMapper permisoMapper;

    @Transactional(readOnly = true)
    public List<PermisoDTO> findAll() {
        return permisoRepository.findAll().stream()
                .map(permisoMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<PermisoDTO> findById(Long id) {
        return permisoRepository.findById(id)
                .map(permisoMapper::toDTO);
    }

    @Transactional
    public PermisoDTO save(PermisoDTO permisoDTO) {
        Permiso permiso = permisoMapper.toEntity(permisoDTO);
        permiso = permisoRepository.save(permiso);
        return permisoMapper.toDTO(permiso);
    }

    @Transactional
    public void deleteById(Long id) {
        permisoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsByNombre(String nombre) {
        return permisoRepository.existsByNombre(nombre);
    }
}