package com.odonto.dentisys.mapper;

import org.mapstruct.Mapper;

import com.odonto.dentisys.dto.RolDTO;
import com.odonto.dentisys.model.Rol;

@Mapper(componentModel = "spring")
public interface RolMapper {
    RolDTO toDTO(Rol rol);

    Rol toEntity(RolDTO rolDTO);
}