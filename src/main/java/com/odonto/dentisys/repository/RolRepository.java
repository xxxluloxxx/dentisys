package com.odonto.dentisys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odonto.dentisys.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    boolean existsByNombre(String nombre);
}