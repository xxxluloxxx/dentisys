package com.odonto.dentisys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odonto.dentisys.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    boolean existsByUsuario_NumeroDocumento(String numeroDocumento);

    boolean existsByUsuario_Email(String email);
}