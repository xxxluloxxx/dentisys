package com.odonto.dentisys.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odonto.dentisys.model.Cobranza;
import com.odonto.dentisys.model.Paciente;
import com.odonto.dentisys.model.Proforma;

@Repository
public interface CobranzaRepository extends JpaRepository<Cobranza, Integer> {
    List<Cobranza> findByPaciente(Paciente paciente);

    List<Cobranza> findByProforma(Proforma proforma);

    List<Cobranza> findByFechaPagoBetween(LocalDate fechaInicio, LocalDate fechaFin);

    List<Cobranza> findByEstado(String estado);

    List<Cobranza> findByPacienteAndEstado(Paciente paciente, String estado);
}