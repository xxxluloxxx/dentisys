package com.odonto.dentisys.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odonto.dentisys.model.Categoria;
import com.odonto.dentisys.model.Cobranza;
import com.odonto.dentisys.model.Cuenta;
import com.odonto.dentisys.model.Medico;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    List<Cuenta> findByCategoria(Categoria categoria);

    List<Cuenta> findByCobranza(Cobranza cobranza);

    List<Cuenta> findByMedico(Medico medico);

    List<Cuenta> findByFechaMovimientoBetween(LocalDate fechaInicio, LocalDate fechaFin);
}