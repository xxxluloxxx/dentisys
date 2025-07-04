package com.odonto.dentisys.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odonto.dentisys.model.Banco;
import com.odonto.dentisys.model.Cobranza;
import com.odonto.dentisys.model.Proforma;

@Repository
public interface CobranzaRepository extends JpaRepository<Cobranza, Long> {
    List<Cobranza> findByProforma(Proforma proforma);

    List<Cobranza> findByProformaIn(List<Proforma> proformas);

    List<Cobranza> findByProformaInAndEstado(List<Proforma> proformas, String estado);

    List<Cobranza> findByFechaPagoBetween(LocalDate fechaInicio, LocalDate fechaFin);

    List<Cobranza> findByEstado(String estado);

    List<Cobranza> findByBanco(Banco banco);
}