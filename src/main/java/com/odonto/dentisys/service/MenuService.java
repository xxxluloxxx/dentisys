package com.odonto.dentisys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.odonto.dentisys.dto.MenuItemDTO;
import com.odonto.dentisys.dto.MenuResponseDTO;
import com.odonto.dentisys.model.Menu;
import com.odonto.dentisys.repository.MenuRepository;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    /**
     * Obtiene el menú completo para un rol específico
     */
    public MenuResponseDTO getMenuByRolId(Integer rolId) {
        List<Menu> menuItems = menuRepository.findMenuByRolId(rolId);

        if (menuItems.isEmpty()) {
            return new MenuResponseDTO(new ArrayList<>());
        }

        // Construir estructura jerárquica
        List<MenuItemDTO> menuStructure = buildMenuHierarchy(menuItems);

        return new MenuResponseDTO(menuStructure);
    }

    /**
     * Construye la estructura jerárquica del menú
     */
    private List<MenuItemDTO> buildMenuHierarchy(List<Menu> menuItems) {
        // Agrupar elementos por parent_id
        Map<Integer, List<Menu>> menuByParent = menuItems.stream()
                .collect(Collectors.groupingBy(item -> item.getParentId() != null ? item.getParentId() : 0));

        // Obtener elementos raíz (parent_id = null)
        List<Menu> rootItems = menuByParent.get(0);

        if (rootItems == null) {
            return new ArrayList<>();
        }

        // Construir estructura recursiva
        return rootItems.stream()
                .map(item -> buildMenuItem(item, menuByParent))
                .collect(Collectors.toList());
    }

    /**
     * Construye un elemento del menú con sus hijos
     */
    private MenuItemDTO buildMenuItem(Menu menuItem, Map<Integer, List<Menu>> menuByParent) {
        MenuItemDTO dto = new MenuItemDTO();
        dto.setLabel(menuItem.getLabel());

        // Solo establecer icon si no es null
        if (menuItem.getIcon() != null && !menuItem.getIcon().trim().isEmpty()) {
            dto.setIcon(menuItem.getIcon());
        }

        // Solo establecer to si no es null
        if (menuItem.getToPath() != null && !menuItem.getToPath().trim().isEmpty()) {
            dto.setTo(menuItem.getToPath());
        }

        // Buscar elementos hijos
        List<Menu> children = menuByParent.get(menuItem.getId());
        if (children != null && !children.isEmpty()) {
            List<MenuItemDTO> childItems = children.stream()
                    .map(child -> buildMenuItem(child, menuByParent))
                    .collect(Collectors.toList());
            dto.setItems(childItems);
        }

        return dto;
    }
}