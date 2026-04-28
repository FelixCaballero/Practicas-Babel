package com.example.portal_paciente.Configuration;

import com.example.portal_paciente.model.portalUsuario;
import com.example.portal_paciente.repository.portalUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class portalUsuarioValidator {
    private final portalUsuarioRepository portalUsuarioRepository;

    public String validar(String username){
        portalUsuario portalUsuario =portalUsuarioRepository.findByusuario(username);
        if(portalUsuario==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el usuario con id: " + username);
        }
        return username;
    }
}
