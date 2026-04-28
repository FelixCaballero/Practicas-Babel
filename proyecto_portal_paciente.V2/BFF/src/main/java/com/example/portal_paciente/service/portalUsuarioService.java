package com.example.portal_paciente.service;

import BackOffice.portal_paciente.client.PortalUsuarioControllerApi;
import com.example.portal_paciente.DTO.LoginRequest;
import com.example.portal_paciente.controller.portalUsuarioController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class portalUsuarioService {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Value("${backend.api.url}")
    private String backendApiUrl;
    private final RestTemplate restTemplate;
    private final PortalUsuarioControllerApi PortalUsuarioControllerApi;

    public LoginRequest save (LoginRequest DTO) {
        String url = backendApiUrl+"api/portal/usuario/create";

        DTO.setPassword(encoder.encode(DTO.getPassword()));

        ResponseEntity<LoginRequest> response = restTemplate.postForEntity(
                url, DTO, LoginRequest.class);

        return response.getBody();
    }

    public List<LoginRequest> findAll(){
        String url = backendApiUrl+"/api/portal/usuario";

        ResponseEntity<List<LoginRequest>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<LoginRequest>>() {}
        );

        return response.getBody();
    }

    public LoginRequest findById(String usuario) {
        String url = backendApiUrl+"/api/portal/usuario/"+usuario;

        ResponseEntity<LoginRequest> response = restTemplate.getForEntity(
                url,
                LoginRequest.class
        );
        return response.getBody();
    }

    public void deleteById(String usuario) {
        String url = backendApiUrl + "/api/portal/pagina/delete/" + usuario;

        restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                Void.class
        );
    }

    public LoginRequest update(LoginRequest portalUsuario) {
        String url = backendApiUrl + "/api/portal/pagina/update";

        portalUsuario.setPassword(encoder.encode(portalUsuario.getPassword()));
        HttpEntity<LoginRequest> request = new HttpEntity<>(portalUsuario);
        ResponseEntity<LoginRequest> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                request,
                LoginRequest.class
        );

        return response.getBody();
    }
    public boolean login(LoginRequest req) {
        String url = backendApiUrl + "/api/portal-usuario/login";

        try {
            ResponseEntity<Void> response = restTemplate.postForEntity(
                    url,
                    req,
                    Void.class
            );
            return response.getStatusCode() == HttpStatus.OK;

        } catch (HttpClientErrorException.Unauthorized e) {
            return false;
        }
    }

}
