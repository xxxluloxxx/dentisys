package com.odonto.dentisys.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odonto.dentisys.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    boolean existsByIdentificacion(String identificacion);

    List<Paciente> findByCreatedAtBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
}