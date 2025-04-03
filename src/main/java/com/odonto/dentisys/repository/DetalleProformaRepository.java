package com.odonto.dentisys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odonto.dentisys.model.DetalleProforma;
import com.odonto.dentisys.model.Proforma;

@Repository
public interface DetalleProformaRepository extends JpaRepository<DetalleProforma, Integer> {
    List<DetalleProforma> findByProforma(Proforma proforma);
}