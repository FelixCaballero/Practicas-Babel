package com.example.portal_paciente.Configuration;

import com.example.portal_paciente.model.portalPagina;
import com.example.portal_paciente.repository.portalPaginaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class portalPaginaValidator {
    private final portalPaginaRepository portalPaginaRepository;

    public portalPagina validar(Integer idPagina){
        portalPagina portalPagina = portalPaginaRepository.findById(idPagina).orElse(null);
        if(portalPagina == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el portalPagina con id: " + idPagina);
        }
        return portalPagina;
    }
}
