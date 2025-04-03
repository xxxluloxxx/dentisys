package com.odonto.dentisys.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.odonto.dentisys.dto.RolPermisoDTO;
import com.odonto.dentisys.model.RolPermiso;

@Mapper(componentModel = "spring")
public interface RolPermisoMapper {
    @Mapping(source = "rol.id", target = "rolId")
    @Mapping(source = "permiso.id", target = "permisoId")
    RolPermisoDTO toDTO(RolPermiso rolPermiso);

    @Mapping(source = "rolId", target = "rol.id")
    @Mapping(source = "permisoId", target = "permiso.id")
    RolPermiso toEntity(RolPermisoDTO rolPermisoDTO);
}