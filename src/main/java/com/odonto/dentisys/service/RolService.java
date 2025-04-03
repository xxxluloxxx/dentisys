package com.odonto.dentisys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odonto.dentisys.dto.RolDTO;
import com.odonto.dentisys.mapper.RolMapper;
import com.odonto.dentisys.model.Rol;
import com.odonto.dentisys.repository.RolRepository;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private RolMapper rolMapper;

    @Transactional(readOnly = true)
    public List<RolDTO> findAll() {
        return rolRepository.findAll().stream()
                .map(rolMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<RolDTO> findById(Long id) {
        return rolRepository.findById(id)
                .map(rolMapper::toDTO);
    }

    @Transactional
    public RolDTO save(RolDTO rolDTO) {
        Rol rol = rolMapper.toEntity(rolDTO);
        rol = rolRepository.save(rol);
        return rolMapper.toDTO(rol);
    }

    @Transactional
    public void deleteById(Long id) {
        rolRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsByNombre(String nombre) {
        return rolRepository.existsByNombre(nombre);
    }
}