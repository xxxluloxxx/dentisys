package com.odonto.dentisys.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odonto.dentisys.dto.ImagenFichaDTO;
import com.odonto.dentisys.model.FichaOdontologica;
import com.odonto.dentisys.model.ImagenFicha;
import com.odonto.dentisys.repository.FichaOdontologicaRepository;
import com.odonto.dentisys.repository.ImagenFichaRepository;

@Service
public class ImagenFichaService {

    @Autowired
    private ImagenFichaRepository imagenFichaRepository;

    @Autowired
    private FichaOdontologicaRepository fichaOdontologicaRepository;

    @Transactional(readOnly = true)
    public List<ImagenFichaDTO> findAll() {
        return imagenFichaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(java.util.stream.Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<ImagenFichaDTO> findById(Integer id) {
        return imagenFichaRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Transactional
    public ImagenFicha save(ImagenFichaDTO dto) {
        ImagenFicha imagenFicha = new ImagenFicha();
        imagenFicha.setNombre(dto.getNombre());
        imagenFicha.setTipoImagen(dto.getTipoImagen());
        imagenFicha.setDescripcion(dto.getDescripcion());

        // Convertir base64 a byte[]
        if (dto.getImagenBase64() != null && !dto.getImagenBase64().isEmpty()) {
            String base64Image = dto.getImagenBase64().split(",")[1];
            imagenFicha.setImagen(Base64.getDecoder().decode(base64Image));
        }

        // Obtener la ficha odontológica
        FichaOdontologica ficha = fichaOdontologicaRepository.findById(dto.getFichaId())
                .orElseThrow(
                        () -> new RuntimeException("Ficha odontológica no encontrada con ID: " + dto.getFichaId()));
        imagenFicha.setFicha(ficha);

        return imagenFichaRepository.save(imagenFicha);
    }

    @Transactional
    public void deleteById(Integer id) {
        imagenFichaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<ImagenFichaDTO> findByFichaId(Integer fichaId) {
        return imagenFichaRepository.findByFichaId(fichaId).stream()
                .map(this::convertToDTO)
                .collect(java.util.stream.Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ImagenFichaDTO> findByTipoImagen(String tipoImagen) {
        return imagenFichaRepository.findByTipoImagen(tipoImagen).stream()
                .map(this::convertToDTO)
                .collect(java.util.stream.Collectors.toList());
    }

    private ImagenFichaDTO convertToDTO(ImagenFicha imagenFicha) {
        ImagenFichaDTO dto = new ImagenFichaDTO();
        dto.setId(imagenFicha.getId());
        dto.setFichaId(imagenFicha.getFicha().getId());
        dto.setNombre(imagenFicha.getNombre());
        dto.setTipoImagen(imagenFicha.getTipoImagen());
        dto.setDescripcion(imagenFicha.getDescripcion());
        dto.setCreatedAt(imagenFicha.getCreatedAt());
        dto.setUpdatedAt(imagenFicha.getUpdatedAt());

        // Convertir byte[] a base64
        if (imagenFicha.getImagen() != null) {
            dto.setImagenBase64(
                    "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imagenFicha.getImagen()));
        }

        return dto;
    }
}