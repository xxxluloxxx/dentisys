package com.odonto.dentisys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odonto.dentisys.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    boolean existsByNumeroDocumento(String numeroDocumento);

    boolean existsByEmail(String email);
}