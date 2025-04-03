package com.odonto.dentisys.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odonto.dentisys.model.Medico;
import com.odonto.dentisys.model.Paciente;
import com.odonto.dentisys.model.Proforma;

@Repository
public interface ProformaRepository extends JpaRepository<Proforma, Long> {
    List<Proforma> findByPaciente(Paciente paciente);

    List<Proforma> findByMedico(Medico medico);

    List<Proforma> findByFechaEmisionBetween(LocalDate fechaInicio, LocalDate fechaFin);

    List<Proforma> findByEstado(String estado);
}