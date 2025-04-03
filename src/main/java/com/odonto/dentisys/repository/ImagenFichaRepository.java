package com.odonto.dentisys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odonto.dentisys.model.ImagenFicha;

@Repository
public interface ImagenFichaRepository extends JpaRepository<ImagenFicha, Integer> {
    List<ImagenFicha> findByFichaId(Integer fichaId);

    List<ImagenFicha> findByTipoImagen(String tipoImagen);
}