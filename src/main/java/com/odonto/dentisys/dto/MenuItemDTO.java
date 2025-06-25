package com.odonto.dentisys.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuItemDTO {
    private String label;
    private String icon;
    private String to;
    private List<MenuItemDTO> items;

    // Constructores
    public MenuItemDTO() {
    }

    public MenuItemDTO(String label, String icon, String to) {
        this.label = label;
        this.icon = icon;
        this.to = to;
    }

    public MenuItemDTO(String label, String icon, String to, List<MenuItemDTO> items) {
        this.label = label;
        this.icon = icon;
        this.to = to;
        this.items = items;
    }

    // Getters y Setters
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<MenuItemDTO> getItems() {
        return items;
    }

    public void setItems(List<MenuItemDTO> items) {
        this.items = items;
    }
}