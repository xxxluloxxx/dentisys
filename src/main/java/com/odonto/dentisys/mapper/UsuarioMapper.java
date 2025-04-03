package com.odonto.dentisys.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.odonto.dentisys.dto.UsuarioDTO;
import com.odonto.dentisys.model.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    @Mapping(source = "rol.id", target = "rolId")
    UsuarioDTO toDTO(Usuario usuario);

    @Mapping(source = "rolId", target = "rol.id")
    Usuario toEntity(UsuarioDTO usuarioDTO);
}