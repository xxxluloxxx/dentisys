package com.odonto.dentisys.mapper;

import org.springframework.stereotype.Component;

import com.odonto.dentisys.dto.CuentaDTO;
import com.odonto.dentisys.model.Cuenta;

@Component
public class CuentaMapper {

    public CuentaDTO toDTO(Cuenta cuenta) {
        if (cuenta == null) {
            return null;
        }

        CuentaDTO dto = new CuentaDTO();
        dto.setId(cuenta.getId());
        dto.setCategoriaId(cuenta.getCategoria().getId());
        dto.setCategoriaNombre(cuenta.getCategoria().getNombre());
        if (cuenta.getCobranza() != null) {
            dto.setCobranzaId(cuenta.getCobranza().getId());
        }
        dto.setMonto(cuenta.getMonto());
        dto.setFechaMovimiento(cuenta.getFechaMovimiento());
        dto.setDescripcion(cuenta.getDescripcion());
        dto.setCreatedAt(cuenta.getCreatedAt());
        dto.setUpdatedAt(cuenta.getUpdatedAt());

        return dto;
    }

    public Cuenta toEntity(CuentaDTO dto) {
        if (dto == null) {
            return null;
        }

        Cuenta cuenta = new Cuenta();
        cuenta.setId(dto.getId());
        cuenta.setMonto(dto.getMonto());
        cuenta.setFechaMovimiento(dto.getFechaMovimiento());
        cuenta.setDescripcion(dto.getDescripcion());

        return cuenta;
    }
}