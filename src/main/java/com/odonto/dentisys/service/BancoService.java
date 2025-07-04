package com.odonto.dentisys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odonto.dentisys.dto.BancoDTO;
import com.odonto.dentisys.mapper.BancoMapper;
import com.odonto.dentisys.model.Banco;
import com.odonto.dentisys.repository.BancoRepository;

@Service
public class BancoService {

    @Autowired
    private BancoRepository bancoRepository;

    @Autowired
    private BancoMapper bancoMapper;

    @Transactional(readOnly = true)
    public List<BancoDTO> findAll() {
        return bancoRepository.findAll().stream()
                .map(bancoMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<BancoDTO> findById(Long id) {
        return bancoRepository.findById(id)
                .map(bancoMapper::toDTO);
    }

    @Transactional
    public BancoDTO save(BancoDTO bancoDTO) {
        Banco banco = bancoMapper.toEntity(bancoDTO);
        banco = bancoRepository.save(banco);
        return bancoMapper.toDTO(banco);
    }

    @Transactional
    public void deleteById(Long id) {
        bancoRepository.deleteById(id);
    }
}