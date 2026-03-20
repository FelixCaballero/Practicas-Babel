package com.example.portal_paciente.repository;

import com.example.portal_paciente.model.portalMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface portalMenuRepository extends JpaRepository<portalMenu,Integer> {

    List<portalMenu> findByIdPadreIsNullOrderByPosicionRaizAsc();

    Optional<portalMenu> findByNomMenuContainingIgnoreCase(String filtro);

    List<portalMenu> findByNivel(Integer nivel);

    List<portalMenu> findByIdPadre(Integer idPadre);
}
