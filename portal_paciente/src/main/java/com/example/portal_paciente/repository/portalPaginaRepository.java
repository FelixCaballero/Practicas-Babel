package com.example.portal_paciente.repository;

import com.example.portal_paciente.model.portalPagina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface portalPaginaRepository extends JpaRepository<portalPagina,Integer> {
    Optional<portalPagina> findByDescripcionContainingIgnoreCase(String filtro);
}
