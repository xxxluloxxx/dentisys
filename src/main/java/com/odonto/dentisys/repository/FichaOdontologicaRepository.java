package com.odonto.dentisys.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odonto.dentisys.model.FichaOdontologica;

@Repository
public interface FichaOdontologicaRepository extends JpaRepository<FichaOdontologica, Integer> {
        List<FichaOdontologica> findByPacienteId(Integer pacienteId);

        List<FichaOdontologica> findByMedicoId(Integer medicoId);

        List<FichaOdontologica> findByPacienteIdAndFechaCreacionBetween(Integer pacienteId, LocalDate fechaInicio,
                        LocalDate fechaFin);

        List<FichaOdontologica> findByMedicoIdAndFechaCreacionBetween(Integer medicoId, LocalDate fechaInicio,
                        LocalDate fechaFin);
}