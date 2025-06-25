package com.odonto.dentisys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.odonto.dentisys.dto.MenuResponseDTO;
import com.odonto.dentisys.service.MenuService;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * Obtiene el menú desde la base de datos para un rol específico
     */
    @GetMapping("/rol/{rolId}")
    public ResponseEntity<?> getMenuByRol(@PathVariable Integer rolId) {
        try {
            MenuResponseDTO menuResponse = menuService.getMenuByRolId(rolId);
            return ResponseEntity.ok(menuResponse);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error al obtener el menú para el rol " + rolId + ": " + e.getMessage());
        }
    }
}