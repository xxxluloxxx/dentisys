package com.odonto.dentisys.mapper;

import org.mapstruct.Mapper;

import com.odonto.dentisys.dto.PermisoDTO;
import com.odonto.dentisys.model.Permiso;

@Mapper(componentModel = "spring")
public interface PermisoMapper {
    PermisoDTO toDTO(Permiso permiso);

    Permiso toEntity(PermisoDTO permisoDTO);
}