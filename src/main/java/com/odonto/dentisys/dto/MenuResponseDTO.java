package com.odonto.dentisys.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuResponseDTO {
    private List<MenuItemDTO> menu;

    // Constructores
    public MenuResponseDTO() {
    }

    public MenuResponseDTO(List<MenuItemDTO> menu) {
        this.menu = menu;
    }

    // Getters y Setters
    public List<MenuItemDTO> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuItemDTO> menu) {
        this.menu = menu;
    }
}