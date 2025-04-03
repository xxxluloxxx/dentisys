package com.odonto.dentisys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odonto.dentisys.model.General;

@Repository
public interface GeneralRepository extends JpaRepository<General, Integer> {
    boolean existsByParametro(String parametro);
}