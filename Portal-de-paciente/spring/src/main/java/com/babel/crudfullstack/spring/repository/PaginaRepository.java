package com.babel.crudfullstack.spring.repository;

import com.babel.crudfullstack.spring.model.Pagina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaginaRepository extends JpaRepository<Pagina, String> {
    @Query("SELECT MAX(p.idPagina) FROM Pagina p")
    String findMaxId();
}