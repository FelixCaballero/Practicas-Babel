package com.example.portal_paciente.Configuration;

import com.example.portal_paciente.model.portalMenu;
import com.example.portal_paciente.repository.portalMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class portalMenuValidator {
    private final portalMenuRepository portalMenuRepository;

    public portalMenu validar(Integer idMenu){
        portalMenu portalMenu = portalMenuRepository.findById(idMenu).orElse(null);
        if(portalMenu == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el portalMenu con id: " + idMenu);
        }
        return portalMenu;
    }
}
