package com.odonto.dentisys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odonto.dentisys.model.Procedimiento;

@Repository
public interface ProcedimientoRepository extends JpaRepository<Procedimiento, Integer> {
    List<Procedimiento> findByFichaId(Integer fichaId);
}