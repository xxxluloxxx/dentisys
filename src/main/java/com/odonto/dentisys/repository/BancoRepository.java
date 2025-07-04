package com.odonto.dentisys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odonto.dentisys.model.Banco;

@Repository
public interface BancoRepository extends JpaRepository<Banco, Long> {
} 