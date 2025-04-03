package com.odonto.dentisys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odonto.dentisys.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    boolean existsByCodigo(String codigo);

    List<Producto> findByCategoria(String categoria);

    List<Producto> findByEstado(Boolean estado);
}