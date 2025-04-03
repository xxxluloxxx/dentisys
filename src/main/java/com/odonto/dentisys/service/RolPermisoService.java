package com.odonto.dentisys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odonto.dentisys.dto.RolPermisoDTO;
import com.odonto.dentisys.mapper.RolPermisoMapper;
import com.odonto.dentisys.model.RolPermiso;
import com.odonto.dentisys.repository.PermisoRepository;
import com.odonto.dentisys.repository.RolPermisoRepository;
import com.odonto.dentisys.repository.RolRepository;

@Service
public class RolPermisoService {

    @Autowired
    private RolPermisoRepository rolPermisoRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PermisoRepository permisoRepository;

    @Autowired
    private RolPermisoMapper rolPermisoMapper;

    @Transactional(readOnly = true)
    public List<RolPermisoDTO> findAll() {
        return rolPermisoRepository.findAll().stream()
                .map(rolPermisoMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<RolPermisoDTO> findById(Long id) {
        return rolPermisoRepository.findById(id)
                .map(rolPermisoMapper::toDTO);
    }

    @Transactional
    public RolPermisoDTO save(RolPermisoDTO rolPermisoDTO) {
        RolPermiso rolPermiso = rolPermisoMapper.toEntity(rolPermisoDTO);

        // Cargar las entidades relacionadas
        rolPermiso.setRol(rolRepository.findById(rolPermisoDTO.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado")));
        rolPermiso.setPermiso(permisoRepository.findById(rolPermisoDTO.getPermisoId())
                .orElseThrow(() -> new RuntimeException("Permiso no encontrado")));

        rolPermiso = rolPermisoRepository.save(rolPermiso);
        return rolPermisoMapper.toDTO(rolPermiso);
    }

    @Transactional
    public void deleteById(Long id) {
        rolPermisoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<RolPermisoDTO> findByRolId(Long rolId) {
        return rolPermisoRepository.findByRolId(rolId).stream()
                .map(rolPermisoMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public boolean existsByRolIdAndPermisoId(Long rolId, Long permisoId) {
        return rolPermisoRepository.existsByRolIdAndPermisoId(rolId, permisoId);
    }
}