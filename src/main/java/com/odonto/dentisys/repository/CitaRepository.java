package com.odonto.dentisys.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odonto.dentisys.model.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByPacienteId(Long pacienteId);

    List<Cita> findByMedicoId(Long medicoId);

    List<Cita> findByPacienteIdAndFechaCitaBetween(Long pacienteId, LocalDate fechaInicio, LocalDate fechaFin);

    List<Cita> findByMedicoIdAndFechaCitaBetween(Long medicoId, LocalDate fechaInicio, LocalDate fechaFin);

    List<Cita> findByEstado(String estado);
}