package com.example.portal_paciente.repository;

import com.example.portal_paciente.model.portalUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface portalUsuarioRepository extends JpaRepository<portalUsuario,String> {
    portalUsuario findByusuario(String usuario);
}
