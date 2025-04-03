package com.odonto.dentisys.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odonto.dentisys.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByNumeroDocumento(String numeroDocumento);

    boolean existsByEmail(String email);

    boolean existsByNumeroDocumento(String numeroDocumento);

    Optional<Usuario> findByEmailAndPassword(String email, String password);
}