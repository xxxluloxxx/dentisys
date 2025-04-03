package com.odonto.dentisys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odonto.dentisys.model.RolPermiso;

@Repository
public interface RolPermisoRepository extends JpaRepository<RolPermiso, Long> {
    List<RolPermiso> findByRolId(Long rolId);

    boolean existsByRolIdAndPermisoId(Long rolId, Long permisoId);
}