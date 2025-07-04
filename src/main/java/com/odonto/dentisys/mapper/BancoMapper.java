package com.odonto.dentisys.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.odonto.dentisys.dto.BancoDTO;
import com.odonto.dentisys.model.Banco;

@Component
public class BancoMapper {

    public BancoDTO toDTO(Banco banco) {
        if (banco == null) {
            return null;
        }

        BancoDTO dto = new BancoDTO();
        dto.setId(banco.getId());
        dto.setNombreCuenta(banco.getNombreCuenta());
        dto.setBanco(banco.getBanco());
        dto.setCuenta(banco.getCuenta());
        dto.setColor(banco.getColor());
        dto.setCreatedAt(banco.getCreatedAt());
        dto.setUpdatedAt(banco.getUpdatedAt());

        return dto;
    }

    public Banco toEntity(BancoDTO dto) {
        if (dto == null) {
            return null;
        }

        Banco banco = new Banco();
        banco.setId(dto.getId());
        banco.setNombreCuenta(dto.getNombreCuenta());
        banco.setBanco(dto.getBanco());
        banco.setCuenta(dto.getCuenta());
        banco.setColor(dto.getColor());

        return banco;
    }

    public List<BancoDTO> toDTOList(List<Banco> bancos) {
        if (bancos == null) {
            return null;
        }

        return bancos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
} 