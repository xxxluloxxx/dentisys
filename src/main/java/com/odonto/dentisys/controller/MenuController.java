package com.odonto.dentisys.controller;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/menu1")
    public ResponseEntity<?> getMenu1() {
        try {
            ClassPathResource resource = new ClassPathResource("menuAdmin.json");
            Object menu = objectMapper.readValue(resource.getInputStream(), Object.class);
            return ResponseEntity.ok(menu);
        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body("Error al leer el archivo menuAdmin.json: " + e.getMessage());
        }
    }

    @GetMapping("/menu2")
    public ResponseEntity<?> getMenu2() {
        try {
            ClassPathResource resource = new ClassPathResource("menuMedico.json");
            Object menu = objectMapper.readValue(resource.getInputStream(), Object.class);
            return ResponseEntity.ok(menu);
        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body("Error al leer el archivo menuMesero.json: " + e.getMessage());
        }
    }
}