package com.odonto.dentisys.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odonto.dentisys.model.AgendaCita;

@Repository
public interface AgendaCitaRepository extends JpaRepository<AgendaCita, Integer> {
    List<AgendaCita> findByMedicoIdAndFecha(Integer medicoId, LocalDate fecha);

    List<AgendaCita> findByMedicoIdAndFechaBetween(Integer medicoId, LocalDate fechaInicio, LocalDate fechaFin);
}