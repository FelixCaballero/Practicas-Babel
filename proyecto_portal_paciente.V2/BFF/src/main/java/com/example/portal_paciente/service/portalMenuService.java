package com.example.portal_paciente.service;

import BackOffice.portal_paciente.model.PortalMenuDTO;
import com.example.portal_paciente.DTO.portalMenuCreateDTO;
import com.example.portal_paciente.DTO.portalMenuUpdateDTO;
import com.example.portal_paciente.controller.portalMenuController;
import lombok.RequiredArgsConstructor;
import BackOffice.portal_paciente.client.PortalMenuControllerApi;
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
public class portalMenuService {
    @Value("${backend.api.url}")
    private String backendApiUrl;
    private final RestTemplate restTemplate;
    private final PortalMenuControllerApi portalMenuController;

//    public portalMenuDTO save(portalMenuCreateDTO DTO) {
//        String url = backendApiUrl+"/api/portal/menu/create";
//        ResponseEntity<portalMenuDTO> response = restTemplate.postForEntity(
//                url, DTO, portalMenuDTO.class);
//        return response.getBody();
//    }
//
    public List<PortalMenuDTO> findAll() {
        return this.portalMenuController.findAll2();
    }

//    public portalMenuDTO findById(Integer id) {
//        String url = backendApiUrl + "/api/portal/menu/" + id;
//
//        ResponseEntity<portalMenuDTO> response = restTemplate.getForEntity(
//                url,
//                portalMenuDTO.class
//        );
//
//        return response.getBody();
//    }

    public void deleteById(Integer id) {
            String url = backendApiUrl + "/api/portal/menu/delete/" + id;

            restTemplate.exchange(
                    url,
                    HttpMethod.DELETE,
                    null,
                    Void.class
            );
    }

//    public portalMenuDTO update(Integer id, portalMenuUpdateDTO portalMenu) {
//        String url = backendApiUrl + "/api/portal/menu/update/" + id;
//
//        HttpEntity<portalMenuUpdateDTO> request = new HttpEntity<>(portalMenu);
//
//        ResponseEntity<portalMenuDTO> response = restTemplate.exchange(
//                url,
//                HttpMethod.PUT,
//                request,
//                portalMenuDTO.class
//        );
//
//        return response.getBody();
//    }

//    public List<portalMenuDTO> findByNivel(Integer nivel) {
//        String url = backendApiUrl + "/api/portal/menu/nivel/" + nivel;
//
//        ResponseEntity<List<portalMenuDTO>> response = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<portalMenuDTO>>() {}
//        );
//
//        return response.getBody();
//    }
//    public List<portalMenuDTO> findByPadre(Integer idPadre) {
//        String url = backendApiUrl + "/api/portal/menu/padre/" + idPadre;
//
//        ResponseEntity<List<portalMenuDTO>> response = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<portalMenuDTO>>() {}
//        );
//
//        return response.getBody();
//    }
//    public portalMenuDTO search(String filtro) {
//        String url = backendApiUrl + "/api/portal/menu/search?filtro=" + filtro;
//
//        ResponseEntity<portalMenuDTO> response = restTemplate.getForEntity(
//                url,
//                portalMenuDTO.class
//        );
//
//        return response.getBody();
//    }

}
