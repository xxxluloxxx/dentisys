package com.odonto.dentisys.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.odonto.dentisys.dto.UsuarioDTO;
import com.odonto.dentisys.model.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    @Mapping(source = "rol.id", target = "rolId")
    @Mapping(source = "password", target = "password")
    UsuarioDTO toDTO(Usuario usuario);

    @Mapping(source = "rolId", target = "rol.id")
    @Mapping(source = "password", target = "password")
    Usuario toEntity(UsuarioDTO usuarioDTO);
}