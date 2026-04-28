package com.example.portal_paciente.service;

import BackOffice.portal_paciente.client.PortalPaginaControllerApi;
import com.example.portal_paciente.DTO.*;
import com.example.portal_paciente.controller.portalMenuController;
import com.example.portal_paciente.controller.portalPaginaController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class portalPaginaService {
    @Value("${backend.api.url}")
    private String backendApiUrl;
    private final RestTemplate restTemplate;
    private final PortalPaginaControllerApi PortalPaginaControllerApi;

    public portalPaginaDTO save(portalPaginaCreateDTO DTO) {
        String url = backendApiUrl+"/api/portal/pagina/create";

        ResponseEntity<portalPaginaDTO> response = restTemplate.postForEntity(
                url, DTO, portalPaginaDTO.class);
        return response.getBody();
    }

    public List<portalPaginaDTO> findAll() {
        String url = backendApiUrl + "/api/portal/pagina";

        ResponseEntity<List<portalPaginaDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<portalPaginaDTO>>() {}
        );

        return response.getBody();
    }

    public portalPaginaDTO findById(Integer id) {
        String url = backendApiUrl+"/api/portal/pagina/"+id;

        ResponseEntity<portalPaginaDTO> response = restTemplate.getForEntity(
                url,
                portalPaginaDTO.class
        );

        return response.getBody();
    }

    public void deleteById(Integer id) {
        String url = backendApiUrl + "/api/portal/pagina/delete/" + id;

        restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                Void.class
        );
    }

    public portalPaginaDTO update(Integer id,portalPaginaUpdateDTO portalPagina) {
        String url = backendApiUrl + "/api/portal/pagina/update/" + id;

        HttpEntity<portalPaginaUpdateDTO> request = new HttpEntity<>(portalPagina);

        ResponseEntity<portalPaginaDTO> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                request,
                portalPaginaDTO.class
        );

        return response.getBody();
    }

    public portalPaginaDTO findByDescripcion(String descripcion) {
        String url = backendApiUrl + "/api/portal/menu/search?filtro=" + descripcion;

        ResponseEntity<portalPaginaDTO> response = restTemplate.getForEntity(
                url,
                portalPaginaDTO.class
        );

        return response.getBody();
    }
}
