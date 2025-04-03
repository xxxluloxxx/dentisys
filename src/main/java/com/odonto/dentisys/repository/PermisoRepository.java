package com.odonto.dentisys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odonto.dentisys.model.Permiso;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Long> {
    boolean existsByNombre(String nombre);
}